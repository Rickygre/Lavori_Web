
package airhacks.service.SecurityEncoding;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author tss
 */
public class SecurityEncoding {
    
    public static String shaHash(String value) {
        try {
            MessageDigest m = null;
            m = MessageDigest.getInstance("SHA-256"); // algoritmo di endcoding
            byte[] hash = m.digest(value.getBytes("UTF-8")); // applico l'algorittmo di endcoding
            byte[] encodedhash = Base64.getEncoder().encode(hash); //lo codifica in base 64
            return new String(encodedhash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new IllegalArgumentException("Impossibile codificare in SHA-256", ex);
        }
    }
    
}
