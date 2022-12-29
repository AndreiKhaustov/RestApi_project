package by.andrei.restapp.controllers;


import by.andrei.restapp.dto.PersonDTO;
import by.andrei.restapp.models.Person;
import by.andrei.restapp.services.PersonService;
import by.andrei.restapp.utils.PersonErrorResponse;
import by.andrei.restapp.utils.PersonNotCreatedException;
import by.andrei.restapp.utils.PersonNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class JsonController {
 private final PersonService personService;
@Autowired
    public JsonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    public List<PersonDTO> getAllPeople(){
    return personService.findAll().stream().map(personService::convertToPersonDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id")int id){
    return personService.convertToPersonDTO(personService.findById(id));
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
   PersonErrorResponse p = new PersonErrorResponse("person not found", System.currentTimeMillis());
     return new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createPerson(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
        StringBuilder sb = new StringBuilder();
        List<FieldError> errors =bindingResult.getFieldErrors();
        errors.forEach(t -> {
            sb.append(t.getField());
            sb.append(" - ");
            sb.append(t.getDefaultMessage());
            sb.append(";");
                }
        );
        System.out.println(sb.toString());
        throw new PersonNotCreatedException(sb.toString());
    }
    Person person = personService.convertToPerson(personDTO);
    personService.savePerson(person);
    return new ResponseEntity<>(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
        PersonErrorResponse p = new PersonErrorResponse(e.getMessage(), System.currentTimeMillis());
        System.out.println("second time");
        System.out.println(e.getMessage());
        return new ResponseEntity<>(p, HttpStatus.BAD_REQUEST);
    }

}



