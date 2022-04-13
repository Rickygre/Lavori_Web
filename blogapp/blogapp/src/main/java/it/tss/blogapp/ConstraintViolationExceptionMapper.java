
package it.tss.blogapp;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author tss
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        Response.ResponseBuilder rb = Response.status(Response.Status.BAD_REQUEST);
        ex.getConstraintViolations()
                .forEach(v -> rb.header("cased-by-" + v.getPropertyPath().toString(), v.getMessage()));
        return rb.build();
    }
    
}
