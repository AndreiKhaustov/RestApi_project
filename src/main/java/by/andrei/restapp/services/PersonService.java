package by.andrei.restapp.services;

import by.andrei.restapp.dto.PersonDTO;
import by.andrei.restapp.models.Person;
import by.andrei.restapp.repositories.PersonRepository;
import by.andrei.restapp.utils.PersonNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
@Autowired
    public PersonService(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
    this.modelMapper = modelMapper;
}
    public List<Person> findAll(){
    return personRepository.findAll();
    }
    public Person findById(int id){
    return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }
    @Transactional
    public void savePerson(Person person){
    personRepository.save(person);
    }

    public Person convertToPerson(PersonDTO personDTO) {
    Person person = modelMapper.map(personDTO, Person.class);
    enrichPerson(person);
    return person;
}
   public Person enrichPerson(Person person) {
    person.setCreated_time(LocalDateTime.now());
    return person;
   }
   public PersonDTO convertToPersonDTO(Person person){
    return modelMapper.map(person, PersonDTO.class);
   }
}
