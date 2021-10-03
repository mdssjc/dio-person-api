package one.digitalinnovation.personapi.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * Modelo de dados para a mensagem de resposta.
 *
 * @author Marcelo dos Santos
 */
@Data
@Builder
public class MessageResponseDto {

  private String message;
}
