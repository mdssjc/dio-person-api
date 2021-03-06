package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.dto.request.PersonDto;
import one.digitalinnovation.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Utilitário para criação de {@link Person}.
 *
 * @author Marcelo dos Santos
 */
public class PersonUtils {

  private static final String FIRST_NAME = "Rodrigo";
  private static final String LAST_NAME = "Peleias";
  private static final String CPF_NUMBER = "369.333.878-79";
  private static final long PERSON_ID = 1L;
  public static final LocalDate BIRTH_DATE = LocalDate.of(2010, 10, 1);

  public static PersonDto createFakeDTO() {
    return PersonDto.builder()
                    .firstName(FIRST_NAME)
                    .lastName(LAST_NAME)
                    .cpf(CPF_NUMBER)
                    .birthDate("04-04-2010")
                    .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
                    .build();
  }

  public static Person createFakeEntity() {
    return Person.builder()
                 .id(PERSON_ID)
                 .firstName(FIRST_NAME)
                 .lastName(LAST_NAME)
                 .cpf(CPF_NUMBER)
                 .birthDate(BIRTH_DATE)
                 .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                 .build();
  }

  public static List<PersonDto> createFakeDTO(int amount) {
    return LongStream.range(1, amount + 1)
                     .mapToObj(id -> PersonDto.builder()
                                              .id(id)
                                              .firstName(FIRST_NAME)
                                              .lastName(LAST_NAME)
                                              .cpf(CPF_NUMBER)
                                              .birthDate("04-04-2010")
                                              .phones(Collections.singletonList(
                                                  PhoneUtils.createFakeDTO()))
                                              .build())
                     .collect(Collectors.toList());
  }

  public static List<Person> createFakeEntity(int amount) {
    return LongStream.range(1, amount + 1)
                     .mapToObj(id -> Person.builder()
                                           .id(id)
                                           .firstName(FIRST_NAME)
                                           .lastName(LAST_NAME)
                                           .cpf(CPF_NUMBER)
                                           .birthDate(BIRTH_DATE)
                                           .phones(Collections.singletonList(
                                               PhoneUtils.createFakeEntity()))
                                           .build())
                     .collect(Collectors.toList());
  }
}
