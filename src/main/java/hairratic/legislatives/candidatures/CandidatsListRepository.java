package hairratic.legislatives.candidatures;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CandidatsListRepository {
    private List<Candidat> candidats = List.of();

    public void setCandidats(List<Candidat> candidats){
        this.candidats = candidats;
    }

    public List<Candidat> getAll(){
        return candidats;
    }
}
