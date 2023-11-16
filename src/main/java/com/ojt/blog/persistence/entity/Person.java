package com.ojt.blog.persistence.entity;

import com.ojt.blog.bl.dto.PersonDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Temporal(TemporalType.TIME)
    @CreationTimestamp
    Date createdAt;

    @Temporal(TemporalType.TIME)
    @UpdateTimestamp
    Date updatedAt;

    public Person(PersonDTO personDTO){
        setId(personDTO.getId());
        setName(personDTO.getName());
        setCreatedAt(personDTO.getCreatedAt());
    }


}
