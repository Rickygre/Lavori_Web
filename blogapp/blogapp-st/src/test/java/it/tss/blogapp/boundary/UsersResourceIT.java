package it.tss.blogapp.boundary;

import java.math.BigDecimal;
import java.net.URI;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tss
 */
public class UsersResourceIT {

    UsersResourceClient client;

    @BeforeEach
    public void ini() {
        URI baseURI = URI.create("http://localhost:8080/blogapp/resources/");
        this.client = RestClientBuilder.newBuilder()
                .baseUri(baseURI)
                .build(UsersResourceClient.class);

    }
    
    @Test
    public void login(){
        JsonObject credential =  Json.createObjectBuilder()
                .add("usr", "bianchi@gmail.com")
                .add("pwd","bianchi")
                .build();
        JsonObject result = this.client.login(credential);
        Assertions.assertTrue(result.containsKey("jwt"));
        System.out.println(result);
    }
    
    @Test
    public void registration(){
        JsonObject credential =  Json.createObjectBuilder()
                .add("fisrt_name", "alex")
                .add("last_name","bianchi")
                .add("usr", "bianchi@gmail.com")
                .add("pwd","bianchi")
                .build();
        
               
                
        
    }

}
