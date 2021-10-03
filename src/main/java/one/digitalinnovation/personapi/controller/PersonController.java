package one.digitalinnovation.personapi.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDto;
import one.digitalinnovation.personapi.dto.response.MessageResponseDto;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints para o gerenciamento da entidade {@link Person}.
 *
 * @author Marcelo dos Santos
 */
@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MessageResponseDto createPerson(@RequestBody @Valid final PersonDto personDto) {
    return personService.createPerson(personDto);
  }

  @GetMapping
  public List<PersonDto> listAll() {
    return personService.listAll();
  }

  @GetMapping("/{id}")
  public PersonDto findById(@PathVariable final Long id) throws PersonNotFoundException {
    return personService.findById(id);
  }

  @PutMapping("/{id}")
  public MessageResponseDto updateById(@PathVariable final Long id,
                                       @RequestBody @Valid final PersonDto personDto)
      throws PersonNotFoundException {
    return personService.updateById(id, personDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable final Long id) throws PersonNotFoundException {
    personService.delete(id);
  }
}
