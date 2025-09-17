package br.com.muniz.integrationtests.controllers.withjson;

import br.com.muniz.config.TestConig;
import br.com.muniz.integrationtests.dto.BookDTOV1;
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

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerJsonTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static BookDTOV1 book;

    @BeforeAll
    static void setUp(){
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

    private void mockBook(){
        book.setAuthorName("isaica");
        book.setTitle("a volta dos que nao foram");
        book.setPrice(5.0);
    }
}
