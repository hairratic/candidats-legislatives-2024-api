package hairratic.legislatives.circonscriptions.services;

import hairratic.legislatives.circonscriptions.data.CirconscriptionProperties;
import hairratic.legislatives.circonscriptions.repositories.CirconscriptionsAreasRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CirconscriptionsService {
    private final CirconscriptionsAreasRepository circonscriptionsAreasRepository;

    public CirconscriptionsService(CirconscriptionsAreasRepository circonscriptionsAreasRepository) {
        this.circonscriptionsAreasRepository = circonscriptionsAreasRepository;
    }

    public Optional<CirconscriptionProperties> findCodeCirconscriptionWithCoordinates(float X, float Y) {
        for (var area : circonscriptionsAreasRepository.getAll()) {
            if (area.contains(X, Y)) {
                return Optional.of(area.properties());
            }
        }
        return Optional.empty();
    }
}