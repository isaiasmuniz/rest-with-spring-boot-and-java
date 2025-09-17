package br.com.muniz.services;

import br.com.muniz.controllers.BookController;
import br.com.muniz.data.dto.booksdto.BooksDTOV1;
import br.com.muniz.exception.RequiredObjectIsNullException;
import br.com.muniz.exception.resourceNotFoundException;
import br.com.muniz.model.Books;
import br.com.muniz.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.muniz.mapper.ObjectMapper.parseObject;
import static br.com.muniz.mapper.ObjectMapper.parselistObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksService {

    @Autowired
    BooksRepository repository;

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PagedResourcesAssembler assembler;


    public BooksDTOV1 findById(Long id){
        logger.info("find one book");

        var entity = repository.findById(id).orElseThrow(() -> new resourceNotFoundException("No records found for this id"));
        var dto = parseObject(entity, BooksDTOV1.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BooksDTOV1 create(BooksDTOV1 book){
        logger.info("create a book");

        if (book == null) throw new RequiredObjectIsNullException();


        var entity = parseObject(book, Books.class);
        var dto = parseObject(repository.save(entity), BooksDTOV1.class);
        addHateoasLinks(dto);
        return dto;
    }
    public BooksDTOV1 update(BooksDTOV1 book){
        logger.info("updating data book");

        if (book == null) throw new RequiredObjectIsNullException();

        Books entity = repository.findById(book.getId()).orElseThrow(() -> new resourceNotFoundException("No records found for this id"));
        entity.setAuthorName(book.getAuthorName());
        entity.setLaunchData(book.getLaunchData());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        var dto = parseObject(repository.save(entity), BooksDTOV1.class);
        addHateoasLinks(dto);
        return dto;
    }
    public void  delete(Long id){
        logger.info("deleting one book");

        Books entity = repository.findById(id).orElseThrow(() -> new resourceNotFoundException("No records founds for this id"));
        repository.delete(entity);
    }

    public PagedModel<EntityModel<BooksDTOV1>> findAll(Pageable pageable){
        logger.info("find all books");

        var book = repository.findAll(pageable);
        var bookWithLinks = book.map(books ->{
            var dto = parseObject(books, BooksDTOV1.class);
            addHateoasLinks(dto);
            return dto;
        });
        Link findAllLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class)
                .findAll(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        String.valueOf(pageable.getSort())
                )).withSelfRel();

        return assembler.toModel(bookWithLinks, findAllLink);
    }

    private void addHateoasLinks(BooksDTOV1 dto){
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll(1, 15, "asc")).withRel("find all").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
