package hairratic.legislatives.circonscriptions.repositories;

import hairratic.legislatives.circonscriptions.data.CirconscriptionArea;

import java.util.List;

public interface CirconscriptionsAreasRepository {
    public void setCirconscriptions(List<CirconscriptionArea> circonscriptions);
    public List<CirconscriptionArea> getAll();
}
