package com.ojt.blog.web.controller;

import com.ojt.blog.bl.dto.PersonDTO;
import com.ojt.blog.bl.service.PersonService;
import com.ojt.blog.persistence.entity.Person;
import com.ojt.blog.web.form.PersonForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
//    @Qualifier("myPersonService")
    PersonService personService;

    @GetMapping("/search")
    public String searchPage(Model model,
                             @Param("keyword") String keyword){
//        String keyword = "HLA HLA";
        List<Person> listPersons = personService.listAll(keyword);
        model.addAttribute("persons",listPersons);
        System.out.println("**************************************OK***************************************");


        return "person/search";
    }
    //    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable int pageNo,Model model){
//        int pageSize = 8;
//
//        Page<Person> page = personService.findPaginated(pageNo,pageSize);
//        List<Person> persons = page.getContent();
//
//        model.addAttribute("currentPage",pageNo);
//        model.addAttribute("totalPages",page.getTotalPages());
//        model.addAttribute("totalItems",page.getTotalElements());
//        model.addAttribute("persons",persons);
//
//        return "person/list";
//    }

    @GetMapping("/list")
    public String viewHomePage(Model model){

        return findPaginated(1,"name","asc",model);
    }






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
    public String createPerson(Model model, @Valid @ModelAttribute("person") PersonForm person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("person",person);
            return "person/create";
        }
        //PersonDTO ----> Service
        PersonDTO personDTO = new PersonDTO(person);
        personService.registerPerson(personDTO);
        return "redirect:/person/list";
    }

//    @GetMapping("/list")
//    public String personList(Model model,
//                             @RequestParam(defaultValue = "0") Integer page,
//                             @RequestParam(defaultValue = "5") Integer size){
////        List<PersonDTO> personDTOList = new ArrayList<>();
////        personDTOList.add(new PersonDTO("person one"));
////        personDTOList.add(new PersonDTO("person two"));
////        personDTOList.add(new PersonDTO("person three"));
////        personDTOList.add(new PersonDTO("person four"));
////        personDTOList.add(new PersonDTO("person five"));
////        model.addAttribute("persons",personDTOList);
//
////        List<PersonDTO> persons = personService.getPersonList();
////        model.addAttribute("persons",persons);
//
//        Pageable pageable = PageRequest.of(page,size);
//        Page<Person> persons = personService.getPageablePersonList(pageable);
//        model.addAttribute("persons",persons);
//        return "person/list";
//    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,
                                @RequestParam String sortField,
                                @RequestParam String sortDir,
                                Model model){
        int pageSize = 6;

        Page<Person> page = personService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Person> persons = page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("persons",persons);

        return "person/list";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable long id,Model model){
        Person person = personService.getPersonById(id);
        model.addAttribute("person",person);
        return "person/update";
    }

    @PostMapping("/update")
    public String updatePerson(@ModelAttribute Person person,Model model){
        personService.updatePerson(person);
        return "redirect:/person/list";
    }
    @GetMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable long id,Model model){
        personService.deleteById(id);
        return "redirect:/person/list";
    }
}
