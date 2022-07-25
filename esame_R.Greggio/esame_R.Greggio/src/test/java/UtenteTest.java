
import airhacks.service.entity.Utente;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Gregio Riccardo
 */
public class UtenteTest {

    private Validator validator;
    Set<ConstraintViolation<Utente>> constraintViolations;

    @BeforeEach
    public void init() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testNotValid() {

        Utente u = new Utente();

        //nome
        u.setNome("");

        constraintViolations
                = validator.validate(u);

        boolean anyMatch = constraintViolations.stream()
                .anyMatch(v -> v.getPropertyPath().toString()
                .equals("nome"));

        Assertions.assertTrue(anyMatch);

        //cognome
        u.setCognome("   ");
        constraintViolations
                = validator.validate(u);

        anyMatch = constraintViolations.stream()
                .anyMatch(v -> v.getPropertyPath().toString()
                .equals("cognome"));

        Assertions.assertTrue(anyMatch);

        //email
        u.setEmail("www.hotmail.it");
        constraintViolations
                = validator.validate(u);

        anyMatch = constraintViolations.stream()
                .anyMatch(v -> v.getPropertyPath().toString()
                .equals("email"));

        Assertions.assertTrue(anyMatch);

        //password
        u.setPassword("123");
        constraintViolations
                = validator.validate(u);

        anyMatch = constraintViolations.stream()
                .anyMatch(v -> v.getPropertyPath().toString()
                .equals("pwd"));

        Assertions.assertTrue(anyMatch);
    }

    @Test
    public void testValid() {
        Utente utente = new Utente();
        utente.setNome("mario");
        Assertions.assertTrue(utente.getNome() != null && utente.getNome().equals("mario"));

        utente.setCognome("rossi");
        Assertions.assertTrue(utente.getCognome() != null && utente.getCognome().equals("rossi"));

        utente.setEmail("rossi@hotmail.it");
        Assertions.assertTrue(utente.getEmail() != null && utente.getEmail().equals("rossi@hotmail.it"));

        utente.setPassword("12345");
        Assertions.assertTrue(utente.getPassword() != null && utente.getPassword().equals("12345"));

        constraintViolations = validator.validate(utente);

        Assertions.assertTrue(constraintViolations.isEmpty());

    }

}
