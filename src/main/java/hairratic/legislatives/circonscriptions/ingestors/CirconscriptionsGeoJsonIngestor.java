package hairratic.legislatives.circonscriptions.ingestors;

import hairratic.legislatives.circonscriptions.data.CirconscriptionArea;
import hairratic.legislatives.circonscriptions.repositories.CirconscriptionsAreasRepository;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.locationtech.jts.io.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CirconscriptionsGeoJsonIngestor {
    private final String circonscriptionGeoJson;
    private final CirconscriptionsAreasRepository circonscriptionsAreasRepository;

    public CirconscriptionsGeoJsonIngestor(@ConfigProperty(name = "circonscription.geojson") String circonscriptionGeoJson, CirconscriptionsAreasRepository circonscriptionsAreasRepository) {
        this.circonscriptionGeoJson = circonscriptionGeoJson;
        this.circonscriptionsAreasRepository = circonscriptionsAreasRepository;
    }

    public void ingest(@Observes StartupEvent event) throws IOException {
        Log.infof("GeoJson source: %s", circonscriptionGeoJson);

        try (InputStream geoJsonStream = getClass().getClassLoader().getResourceAsStream(circonscriptionGeoJson)) {
            String jsonStr = new String(geoJsonStream.readAllBytes(), StandardCharsets.UTF_8);
            List<CirconscriptionArea> circonscriptionsAreasList = CirconscriptionArea.listFromJson(jsonStr);
            circonscriptionsAreasRepository.setCirconscriptions(circonscriptionsAreasList);
            Log.infof("Ingested %d circonscriptions areas", circonscriptionsAreasList.size());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
