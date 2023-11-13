package com.ojt.blog.bl.dto;

import com.ojt.blog.persistence.entity.Person;
import com.ojt.blog.web.form.PersonForm;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    String name;
    public PersonDTO(PersonForm personForm){
        setName(personForm.getName());
    }
    public PersonDTO(Person person){
        setName(person.getName());

    }
}
