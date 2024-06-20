package hairratic.legislatives.candidatures;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/")
public class CandidatsResource {
    private  final CandidatsService candidatsService;

    public CandidatsResource(CandidatsService candidatsService) {
        this.candidatsService = candidatsService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Candidat> allCandidats(){
        return candidatsService.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{departement}/{circonscription}")
    public List<Candidat> candidatsPourDepartementEtCirconscription(@PathParam("departement") String departement,
                                                                    @PathParam("circonscription") String circonscription){
        return candidatsService.getCandidatsForDepartementAndCirconscription(departement, circonscription);
    }

}
