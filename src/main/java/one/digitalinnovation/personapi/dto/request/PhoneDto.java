package one.digitalinnovation.personapi.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.entity.Phone;
import one.digitalinnovation.personapi.enums.PhoneType;

/**
 * Modelo de dados para a entidade {@link Phone}.
 *
 * @author Marcelo dos Santos
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {

  private Long id;

  @Enumerated(EnumType.STRING)
  private PhoneType type;

  @NotEmpty
  @Size(min = 13, max = 14)
  private String number;
}
