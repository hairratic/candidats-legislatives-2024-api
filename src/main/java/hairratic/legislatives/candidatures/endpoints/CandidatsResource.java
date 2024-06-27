package hairratic.legislatives.candidatures.endpoints;

import hairratic.legislatives.candidatures.data.Candidat;
import hairratic.legislatives.candidatures.services.CandidatsService;
import hairratic.legislatives.circonscriptions.data.CirconscriptionProperties;
import hairratic.legislatives.circonscriptions.services.CirconscriptionsService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.util.List;

@Path("/")
public class CandidatsResource {
    private final CandidatsService candidatsService;
    private final CirconscriptionsService circonscriptionsService;

    public CandidatsResource(CandidatsService candidatsService, CirconscriptionsService circonscriptionsService) {
        this.candidatsService = candidatsService;
        this.circonscriptionsService = circonscriptionsService;
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(NotFoundException e) {
        return RestResponse.status(Response.Status.NOT_FOUND, e.getMessage());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Candidat> allCandidats(){
        return candidatsService.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{departement}")
    public List<Candidat> candidatsWithDepartement(@PathParam("departement") String departement){
        return candidatsService.getCandidatsForDepartement(departement);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{departement}/{circonscription}")
    public List<Candidat> candidatsWithDepartementAndCirconscription(@PathParam("departement") String departement,
                                                                     @PathParam("circonscription") String circonscription){
        return candidatsService.getCandidatsForDepartementAndCirconscription(departement, circonscription);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/coordinates/{XCoordinate}/{YCoordinate}")
    public List<Candidat> circonscriptionWithCoordinates(@PathParam("XCoordinate") float XCoordinates,
                                                         @PathParam("YCoordinate") float YCoordinates){
        CirconscriptionProperties circonscription = circonscriptionsService.findCodeCirconscriptionWithCoordinates(XCoordinates, YCoordinates)
            .orElseThrow(() -> new NotFoundException("No circonscription found for given coordinates"));
        return candidatsService.getCandidatsForDepartementAndCirconscription(circonscription.codeDepartement(), circonscription.codeCirconscription());
    }
}