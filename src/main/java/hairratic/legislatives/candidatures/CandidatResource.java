package hairratic.legislatives.candidatures;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/")
public class CandidatResource {
    private  final CandidatsListRepository candidatsListRepository;

    public CandidatResource(CandidatsListRepository candidatsListRepository) {
        this.candidatsListRepository = candidatsListRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{departement}/{circonscription}")
    public List<Candidat> candidatsPourDepartementEtCirconscription(@PathParam("departement") String departement,
                                                                    @PathParam("circonscription") String circonscription){
        return candidatsListRepository.getAll().stream().filter(
                candidat -> {
                    return candidat.departement().equals(departement)
                            && candidat.circonscription().equals(circonscription);
                }
        ).toList();
    }

}
