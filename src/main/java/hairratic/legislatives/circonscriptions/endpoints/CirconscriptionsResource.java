package hairratic.legislatives.circonscriptions.endpoints;

import hairratic.legislatives.circonscriptions.data.CirconscriptionProperties;
import hairratic.legislatives.circonscriptions.services.CirconscriptionsService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@Path("circonscription")
public class CirconscriptionsResource {
    private final CirconscriptionsService circonscriptionsService;

    public CirconscriptionsResource(CirconscriptionsService circonscriptionsService) {
        this.circonscriptionsService = circonscriptionsService;
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(NotFoundException e) {
        return RestResponse.status(Response.Status.NOT_FOUND, e.getMessage());
    }

    @Operation(summary = "Get circonscription(s) at position", description = "Returns a list of all circonscriptions at a given position")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{XCoordinates}/{YCoordinates}")
    public CirconscriptionProperties circonscriptionWithCoordinates(float XCoordinates, float YCoordinates){
        return circonscriptionsService.findCodeCirconscriptionWithCoordinates(XCoordinates,YCoordinates)
                .orElseThrow(() -> new NotFoundException("No circonscription found for given coordinates"));
    }
}
