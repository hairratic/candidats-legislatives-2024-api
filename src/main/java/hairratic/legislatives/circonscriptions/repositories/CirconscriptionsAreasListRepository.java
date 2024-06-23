package hairratic.legislatives.circonscriptions.repositories;

import hairratic.legislatives.circonscriptions.data.CirconscriptionArea;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CirconscriptionsAreasListRepository implements CirconscriptionsAreasRepository {
    private List<CirconscriptionArea> circonscriptionAreas;

    @Override
    public void setCirconscriptions(List<CirconscriptionArea> circonscriptions) {
        this.circonscriptionAreas = circonscriptions;
    }

    @Override
    public List<CirconscriptionArea> getAll() {
        return circonscriptionAreas;
    }
}
