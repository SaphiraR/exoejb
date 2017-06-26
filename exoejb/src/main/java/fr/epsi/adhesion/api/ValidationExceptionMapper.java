package fr.epsi.adhesion.api;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import fr.epsi.adhesion.ValidationException;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException exception) {
	    return Response.status(Status.BAD_REQUEST)
                       .type(MediaType.APPLICATION_JSON)
                       .entity(exception.getRaisons())
                       .build();
	}

}
