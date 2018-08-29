package com.qa.HelloWord;

import com.qa.HelloWord.exception.ResourceNotFoundException;
import com.qa.HelloWord.model.Person;
import com.qa.HelloWord.repository.PersonRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/api")
@ComponentScan(basePackageClasses = ApplicationController.class)
public class ApplicationController {

    @Autowired
    PersonRepository personRepository;


    @PostMapping("/person")
    public Person addPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }



    @GetMapping("/people")
    @ResponseBody
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }


    @GetMapping("/person/{personId}")
    @ResponseBody
    public Person retrieveAccountById(@PathVariable Long personId) {

       return personRepository.findById(personId).orElseThrow(()-> new ResourceNotFoundException("Person","id",personId));

    }

    @DeleteMapping("/person/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable Long personId) {

        Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person","id",personId));

        personRepository.delete(person);

        return ResponseEntity.ok().build();

    }


    @PutMapping("/person/{personId}")
    public Person updatePerson(@PathVariable Long personId, @Valid @RequestBody Person personDetails) {

        Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person","id",personId));

        person.setName(personDetails.getName());
        person.setAddress(personDetails.getAddress());
        person.setAge(personDetails.getAge());

        Person updatedPerson = personRepository.save(person);

        return updatedPerson;

    }


    @GetMapping("/loop")
    @ResponseBody
    public Collection<String> sayHello() {
        return IntStream.range(0, 10)
                .mapToObj(i -> "Hello number " + i)
                .collect(Collectors.toList());
    }



}
