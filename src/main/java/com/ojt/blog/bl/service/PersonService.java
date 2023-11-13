package com.ojt.blog.bl.service;

import com.ojt.blog.bl.dto.PersonDTO;
import com.ojt.blog.persistence.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    /***
     *<h2>Register Person</h2>
     * <p>Person Name is modified to UpperCase And Register Person to DB</p>
     *
     * @param personDTO
     * return void
     */
         void registerPerson(PersonDTO personDTO);

     /***
     *<h2>Get Person List</h2>
     * <p>Getting Person Data from DB</p>
     *
     * return void
     */
    List<PersonDTO> getPersonList();

    /***
     *<h2>Get Person List</h2>
     * <p>Getting Person Data from DB</p>
     *
     * return void
     */
    Page<Person> getPageablePersonList(Pageable pageable);
}
