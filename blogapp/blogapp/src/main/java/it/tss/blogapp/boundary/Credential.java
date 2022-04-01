
package it.tss.blogapp.boundary;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author tss
 */


public class Credential {
    @NotBlank //non puo essere bianca quindi vuota
    public String usr;
    @NotBlank
    public String pwd;
    
    
    
    
}
