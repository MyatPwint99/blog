package com.ojt.blog.persistence.entity;

import com.ojt.blog.bl.dto.PersonDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    Date createdAt;
    Date updatedAt;

    public Person(PersonDTO personDTO){
        setId(personDTO.getId());
        setName(personDTO.getName());


    }


}
