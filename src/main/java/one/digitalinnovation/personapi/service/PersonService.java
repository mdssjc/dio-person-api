package one.digitalinnovation.personapi.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDto;
import one.digitalinnovation.personapi.dto.response.MessageResponseDto;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

/**
 * Regras de negócio para a entidade {@link Person}.
 *
 * @author Marcelo dos Santos
 */
@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;

  private static final PersonMapper PERSON_MAPPER = PersonMapper.INSTANCE;

  public MessageResponseDto createPerson(final PersonDto personDto) {
    final var personToSave = PERSON_MAPPER.toModel(personDto);
    final var savedPerson = personRepository.save(personToSave);

    return createMessageResponse(savedPerson.getId(), "Created person with ID ");
  }

  public List<PersonDto> listAll() {
    return personRepository.findAll()
        .stream()
        .map(PERSON_MAPPER::toDto)
        .collect(Collectors.toList());
  }

  public PersonDto findById(final Long id) throws PersonNotFoundException {
    final var person = verifyIfExists(id);

    return PERSON_MAPPER.toDto(person);
  }

  public void delete(final Long id) throws PersonNotFoundException {
    verifyIfExists(id);

    personRepository.deleteById(id);
  }

  public MessageResponseDto updateById(final Long id, final PersonDto personDto)
      throws PersonNotFoundException {
    verifyIfExists(id);

    final var personToUpdate = PERSON_MAPPER.toModel(personDto);
    final var updatedPerson = personRepository.save(personToUpdate);

    return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
  }

  private Person verifyIfExists(final Long id) throws PersonNotFoundException {
    return personRepository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException(id));
  }

  private MessageResponseDto createMessageResponse(final Long id, final String message) {
    return MessageResponseDto.builder()
        .message(message + id)
        .build();
  }
}
