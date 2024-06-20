package hairratic.legislatives.candidatures.repository;

import hairratic.legislatives.candidatures.Candidat;

import java.util.List;

public interface CandidatsRepository {
    public void setCandidats(List<Candidat> candidats);
    public List<Candidat> getAll();
    public List<Candidat> getCandidatsForDepartementAndCirconscription(
            String departement, String circonscription
    );
}
