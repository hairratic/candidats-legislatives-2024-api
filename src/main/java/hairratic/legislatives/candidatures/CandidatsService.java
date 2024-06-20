package hairratic.legislatives.candidatures;

import hairratic.legislatives.candidatures.repository.CandidatsListRepository;
import hairratic.legislatives.candidatures.repository.CandidatsRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CandidatsService {
    private  final CandidatsRepository candidatsRepository;

    public CandidatsService(CandidatsListRepository candidatsRepository) {
        this.candidatsRepository = candidatsRepository;
    }

    public List<Candidat> getAll(){
        return candidatsRepository.getAll();
    }

    public List<Candidat> getCandidatsForDepartementAndCirconscription(
            String departement, String circonscription
    ){
        return candidatsRepository.getCandidatsForDepartementAndCirconscription(departement, circonscription);
    }
}
