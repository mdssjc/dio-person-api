package one.digitalinnovation.personapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static one.digitalinnovation.personapi.utils.MessageResponseUtils.createExpectedMessageResponse;
import static one.digitalinnovation.personapi.utils.MessageResponseUtils.updateExpectedMessageResponse;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes dos endpoints de {@link Person}.
 *
 * @author Marcelo dos Santos
 */
@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @MockBean
    PersonService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @AfterEach
    void tearDown() {
        reset(service);
    }

    @DisplayName("Create Person")
    @Test
    void test_given_person_content_then_return_created_status() throws Exception {
        var personId = 1L;
        var personDTO = createFakeDTO();
        personDTO.setId(personId);
        var expectedSuccessMessage = createExpectedMessageResponse(personDTO.getId());
        given(service.createPerson(personDTO)).willReturn(expectedSuccessMessage);

        mockMvc.perform(post("/api/v1/people").contentType(MediaType.APPLICATION_JSON)
                                              .content(mapper.writeValueAsString(personDTO)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.message", is(expectedSuccessMessage.getMessage())))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        then(service).should().createPerson(personDTO);
    }

    @DisplayName("Create Person - Invalid")
    @Test
    void test_given_person_content_when_invalid_then_return_bad_request_status() throws Exception {
        var personDTO = PersonDTO.builder()
                                 .id(1L)
                                 .build();

        mockMvc.perform(post("/api/v1/people").contentType(MediaType.APPLICATION_JSON)
                                              .content(mapper.writeValueAsString(personDTO)))
               .andExpect(status().isBadRequest());
    }

    @DisplayName("List All")
    @Test
    void test_return_ok_status() throws Exception {
        var expectedAmount = 3;
        var personDTO = createFakeDTO(expectedAmount);
        given(service.listAll()).willReturn(personDTO);

        mockMvc.perform(get("/api/v1/people"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(expectedAmount)))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        then(service).should().listAll();
    }

    @DisplayName("Find By Id")
    @Test
    void test_given_person_id_then_return_ok_status() throws Exception {
        var personId = 1L;
        var personDTO = createFakeDTO();
        personDTO.setId(personId);
        given(service.findById(personId)).willReturn(personDTO);

        mockMvc.perform(get("/api/v1/people/" + personId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(equalTo(personId), Long.class))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        then(service).should().findById(personId);
    }

    @DisplayName("Find By Id - PersonNotFoundException")
    @Test
    void test_given_person_id_when_not_found_then_return_not_found_status() throws Exception {
        var personId = 1L;
        given(service.findById(personId)).willThrow(PersonNotFoundException.class);

        mockMvc.perform(get("/api/v1/people/" + personId))
               .andExpect(status().isNotFound());

        then(service).should().findById(personId);
    }

    @DisplayName("Update By Id")
    @Test
    void test_given_person_id_and_person_content_then_return_ok_status() throws Exception {
        var personId = 1L;
        var personDTO = createFakeDTO();
        personDTO.setId(personId);
        var expectedSuccessMessage = updateExpectedMessageResponse(personDTO.getId());
        given(service.updateById(personId, personDTO)).willReturn(expectedSuccessMessage);

        mockMvc.perform(put("/api/v1/people/" + personId).contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(personDTO)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message", is(expectedSuccessMessage.getMessage())))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        then(service).should().updateById(personId, personDTO);
    }

    @DisplayName("Update By Id - PersonNotFoundException")
    @Test
    void test_given_person_id_and_person_content_when_not_found_then_return_not_found_status() throws Exception {
        var personId = 1L;
        var personDTO = createFakeDTO();
        personDTO.setId(personId);
        given(service.updateById(personId, personDTO)).willThrow(PersonNotFoundException.class);

        mockMvc.perform(put("/api/v1/people/" + personId).contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(personDTO)))
               .andExpect(status().isNotFound());

        then(service).should().updateById(personId, personDTO);
    }

    @DisplayName("Delete")
    @Test
    void test_given_person_id_then_return_no_content_status() throws Exception {
        var personId = 1L;
        doNothing().when(service).delete(personId);

        mockMvc.perform(delete("/api/v1/people/" + personId))
               .andExpect(status().isNoContent());

        then(service).should().delete(personId);
    }

    @DisplayName("Delete - PersonNotFoundException")
    @Test
    void test_given_person_id_when_not_found_then_return_not_found() throws Exception {
        var personId = 1L;
        doThrow(PersonNotFoundException.class).when(service).delete(personId);

        mockMvc.perform(delete("/api/v1/people/" + personId))
               .andExpect(status().isNotFound());

        then(service).should().delete(personId);
    }
}
