package hu.unideb.inf.bootstrap.controller;

import hu.unideb.inf.bootstrap.model.Person;
import hu.unideb.inf.bootstrap.model.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    PersonRepository personRepository;

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/persons")
    public String getAllPersons(Model model){
        List<Person> personList = personRepository.findAll();
        model.addAttribute("personList", personList);
        return "persons";
    }

    @GetMapping("/persons/new")
    public String newPerson(Model model){
        model.addAttribute("newPerson", new Person());
        model.addAttribute("pageTitle", "Add new person");
        return "newPersonForm";
    }

    @GetMapping("/persons/edit/{id}")
    public String editPerson(@PathVariable Long id, Model model){
        model.addAttribute("newPerson", personRepository.findById(id));
        model.addAttribute("pageTitle", "Edit person");
        return "newPersonForm";
    }

    @PostMapping("/persons/save")
    public String savePerson(Person newPerson, RedirectAttributes rd){
        personRepository.save(newPerson);
        rd.addFlashAttribute("message", "Saved");
        return "redirect:/persons";
    }

    @GetMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable Long id, RedirectAttributes rd){
        personRepository.deleteById(id);
        rd.addFlashAttribute("message", "Deleted");
        return "redirect:/persons";
    }
}
