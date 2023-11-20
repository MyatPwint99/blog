package com.ojt.blog.bl.service.person;

import com.ojt.blog.bl.dto.PersonDTO;
import com.ojt.blog.persistence.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    public Person getPersonById(long id) ;

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

//    Page<Person> findPaginated(int pageNo,int pageSize);
    Page<Person> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);

    void updatePerson( Long id,String name);

    void deleteById(long id);


     public List<Person> listAll(String keyword);

    Person getByPersonName(String username);
}
