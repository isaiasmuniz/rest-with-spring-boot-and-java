package br.com.muniz.integrationtests.controllers.withYml;

import br.com.muniz.config.TestConig;
import br.com.muniz.integrationtests.controllers.withYml.converter.YamlConverter;
import br.com.muniz.integrationtests.dto.PersonDTOV1;
import br.com.muniz.integrationtests.dto.wrapper.xml.PageModelPerson;
import br.com.muniz.integrationtests.integrationTestsContainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerYmlTest extends AbstractIntegrationTest{

    private static RequestSpecification specification;
    private static YamlConverter objectMapper;
    private static PersonDTOV1 person;

    @BeforeAll
    static void setUp() {
        objectMapper = new YamlConverter();
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

        var createdPerson = given().config(
                RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT))
        ).spec(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .body(person, objectMapper)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PersonDTOV1.class, objectMapper);


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

        var createdPerson = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig()
                                        .encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT))
                ).spec(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .pathParams("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PersonDTOV1.class, objectMapper);


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

        var createdPerson = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig()
                                        .encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT))
                ).spec(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .body(person, objectMapper)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PersonDTOV1.class, objectMapper);


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

        var createdPerson = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig()
                                        .encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT))
                ).spec(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .pathParam("id", person.getId())
                .when()
                .patch("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PersonDTOV1.class, objectMapper);


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

        var response = given(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .queryParams("page", 3, "size", 12, "direction", "asc")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PageModelPerson.class, objectMapper);

        List<PersonDTOV1> perons = response.getContent();

        PersonDTOV1 personOne = perons.get(0);
        person = personOne;


        assertNotNull(personOne.getId());

        assertEquals("Allianora", personOne.getFirstName());
        assertEquals("Whardley", personOne.getLastName());
        assertEquals("Room 847", personOne.getAdress());
        assertEquals("Female", personOne.getGender());
        assertFalse(personOne.getEnabled());

        PersonDTOV1 personSeven = perons.get(2);
        person = personSeven;

        assertNotNull(personSeven.getId());

        assertEquals("Amalle", personSeven.getFirstName());
        assertEquals("Dundredge", personSeven.getLastName());
        assertEquals("PO Box 16019", personSeven.getAdress());
        assertEquals("Female", personSeven.getGender());
        assertTrue(personSeven.getEnabled());
    }

    private void mockPerson() {
        person.setFirstName("isaica");
        person.setLastName("muniz");
        person.setAdress("Marrocos");
        person.setGender("male");
        person.setEnabled(true);
    }
}
