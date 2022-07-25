
package airhacks.service.boundary;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Greggio Riccardo
 */


public class Credential {
    @NotBlank 
    public String usr;
    @NotBlank
    public String pwd;
    
    
    
    
}
