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
@Primary
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    /***
     *<h2>Register Person</h2>
     * <p>Person Name is modified to UpperCase And Register Person to DB</p>
     *
     * @param personDTO
     * return void
     */
    @Override
    public void registerPerson(PersonDTO personDTO) {
        //Person (entity) ----> Repository
        String modifiedWord = personDTO.getName().toUpperCase();
        personDTO.setName(modifiedWord);
        Person person = new Person(personDTO);
        personRepository.save(person);
        System.out.println("Query Ok "+person.getName());
    }
    /***
     *<h2>Get Person List</h2>
     * <p>Getting Person Data from DB</p>
     *
     * return void
     */
    public List<PersonDTO> getPersonList(){
        List<Person> personList = personRepository.findAll();
        List<PersonDTO> personDTOList = personList.stream().map(person -> new PersonDTO(person)).toList();
        return personDTOList;
    }

    /***
     *<h2>Get Person List</h2>
     * <p>Getting Person Data from DB</p>
     *
     * return void
     */
    @Override
    public Page<Person> getPageablePersonList(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
}
