package hairratic.legislatives.candidatures;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import jakarta.enterprise.event.Observes;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;

public class CandidatsCsvIngestor {
    private final String csvURI;
    private final CandidatsListRepository candidatsListRepository;

    public CandidatsCsvIngestor(@ConfigProperty(name = "csv.uri") String csvURI, CandidatsListRepository candidatsListRepository) {
        this.csvURI = csvURI;
        this.candidatsListRepository = candidatsListRepository;
    }

    public void ingest(@Observes StartupEvent event) throws IOException {
        Log.infof("Source: %s", csvURI);
        try (MappingIterator<Candidat> it = new CsvMapper()
                .readerFor(Candidat.class)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues(URI.create(csvURI).toURL())) {
            List<Candidat> candidats = it.readAll();
            candidatsListRepository.setCandidats(candidats);
            Log.infof("Ingested %d candidats.%n", candidats.size());
        }
    }
}
