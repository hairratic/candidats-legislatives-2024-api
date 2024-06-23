package hairratic.legislatives.circonscriptions.endpoints;

import hairratic.legislatives.circonscriptions.data.CirconscriptionProperties;
import hairratic.legislatives.circonscriptions.services.CirconscriptionsService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("circonscription")
public class CirconscriptionsResource {
    private final CirconscriptionsService circonscriptionsService;

    public CirconscriptionsResource(CirconscriptionsService circonscriptionsService) {
        this.circonscriptionsService = circonscriptionsService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{XCoordinates}/{YCoordinates}")
    public CirconscriptionProperties circonscriptionWithCoordinates(float XCoordinates, float YCoordinates){
        return circonscriptionsService.findCodeCirconscriptionWithCoordinates(XCoordinates,YCoordinates).orElseThrow();
    }
}
