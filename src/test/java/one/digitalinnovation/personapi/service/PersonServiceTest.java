package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static one.digitalinnovation.personapi.utils.MessageResponseUtils.createExpectedMessageResponse;
import static one.digitalinnovation.personapi.utils.MessageResponseUtils.updateExpectedMessageResponse;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

/**
 * Testes das regras de negócio de {@link Person}.
 *
 * @author Marcelo dos Santos
 */
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;

  @DisplayName("Create Person")
  @Test
  void test_given_person_dto_then_return_saved_message() {
    var personDTO = createFakeDTO();
    var expectedSavedPerson = createFakeEntity();
    given(personRepository.save(any(Person.class))).willReturn(expectedSavedPerson);

    var expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
    var successMessage = personService.createPerson(personDTO);

    then(personRepository).should().save(any(Person.class));
    assertThat(successMessage).isEqualTo(expectedSuccessMessage);
  }

  @DisplayName("List All")
  @Test
  void test_return_person_dto_list() {
    var expectedAmount = 3;
    var person = createFakeEntity(expectedAmount);
    given(personRepository.findAll()).willReturn(person);

    var returnedPersonDtoList = personService.listAll();

    then(personRepository).should().findAll();
    assertThat(returnedPersonDtoList).hasSize(expectedAmount);
  }

  @DisplayName("Find By Id")
  @Test
  void test_given_person_id_then_return_person_dto() throws PersonNotFoundException {
    var personId = 1L;
    var person = createFakeEntity();
    given(personRepository.findById(personId)).willReturn(Optional.of(person));

    var returnedPersonDto = personService.findById(personId);

    then(personRepository).should().findById(personId);
    assertThat(returnedPersonDto.getId()).isEqualTo(personId);
  }

  @DisplayName("Find By Id - PersonNotFoundException")
  @Test
  void test_given_person_id_when_not_found_then_throw_an_exception() {
    var personId = 1L;
    given(personRepository.findById(personId)).willReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.findById(personId));

    then(personRepository).should().findById(personId);
  }

  @DisplayName("Delete")
  @Test
  void test_given_person_id_then_delete_that_person() throws PersonNotFoundException {
    var personId = 1L;
    var person = createFakeEntity();
    given(personRepository.findById(personId)).willReturn(Optional.of(person));
    doNothing().when(personRepository).deleteById(personId);

    personService.delete(personId);

    then(personRepository).should().findById(personId);
    then(personRepository).should().deleteById(personId);
  }

  @DisplayName("Delete - PersonNotFoundException")
  @Test
  void test_given_person_id_when_delete_not_found_then_throw_an_exception() {
    var personId = 1L;
    given(personRepository.findById(personId)).willReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.delete(personId));

    then(personRepository).should().findById(personId);
  }

  @DisplayName("Update By Id")
  @Test
  void test_given_person_id_and_person_dto_then_return_updated_message()
      throws PersonNotFoundException {
    var personId = 1L;
    var personDTO = createFakeDTO();
    var expectedSavedPerson = createFakeEntity();
    given(personRepository.findById(personId)).willReturn(Optional.of(expectedSavedPerson));
    given(personRepository.save(any(Person.class))).willReturn(expectedSavedPerson);

    var expectedSuccessMessage = updateExpectedMessageResponse(expectedSavedPerson.getId());
    var successMessage = personService.updateById(personId, personDTO);

    then(personRepository).should().findById(personId);
    then(personRepository).should().save(any(Person.class));
    assertThat(successMessage).isEqualTo(expectedSuccessMessage);
  }

  @DisplayName("Update By Id - PersonNotFoundException")
  @Test
  void test_given_person_id_and_person_dto_when_not_found_then_throw_an_exception() {
    var personId = 1L;
    given(personRepository.findById(personId)).willReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.delete(personId));

    then(personRepository).should().findById(personId);
  }
}
