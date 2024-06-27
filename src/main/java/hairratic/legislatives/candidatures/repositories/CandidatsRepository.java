package hairratic.legislatives.candidatures.repositories;

import hairratic.legislatives.candidatures.data.Candidat;

import java.util.List;

public interface CandidatsRepository {
    public void setCandidats(List<Candidat> candidats);
    public List<Candidat> getAll();
    public List<Candidat> getCandidatsForDepartementAndCirconscription(
            String departement, String circonscription
    );
    public List<Candidat> getCandidatsForDepartement(String departement);
}
