package br.com.muniz.services;

import br.com.muniz.data.dto.booksdto.BooksDTOV1;
import br.com.muniz.model.Books;
import br.com.muniz.repository.BooksRepository;
import br.com.muniz.unitetests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BooksServiceTest {

    MockBook input;

    @InjectMocks
    private BooksService service;

    @Mock
    BooksRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Books books = input.mockEntity(1);
        books.setId(1L);
        when(repository.findById(1l)).thenReturn(Optional.of(books));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("api/book/v1/1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
            && link.getHref().endsWith("api/book/v1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
            && link.getHref().endsWith("api/book/v1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
            && link.getHref().endsWith("api/book/v1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
            && link.getHref().endsWith("api/book/v1/1")));

        assertEquals("author name Test1", result.getAuthorName());
        assertEquals("title Test1", result.getTitle());
        assertEquals(1.0, result.getPrice());

    }

    @Test
    void create() {
        Books books = input.mockEntity(1);
        Books persistence = books;
        persistence.setId(1L);
        BooksDTOV1 dto = input.mockDTO(1);

        when(repository.save(books)).thenReturn(persistence);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("api/book/v1/1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("api/book/v1/1")));

        assertEquals("author name Test1", result.getAuthorName());
        assertEquals("title Test1", result.getTitle());
        assertEquals(1.0, result.getPrice());

    }

    @Test
    void update() {

        Books books = input.mockEntity(1);
        Books persistence = books;
        persistence.setId(1L);

        BooksDTOV1 dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(books));
        when(repository.save(books)).thenReturn(persistence);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("api/book/v1/1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("api/book/v1/1")));

        assertEquals("author name Test1", result.getAuthorName());
        assertEquals("title Test1", result.getTitle());
        assertEquals(1.0, result.getPrice());
    }

    @Test
    void delete() {

        Books books = input.mockEntity(1);
        books.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(books));

        service.delete(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Books.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Books> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BooksDTOV1> books = service.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());

        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("api/book/v1/1")));
        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("api/book/v1/1")));

        assertEquals("author name Test1", bookOne.getAuthorName());
        assertEquals("title Test1", bookOne.getTitle());
        assertEquals(1.0, bookOne.getPrice());

        var bookSeven = books.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());
        assertNotNull(bookSeven.getLinks());

        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("api/book/v1/7")));
        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("api/book/v1")));
        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("api/book/v1/7")));

        assertEquals("author name Test7", bookSeven.getAuthorName());
        assertEquals("title Test7", bookSeven.getTitle());
        assertEquals(7.0, bookSeven.getPrice());

    }
    }
