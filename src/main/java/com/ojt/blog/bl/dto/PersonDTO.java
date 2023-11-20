package com.ojt.blog.bl.dto;

import com.ojt.blog.persistence.entity.Person;
import com.ojt.blog.web.form.PersonForm;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    Long id;
    String name;
    Date createdAt;
    String password;

    public PersonDTO(PersonForm personForm){
        setId(personForm.getId());
        setName(personForm.getName());
        setCreatedAt(personForm.getCreatedAt());
        setPassword(personForm.getPassword());
    }
    public PersonDTO(Person person){
        setId(person.getId());
        setName(person.getName());
        setCreatedAt(person.getCreatedAt());
        setPassword(person.getPassword());
    }
}
