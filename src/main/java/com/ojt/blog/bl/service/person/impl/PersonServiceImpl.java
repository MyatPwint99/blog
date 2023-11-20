package com.ojt.blog.bl.service.person.impl;

import com.ojt.blog.bl.dto.PersonDTO;
import com.ojt.blog.bl.service.person.PersonService;
import com.ojt.blog.persistence.entity.Person;
import com.ojt.blog.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    @Override
    public Page<Person> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);
        return this.personRepository.findAll(pageable);
    }

//    @Override
//    public Page<Person> findPaginated(int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
//        return this.personRepository.findAll(pageable);
//    }

    @Override
    public void updatePerson( Long id,String name) {
        personRepository.updatePersonByQuery(id,name);
    }

    @Override
    public void deleteById(long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> listAll(String keyword) {
        if(keyword != null){
            return personRepository.findAll(keyword);
        }
        return personRepository.findAll();
    }

    @Override
    public Person getByPersonName(String username) {
        return personRepository.findByName(username);
    }

    @Override
    public Person getPersonById(long id) {
        Optional<Person> optional = personRepository.findById(id);
        Person person = null;
        if(optional.isPresent()){
            person = optional.get();
        }else {
            throw new RuntimeException("Person not Found for id :: "+id);
        }
        return person;
    }
}
