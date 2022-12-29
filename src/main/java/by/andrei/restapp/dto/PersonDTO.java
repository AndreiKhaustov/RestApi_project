package by.andrei.restapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class PersonDTO {

    @NotEmpty(message = "name shouldn't be empty")
    private String name;


    @Min(value = 2, message = "Should be between 2 and 100")
    @Max(value = 100, message = "Should be between 2 and 100")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonDTO() {
    }
}
