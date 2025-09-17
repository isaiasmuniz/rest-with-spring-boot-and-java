package br.com.muniz.integrationtests.controllers.withjson;

import br.com.muniz.config.TestConig;
import br.com.muniz.integrationtests.dto.PersonDTOV1;
import br.com.muniz.integrationtests.integrationTestsContainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerJsonTest extends AbstractIntegrationTest{

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static PersonDTOV1 person;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        person = new PersonDTOV1();
    }

    @Test
    @Order(1)
    void createTest() throws JsonProcessingException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConig.HEADER_PARAM_ORIGIN, TestConig.ORIGIN_MUNIZ)
                .setBasePath("/api/person/v1")
                .setPort(TestConig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();


        PersonDTOV1 createdPerson = objectMapper.readValue(content, PersonDTOV1.class);
        person = createdPerson;

        assertNotNull(createdPerson.getId());

        assertEquals("isaica", createdPerson.getFirstName());
        assertEquals("muniz", createdPerson.getLastName());
        assertEquals("Marrocos", createdPerson.getAdress());
        assertEquals("male", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());
    }

    @Test
    @Order(2)
    void findBiIdTest() throws JsonProcessingException {

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParams("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();


        PersonDTOV1 createdPerson = objectMapper.readValue(content, PersonDTOV1.class);
        person = createdPerson;

        assertNotNull(createdPerson.getId());

        assertEquals("isaica", createdPerson.getFirstName());
        assertEquals("muniz", createdPerson.getLastName());
        assertEquals("Marrocos", createdPerson.getAdress());
        assertEquals("male", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());


    }

    @Test
    @Order(3)
    void updateTest() throws JsonProcessingException {

        person.setFirstName("isaias");
        person.setAdress("fortal city");

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(person)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();


        PersonDTOV1 createdPerson = objectMapper.readValue(content, PersonDTOV1.class);
        person = createdPerson;

        assertNotNull(createdPerson.getId());

        assertEquals("isaias", createdPerson.getFirstName());
        assertEquals("muniz", createdPerson.getLastName());
        assertEquals("fortal city", createdPerson.getAdress());
        assertEquals("male", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());


    }

    @Test
    @Order(4)
    void disableTest() throws JsonProcessingException {

        person.setEnabled(false);

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", person.getId())
                .when()
                .patch("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();


        PersonDTOV1 createdPerson = objectMapper.readValue(content, PersonDTOV1.class);
        person = createdPerson;

        assertNotNull(createdPerson.getId());

        assertFalse(createdPerson.getEnabled());


    }

    @Test
    @Order(5)
    void deleteTest(){

        given(specification)
                .pathParam("id", person.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);

    }

    @Test
    @Order(6)
    void findAllTest() throws JsonProcessingException {

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        List<PersonDTOV1> perons = objectMapper.readValue(content, new TypeReference<List<PersonDTOV1>>() {});

        PersonDTOV1 personOne = perons.get(0);
        person = personOne;


        assertNotNull(personOne.getId());

        assertEquals("Pedro", personOne.getFirstName());
        assertEquals("Matador", personOne.getLastName());
        assertEquals("Parangaba - Fortaleza - CE - 6669", personOne.getAdress());
        assertEquals("female", personOne.getGender());
        assertTrue(personOne.getEnabled());

        PersonDTOV1 personSeven = perons.get(2);
        person = personSeven;

        assertNotNull(personSeven.getId());

        assertEquals("Braian", personSeven.getFirstName());
        assertEquals("Temoteo", personSeven.getLastName());
        assertEquals("Maranguape - Fortaleza - CE", personSeven.getAdress());
        assertEquals("male", personSeven.getGender());
        assertTrue(personSeven.getEnabled());

        PersonDTOV1 personSix = perons.get(6);

        assertNotNull(personSix.getId());

        assertEquals("Xaulin", personSix.getFirstName());
        assertEquals("Matador de porco", personSix.getLastName());
        assertEquals("Maranguape - Fortaleza - CE", personSix.getAdress());
        assertEquals("male", personSix.getGender());
        assertTrue(personSix.getEnabled());

    }

    private void mockPerson() {
        person.setFirstName("isaica");
        person.setLastName("muniz");
        person.setAdress("Marrocos");
        person.setGender("male");
        person.setEnabled(true);
    }
}
