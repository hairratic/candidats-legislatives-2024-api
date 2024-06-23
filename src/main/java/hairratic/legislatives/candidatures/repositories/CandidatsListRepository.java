package hairratic.legislatives.candidatures.repositories;

import hairratic.legislatives.candidatures.data.Candidat;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CandidatsListRepository implements  CandidatsRepository{
    private List<Candidat> candidats = List.of();

    public void setCandidats(List<Candidat> candidats){
        this.candidats = candidats;
    }

    public List<Candidat> getAll(){
        return candidats;
    }

    public List<Candidat> getCandidatsForDepartementAndCirconscription(
            String departement, String circonscription
    ){
        return candidats.stream().filter(
                candidat -> {
                    return candidat.departement().equals(departement)
                            && candidat.circonscription().equals(circonscription);
                }
        ).toList();
    }

}
