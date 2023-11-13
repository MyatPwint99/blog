package com.ojt.blog.bl.service.impl;

import com.ojt.blog.bl.dto.PersonDTO;
import com.ojt.blog.bl.service.PersonService;
import com.ojt.blog.persistence.entity.Person;
import com.ojt.blog.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Qualifier("myPersonService")
public class PersonServiceImpl2024 implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Override
    public void registerPerson(PersonDTO personDTO) {
        //Person (entity) ----> Repository
        String modifiedWord = personDTO.getName().toLowerCase();
        personDTO.setName(modifiedWord);
        Person person = new Person(personDTO);
        personRepository.save(person);
    }

    @Override
    public List<PersonDTO> getPersonList() {
        return null;
    }

    @Override
    public Page<Person> getPageablePersonList(Pageable pageable) {
        return null;
    }

//
}
