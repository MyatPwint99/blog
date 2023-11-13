package com.ojt.blog.web.controller;

import com.ojt.blog.bl.dto.PersonDTO;
import com.ojt.blog.bl.service.PersonService;
import com.ojt.blog.persistence.entity.Person;
import com.ojt.blog.web.form.PersonForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
//    @Qualifier("myPersonService")
    PersonService personService;

    /***
     * <h2>Register Page</h2>
     * <p>show form for person registration</p>
     *
     *
     * @param model
     * @return String
     */
    @GetMapping("/create")
    public String createPage(Model model){
        model.addAttribute("person",new PersonForm());
        return "person/create";
    }

    /***
     *<h2>Register Person</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @param model
     * @param person
     * @return String
     */
    @PostMapping("/create")
    public String createPerson(Model model, @ModelAttribute PersonForm person){
        //PersonDTO ----> Service
        PersonDTO personDTO = new PersonDTO(person);
        personService.registerPerson(personDTO);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String personList(Model model,
                             @RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "5") Integer size){
//        List<PersonDTO> personDTOList = new ArrayList<>();
//        personDTOList.add(new PersonDTO("person one"));
//        personDTOList.add(new PersonDTO("person two"));
//        personDTOList.add(new PersonDTO("person three"));
//        personDTOList.add(new PersonDTO("person four"));
//        personDTOList.add(new PersonDTO("person five"));
//        model.addAttribute("persons",personDTOList);

//        List<PersonDTO> persons = personService.getPersonList();
//        model.addAttribute("persons",persons);

        Pageable pageable = PageRequest.of(page,size);
        Page<Person> persons = personService.getPageablePersonList(pageable);
        model.addAttribute("persons",persons);
        return "person/list";
    }
}
