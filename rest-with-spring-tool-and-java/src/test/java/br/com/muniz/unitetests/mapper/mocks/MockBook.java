package br.com.muniz.unitetests.mapper.mocks;

import br.com.muniz.data.dto.booksdto.BooksDTOV1;
import br.com.muniz.model.Books;

import java.util.ArrayList;
import java.util.List;

public class MockBook {

    public Books mockEntity() {
        return mockEntity(0);
    }

    public BooksDTOV1 mockDTO() {
        return mockDTO(0);
    }

    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksDTOV1> mockDTOList() {
        List<BooksDTOV1> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }

    public Books mockEntity(Integer number) {
        Books books = new Books();
        books.setAuthorName("author name Test" + number);
        books.setTitle("title Test" + number);
        books.setId(number.longValue());
        books.setPrice(number);
        return books;
    }

    public BooksDTOV1 mockDTO(Integer number) {
        BooksDTOV1 books = new BooksDTOV1();
        books.setTitle("title Test" + number);
        books.setAuthorName("author name Test" + number);
        books.setId(number.longValue());
        books.setPrice(number);
        return books;
    }
}
