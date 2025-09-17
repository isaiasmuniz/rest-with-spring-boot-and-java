package br.com.muniz.integrationtests.controllers.withjson;

import br.com.muniz.config.TestConig;
import br.com.muniz.integrationtests.dto.BookDTOV1;
import br.com.muniz.integrationtests.dto.wrapper.json.WrapperBookDTO;
import br.com.muniz.integrationtests.integrationTestsContainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerTestV1 extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static BookDTOV1 book;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        book = new BookDTOV1();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Test
    @Order(1)
    void createTest() throws JsonProcessingException {
        mockBook();

        specification = new RequestSpecBuilder()
                .addHeader(TestConig.HEADER_PARAM_ORIGIN, TestConig.ORIGIN_MUNIZ)
                .setBasePath("/api/book/v1")
                .setPort(TestConig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        BookDTOV1 createdBook = objectMapper.readValue(content, BookDTOV1.class);
        book = createdBook;

        assertNotNull(createdBook.getId());

        assertEquals("isaica", createdBook.getAuthorName());
        assertEquals("a volta dos que nao foram", createdBook.getTitle());
        assertEquals(5.0, createdBook.getPrice());
    }

    @Test
    @Order(2)
    void findByIdTest() throws JsonProcessingException {


        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParams("id", book.getId())
                .when()
                .get("{id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        BookDTOV1 createdBook = objectMapper.readValue(content, BookDTOV1.class);
        book = createdBook;

        assertNotNull(createdBook.getId());

        assertEquals("isaica", createdBook.getAuthorName());
        assertEquals("a volta dos que nao foram", createdBook.getTitle());
        assertEquals(5.0, createdBook.getPrice());
    }

    @Test
    @Order(3)
    void update() throws JsonProcessingException {
        book.setAuthorName("poc");
        book.setTitle("as trancas do rei careca");

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        BookDTOV1 createdBook = objectMapper.readValue(content, BookDTOV1.class);
        book = createdBook;

        assertNotNull(createdBook.getId());

        assertEquals("poc", createdBook.getAuthorName());
        assertEquals("as trancas do rei careca", createdBook.getTitle());
        assertEquals(5.0, createdBook.getPrice());
    }

    @Test
    @Order(4)
    void delete() {

        given(specification)
                .pathParams("id", book.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(5)
    void findAll() throws JsonProcessingException {
        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("page", 0, "size", 5, "direction", "asc")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        WrapperBookDTO wrapper = objectMapper.readValue(content, WrapperBookDTO.class);
        List<BookDTOV1> books = wrapper.getembedded().getBook();

        BookDTOV1 b1 = books.get(0);
        book = b1;

        assertNotNull(b1.getId());

        assertEquals("Aguinaldo Aragon Fernandes e Vladimir Ferraz de Abreu", b1.getAuthorName());
        assertEquals("Implantando a governança de TI", b1.getTitle());
        assertEquals(54.0, b1.getPrice());

        BookDTOV1 b2 = books.get(2);
        book = b2;

        assertNotNull(b2.getId());

        assertEquals("Crockford", b2.getAuthorName());
        assertEquals("JavaScript", b2.getTitle());
        assertEquals(67.0, b2.getPrice());

        BookDTOV1 b7 = books.get(3);
        book = b7;

        assertNotNull(b7.getId());

        assertEquals("Eric Evans", b7.getAuthorName());
        assertEquals("Domain Driven Design", b7.getTitle());
        assertEquals(92.0, b7.getPrice());
    }

    private void mockBook(){
        book.setAuthorName("isaica");
        book.setTitle("a volta dos que nao foram");
        book.setPrice(5.0);
    }
}