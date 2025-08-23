package br.com.muniz.mapper.custom;

import br.com.muniz.data.dto.v2.PersonDTOV2;
import br.com.muniz.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public Person convertDTOtoEntity(PersonDTOV2 person){
        Person entitty = new Person();

        entitty.setFirstName(person.getFirstName());
       // dto.setBirthday(new Date());
        entitty.setAdress(person.getAdress());
        entitty.setGender(person.getGender());
        entitty.setId(person.getId());
        entitty.setLastName(person.getLastName());

        return entitty;
    }

    public PersonDTOV2 convertEntityToDTO(Person person){
        PersonDTOV2 dto = new PersonDTOV2();

        dto.setFirstName(person.getFirstName());
        dto.setBirthday(new Date());
        dto.setAdress(person.getAdress());
        dto.setGender(person.getGender());
        dto.setId(person.getId());
        dto.setLastName(person.getLastName());

        return dto;
    }
}
