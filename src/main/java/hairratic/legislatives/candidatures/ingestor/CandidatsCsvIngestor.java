package hairratic.legislatives.candidatures.ingestor;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import hairratic.legislatives.candidatures.Candidat;
import hairratic.legislatives.candidatures.repository.CandidatsListRepository;
import hairratic.legislatives.candidatures.repository.CandidatsRepository;
import jakarta.enterprise.event.Observes;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;

public class CandidatsCsvIngestor {
    private final String csvURI;
    private final CandidatsRepository candidatsRepository;

    public CandidatsCsvIngestor(@ConfigProperty(name = "csv.uri") String csvURI, CandidatsListRepository candidatsRepository) {
        this.csvURI = csvURI;
        this.candidatsRepository = candidatsRepository;
    }

    public void ingest(@Observes StartupEvent event) throws IOException {
        Log.infof("Source: %s", csvURI);
        try (MappingIterator<Candidat> it = new CsvMapper()
                .readerFor(Candidat.class)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues(URI.create(csvURI).toURL())) {
            List<Candidat> candidats = it.readAll();
            candidatsRepository.setCandidats(candidats);
            Log.infof("Ingested %d candidats.%n", candidats.size());
        }
    }
}
