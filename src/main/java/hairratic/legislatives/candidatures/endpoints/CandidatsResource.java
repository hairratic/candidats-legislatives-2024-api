package hairratic.legislatives.candidatures.endpoints;

import hairratic.legislatives.candidatures.data.Candidat;
import hairratic.legislatives.candidatures.services.CandidatsService;
import hairratic.legislatives.circonscriptions.data.CirconscriptionProperties;
import hairratic.legislatives.circonscriptions.services.CirconscriptionsService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/")
public class CandidatsResource {
    private final CandidatsService candidatsService;
    private final CirconscriptionsService circonscriptionsService;

    public CandidatsResource(CandidatsService candidatsService, CirconscriptionsService circonscriptionsService) {
        this.candidatsService = candidatsService;
        this.circonscriptionsService = circonscriptionsService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Candidat> allCandidats(){
        return candidatsService.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{departement}/{circonscription}")
    public List<Candidat> candidatsWithDepartementEtCirconscription(@PathParam("departement") String departement,
                                                                    @PathParam("circonscription") String circonscription){
        return candidatsService.getCandidatsForDepartementAndCirconscription(departement, circonscription);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/coordinates/{XCoordinate}/{YCoordinate}")
    public List<Candidat> circonscriptionWithCoordinates(@PathParam("XCoordinate") float XCoordinates,
                                                         @PathParam("YCoordinate") float YCoordinates){
        CirconscriptionProperties circonscription = circonscriptionsService.findCodeCirconscriptionWithCoordinates(XCoordinates, YCoordinates).orElseThrow();
        return candidatsService.getCandidatsForDepartementAndCirconscription(circonscription.codeDepartement(), circonscription.codeCirconscription());
    }
}