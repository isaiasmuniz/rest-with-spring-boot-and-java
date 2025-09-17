package br.com.muniz.services;


import br.com.muniz.controllers.PersonController;
import br.com.muniz.data.dto.persondto.PersonDTOV1;
import br.com.muniz.exception.RequiredObjectIsNullException;
import br.com.muniz.exception.resourceNotFoundException;
import br.com.muniz.mapper.custom.PersonMapper;
import br.com.muniz.model.Person;
import br.com.muniz.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import static br.com.muniz.mapper.ObjectMapper.parseObject;
import static br.com.muniz.mapper.ObjectMapper.parselistObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    @Autowired
    PagedResourcesAssembler assembler; //se erro, usar generics PagedResourcesAssembler<BookDTO>

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public PagedModel<EntityModel<PersonDTOV1>> findAll(Pageable pageable){
        logger.info("find all people");

        var people = repository.findAll(pageable);
        var peoploWithLinks = people.map(person -> {
            var dto = parseObject(person, PersonDTOV1.class);
            addHateoasLinks(dto);
            return dto;
        });

        Link findAllLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class)
                .findAll(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        String.valueOf(pageable
                                .getSort()))).withSelfRel();
        return assembler.toModel(peoploWithLinks, findAllLink);
    }

    public PagedModel<EntityModel<PersonDTOV1>> findByName(String firstName, Pageable pageable){
        logger.info("find people by name");

        var people = repository.findPeopleByName(firstName, pageable);
        var peoploWithLinks = people.map(person -> {
            var dto = parseObject(person, PersonDTOV1.class);
            addHateoasLinks(dto);
            return dto;
        });

        Link findAllLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class)
                .findAll(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        String.valueOf(pageable
                                .getSort()))).withSelfRel();
        return assembler.toModel(peoploWithLinks, findAllLink);
    }

    public PersonDTOV1 findById(Long id){
        logger.info("find one person");
        
        var entity = repository.findById(id).orElseThrow(() -> new resourceNotFoundException("No records found for this id"));
        var dto = parseObject(entity, PersonDTOV1.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTOV1 create(PersonDTOV1 person){

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("creating one person");
        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTOV1.class);
        addHateoasLinks(dto);
        return dto;
    }

//    public PersonDTOV2 createV2(PersonDTOV2 person){
//        logger.info("creating one person v2");
//        var entity = converter.convertDTOtoEntity(person);
//
//
//
//        return converter.convertEntityToDTO(repository.save(entity));
//    }

    public PersonDTOV1 update(PersonDTOV1 person){

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("updating one person");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new resourceNotFoundException("No records found for this id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity), PersonDTOV1.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){

        logger.info("deleting one person");
        Person entity = repository.findById(id).orElseThrow(() -> new resourceNotFoundException("No records found for this id"));
        repository.delete(entity);
    }

    @Transactional
    public PersonDTOV1 disablePerson(Long id){

        logger.info("Disabling one person");
        repository.findById(id).orElseThrow(() -> new resourceNotFoundException("No records found for this id"));
        repository.disablePerson(id);

        var entity = repository.findById(id).get();
        var dto = parseObject(entity, PersonDTOV1.class);
        addHateoasLinks(dto);
        return dto;
    }

    private void addHateoasLinks(PersonDTOV1 dto) {
        dto.add(linkTo(methodOn(PersonController.class).findBiId(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll(1, 15, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }


}
