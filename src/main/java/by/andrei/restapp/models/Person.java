package by.andrei.restapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name="Person")
public class Person {
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
@Column(name = "name")
@NotEmpty(message = "name shouldn't be empty")
    private String name;

@Column(name ="age")
@Min(value = 2, message = "Should be between 2 and 100")
@Max(value = 100, message = "Should be between 2 and 100")
    private int age;
@Column(name="created_time")
private LocalDateTime created_time;

    public LocalDateTime getCreated_time() {
        return created_time;
    }

    public void setCreated_time(LocalDateTime created_time) {
        this.created_time = created_time;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
