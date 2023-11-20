package com.ojt.blog.web.controller;

import com.ojt.blog.bl.dto.PersonDTO;
import com.ojt.blog.bl.service.person.PersonService;
import com.ojt.blog.persistence.entity.Person;
import com.ojt.blog.web.form.PersonForm;
import jakarta.validation.Valid;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
//    @Qualifier("myPersonService")
    PersonService personService;

    /***
     *<h2>Search Page</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @param model
     * @param keyword
     * @return String
     */
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
    /***
     *<h2>View Home Page</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @param model
     * @return String
     */
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
    /***
     *<h2>Find Paginated</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @param pageNo
     * @param sortField
     * @param sortDir
     * @param model
     * @return String
     */

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
    /***
     *<h2>Show Form For Update</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable long id,Model model){
        Person person = personService.getPersonById(id);
        model.addAttribute("person",person);
        return "person/update";
    }
    /***
     *<h2>Update Person</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @param id
     * @param name
     * @return String
     */
    @PostMapping("/update")
    public String updatePerson(@Param("id") Long id,@Param("name") String name){
//        System.out.println("***************"+person.getId()+"***************");
//        System.out.println("***************"+person.getName()+"***************");
//        System.out.println("***************"+person.getCreatedAt()+"***************");
//        System.out.println("***************"+person.getUpdatedAt()+"***************");
//        System.out.println("***************"+createdAt+"***************");
        System.out.println("***************"+name+"***************");
        System.out.println("***************"+id+"***************");
        personService.updatePerson(id,name);
        return "redirect:/person/list";
    }
    /***
     *<h2>Delete Person</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable long id,Model model){
        personService.deleteById(id);
        return "redirect:/person/list";
    }

    /***
     *<h2>Person List Excel Export</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @return String
     */
    @GetMapping("/excel-import")
    public String personListExcelExport() throws IOException, InvalidFormatException {
        File file = new File("D:\\BIB\\JAVA\\JAVA LESSONS\\blog2\\src\\main\\resources\\static\\files\\sample-data.xlsx");
        FileInputStream fis = new FileInputStream(file);
        System.out.println("excel import *************");
//        var workbook = new XSSFWorkbook(file.getInputStream());
//        var sh = workbook.createSheet();
//        var r = sh.createRow(0);
//        var c = r.createCell(0);
//        c.setCellValue(135);

        // Excel Import To JAVA
        var workbook = new XSSFWorkbook(file);
        var sheet = workbook.getSheetAt(0);
        Iterator<Row> itr = sheet.iterator();
        while (itr.hasNext()){
            Row row = itr.next();
//            var cellStyle = workbook.createCellStyle();
//            cellStyle.setAlignment();

            Cell cell = row.getCell(0);
            String name = cell.getStringCellValue();

            PersonForm personForm = new PersonForm();
            personForm.setName(name);
            personService.registerPerson(new PersonDTO(personForm));
        }

        return "redirect:/person/list";
    }
}
