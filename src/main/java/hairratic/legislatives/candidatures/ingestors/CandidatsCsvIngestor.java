package hairratic.legislatives.candidatures.ingestors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import hairratic.legislatives.candidatures.data.Candidat;
import hairratic.legislatives.candidatures.repositories.CandidatsListRepository;
import hairratic.legislatives.candidatures.repositories.CandidatsRepository;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

public class CandidatsCsvIngestor {
    private final static String REMOTE_MODE = "remote";

    private final String csvSrc;
    private final String csvMode;
    private final CandidatsRepository candidatsRepository;

    public CandidatsCsvIngestor(@ConfigProperty(name = "csv.src") String csvSrc,
                                @ConfigProperty(name = "csv.mode") String csvMode,
                                CandidatsListRepository candidatsRepository) {
        this.csvSrc = csvSrc;
        this.csvMode = csvMode;
        this.candidatsRepository = candidatsRepository;
    }

    public MappingIterator<Candidat> fetchData() throws IOException {
        ObjectReader objectReader = new CsvMapper()
                .readerFor(Candidat.class)
                .with(CsvSchema.emptySchema().withHeader());

        if(REMOTE_MODE.equals(csvMode))
            return objectReader.readValues(URI.create(csvSrc).toURL());

        InputStream csvStream = getClass().getClassLoader().getResourceAsStream(csvSrc);
        return objectReader.readValues(csvStream);
    }

    public void ingest(@Observes StartupEvent event) throws IOException {
        Log.infof("Source: %s @ %s", csvMode, csvSrc);
        try (MappingIterator<Candidat> it = this.fetchData()) {
            List<Candidat> candidats = it.readAll();
            candidatsRepository.setCandidats(candidats);
            Log.infof("Ingested %d candidats", candidats.size());
        }

    }
}
