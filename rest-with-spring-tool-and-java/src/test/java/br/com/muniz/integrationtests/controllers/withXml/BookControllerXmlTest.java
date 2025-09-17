package br.com.muniz.integrationtests.controllers.withXml;

import br.com.muniz.config.TestConig;
import br.com.muniz.integrationtests.dto.BookDTOV1;
import br.com.muniz.integrationtests.integrationTestsContainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerXmlTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static XmlMapper mapper;
    private static BookDTOV1 book;

    @BeforeAll
    static void setUp(){
        mapper = new XmlMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        book = new BookDTOV1();
    }

    @Test
    @Order(1)
    void createdTest() throws JsonProcessingException {
        mockBook();

        specification = new RequestSpecBuilder()
                .addHeader(TestConig.HEADER_PARAM_ORIGIN, TestConig.ORIGIN_MUNIZ)
                .setBasePath("/api/book/v1")
                .setPort(TestConig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .body(book)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        BookDTOV1 createdBook = mapper.readValue(content, BookDTOV1.class);
        book = createdBook;

        assertNotNull(createdBook.getId());

        assertEquals("isaica", createdBook.getAuthorName());
        assertEquals("a volta dos que nao foram", createdBook.getTitle());
        assertEquals(2.0, createdBook.getPrice());
    }

    private void mockBook(){
        book.setAuthorName("isaica");
        book.setTitle("a volta dos que nao foram");
        book.setPrice(2.0);
    }
}
