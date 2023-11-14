package com.ojt.blog.bl.dto;

import com.ojt.blog.persistence.entity.Person;
import com.ojt.blog.web.form.PersonForm;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    Long id;
    String name;
    public PersonDTO(PersonForm personForm){
        setId(personForm.getId());
        setName(personForm.getName());
    }
    public PersonDTO(Person person){
        setId(person.getId());
        setName(person.getName());

    }
}
