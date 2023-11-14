package com.ojt.blog.web.form;


import com.ojt.blog.bl.dto.PersonDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonForm {

    Long id;
    @NotEmpty(message = "********* Person's Name must not be Empty *********")
    String name;

    public PersonForm(PersonDTO personDTO){
        setId(personDTO.getId());
        setName(personDTO.getName());

    }

}
