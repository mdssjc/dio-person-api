package one.digitalinnovation.personapi.repository;

import one.digitalinnovation.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade {@link Person}.
 *
 * @author Marcelo dos Santos
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
