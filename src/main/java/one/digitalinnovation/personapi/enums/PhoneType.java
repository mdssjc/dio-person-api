package one.digitalinnovation.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Tipos de contato telefônico.
 *
 * @author Marcelo dos Santos
 */
@Getter
@AllArgsConstructor
public enum PhoneType {

  HOME("Home"),
  MOBILE("Mobile"),
  COMMERCIAL("Commercial");

  private final String description;
}
