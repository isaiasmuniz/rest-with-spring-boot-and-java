package br.com.muniz.controllers;

import br.com.muniz.data.dto.booksdto.BooksDTOV1;
import br.com.muniz.services.BooksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "books", description = "endpoints for mapping books")
public class BookController implements br.com.muniz.controllers.docs.BookControllerDocs {

    @Autowired
    private BooksService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<BooksDTOV1> findAll(){ return service.findAll();}


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public BooksDTOV1 findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public BooksDTOV1 create(@RequestBody BooksDTOV1 book){
        return service.create(book);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public BooksDTOV1 update(@RequestBody BooksDTOV1 book){
        return service.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
         service.delete(id);
         return ResponseEntity.noContent().build();
    }
}
