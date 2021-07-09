package one.digitalinnovation.personapi.exception;

import one.digitalinnovation.personapi.entity.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção para a entidade {@link Person} não encontrada.
 *
 * @author Marcelo dos Santos
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(Long id) {
        super("Person not found with ID " + id);
    }
}
