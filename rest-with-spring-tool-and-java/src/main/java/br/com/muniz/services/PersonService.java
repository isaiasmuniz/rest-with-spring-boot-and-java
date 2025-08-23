package br.com.muniz.services;


import br.com.muniz.data.dto.v1.PersonDTOV1;
import br.com.muniz.data.dto.v2.PersonDTOV2;
import br.com.muniz.exception.resourceNotFoundException;
import br.com.muniz.mapper.custom.PersonMapper;
import br.com.muniz.model.Person;
import br.com.muniz.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.muniz.mapper.ObjectMapper.*;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public List<PersonDTOV1> findAll(){
        logger.info("find all people");
        return parselistObject(repository.findAll(), PersonDTOV1.class);
    }

    public PersonDTOV1 findById(Long id){
        logger.info("find one person");
        var entity = repository.findById(id).orElseThrow(() -> new resourceNotFoundException("No records found for this id"));
        return parseObject(entity, PersonDTOV1.class);
    }

    public PersonDTOV1 create(PersonDTOV1 person){
        logger.info("creating one person");
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTOV1.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){
        logger.info("creating one person v2");
        var entity = converter.convertDTOtoEntity(person);

        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTOV1 update(PersonDTOV1 person){
        logger.info("updating one person");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new resourceNotFoundException("No records found for this id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTOV1.class);
    }

    public void delete(Long id){

        logger.info("deleting one person");
        Person entity = repository.findById(id).orElseThrow(() -> new resourceNotFoundException("No records found for this id"));
        repository.delete(entity);
    }

}
