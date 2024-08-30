package com.spring.first_spring_project.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;

    private LocalDate dob;

    @Transient
    private Integer age;

    public Student(){}

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public static Student fromJson(final HashMap<String,Object> json){
        return new Gson().fromJson(new Gson().toJson(json), Student.class);
    }
    public static Student fromJson(final String json){
        return new Gson().fromJson(json, Student.class);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public HashMap<String,Object> toJson(){
        final Gson gson = new Gson();
       final String jsonString = gson.toJson(this, Student.class);
       return gson.fromJson(jsonString, new TypeToken<HashMap<String,Object>>(){}.getType());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, Student.class);
    }
}
