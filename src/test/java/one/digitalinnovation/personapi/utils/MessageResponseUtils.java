package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.dto.response.MessageResponseDto;

/**
 * Utilitário para criação de {@link MessageResponseDto}.
 *
 * @author Marcelo dos Santos
 */
public class MessageResponseUtils {

  public static MessageResponseDto createExpectedMessageResponse(Long id) {
    return MessageResponseDto
        .builder()
        .message("Created person with ID " + id)
        .build();
  }

  public static MessageResponseDto updateExpectedMessageResponse(Long id) {
    return MessageResponseDto
        .builder()
        .message("Updated person with ID " + id)
        .build();
  }
}
