package com.ojt.blog.web.form;


import com.ojt.blog.bl.dto.PersonDTO;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonForm {

    Long id;
    @NotEmpty(message = "********* User's Name must not be Empty *********")
    String name;
    Date createdAt;
    String password;

    public PersonForm(PersonDTO personDTO){
        setId(personDTO.getId());
        setName(personDTO.getName());
        setCreatedAt(personDTO.getCreatedAt());
        setPassword(personDTO.getPassword());

    }

}
