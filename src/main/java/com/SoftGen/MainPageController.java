package com.SoftGen;

import com.SoftGen.domain.Person;
import com.SoftGen.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainPageController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<Person> persons = personRepository.findAll();

        model.put("persons",persons);

        return "index";
    }

    @PostMapping({"/","/add","/pidFilter","/nameFilter","/surnameFilter"})
    public String add(@RequestParam String pid,
                            @RequestParam String name,
                            @RequestParam String surname,
                            Map<String, Object> model){
        Person person = new Person(pid,name,surname);
        personRepository.save(person);
        Iterable<Person> persons = personRepository.findAll();
        model.put("persons",persons);
        return "index";
    }

    @GetMapping("/pidFilter")
    public String pidFilter(@RequestParam(required=false) String pid,
                         Map<String, Object> model)
    {
        Iterable<Person> persons;
        if (pid != null && !pid.isEmpty()) {
            if ("none".equals(pid)) {
                persons = personRepository.findByPid("");
            } else {
                persons = personRepository.findByPid(pid);
            }
        }
        else {
            persons = personRepository.findAll();
        }
        model.put("persons", persons);
        return "index";
    }

    @GetMapping("/nameFilter")
    public String nameFilter(@RequestParam(required=false) String name,
                         Map<String, Object> model)
    {
        Iterable<Person> persons;
        if (name != null && !name.isEmpty()) {
            if ("none".equals(name)) {
                persons = personRepository.findByName("");
            } else {
                persons = personRepository.findByName(name);
            }
        }
        else {
            persons = personRepository.findAll();
        }
        model.put("persons", persons);
        return "index";
    }


    @GetMapping("/surnameFilter")
    public String surnameFilter(@RequestParam(required=false) String surname,
                             Map<String, Object> model)
    {
        Iterable<Person> persons;
        if (surname != null && !surname.isEmpty()) {
            if ("none".equals(surname)) {
                persons = personRepository.findBySurname("");
            } else {
                persons = personRepository.findBySurname(surname);
            }
        }
        else {
            persons = personRepository.findAll();
        }
        model.put("persons", persons);
        return "index";
    }

    @PostMapping("/deleteAll")
    public String deleteAll(){
        personRepository.deleteAll();


        return "index";
    }
}
