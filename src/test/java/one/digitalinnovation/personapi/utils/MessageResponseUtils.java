package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;

/**
 * Utilitário para criação de {@link MessageResponseDTO}.
 *
 * @author Marcelo dos Santos
 */
public class MessageResponseUtils {

    public static MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }

    public static MessageResponseDTO updateExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Updated person with ID " + id)
                .build();
    }
}
