package one.digitalinnovation.personapi.dto.request;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.entity.Person;
import org.hibernate.validator.constraints.br.CPF;

/**
 * Modelo de dados para a entidade {@link Person}.
 *
 * @author Marcelo dos Santos
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

  private Long id;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String firstName;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String lastName;

  @NotEmpty
  @CPF
  private String cpf;

  private String birthDate;

  @Valid
  @NotEmpty
  private List<PhoneDto> phones;
}
