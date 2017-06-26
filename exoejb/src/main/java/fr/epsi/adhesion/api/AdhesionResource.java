package fr.epsi.adhesion.api;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fr.epsi.adhesion.Adhesion;
import fr.epsi.adhesion.AdhesionRepository;
import fr.epsi.adhesion.ValidationException;

@Path("/api/adhesion")
public class AdhesionResource {

	@EJB
	private AdhesionRepository repository;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Adhesion adhesion, @Context UriInfo uriInfo) throws SQLException, ValidationException {
		adhesion.setConditionsAcceptees(true);
		repository.create(adhesion, adhesion.getMotDePasse());
		URI uri = uriInfo.getRequestUriBuilder().path("/" + adhesion.getId()).build();
		return Response.created(uri).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AdhesionAdapter> getAll() throws SQLException {
		return repository.getAll().stream().map(AdhesionAdapter::new).collect(Collectors.toList());
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AdhesionAdapter get(@PathParam("id") long id) throws SQLException {
		Adhesion adhesion = repository.get(id);
		if (adhesion == null) {
			throw new NotFoundException();
		}
		return new AdhesionAdapter(adhesion);
	}

	@DELETE
	public void delete(@QueryParam("email") String email) throws SQLException {
		repository.delete(email);
	}
}
