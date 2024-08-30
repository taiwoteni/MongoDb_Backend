package com.spring.first_spring_project.controllers;

import com.spring.first_spring_project.models.Student;
import com.spring.first_spring_project.models.ApiResponse;
import com.spring.first_spring_project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping(path = "getStudents")
    public ResponseEntity<ApiResponse> getStudents(){

        final List<Student> students = studentService.getAllStudents();
        final ApiResponse response = new ApiResponse();
        response.setMessage("Got all students successfully");
        response.setData(students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "createStudent")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody final Student student){


        final Optional<Student> studentExists = studentService.getOneStudent(student.getEmail());

        if(studentExists.isEmpty()){
            final Student addedStudent = studentService.createStudent(student);
            final ApiResponse response = new ApiResponse("Created Student Successfully", addedStudent);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        final ApiResponse response = new ApiResponse("Email already exists", null);


        return new ResponseEntity<>(response,HttpStatus.resolve(404));
    }

    @GetMapping(path = "getOneStudent/{id}")
    public ResponseEntity<ApiResponse> getStudent(@PathVariable Long id){
        final Optional<Student> studentById = studentService.getOneStudent(id);

        if(studentById.isPresent()){
            final Student foundStudent = studentById.get();
            final ApiResponse response = new ApiResponse();
            response.setMessage("Found User Successfully");
            response.setData(foundStudent);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        final ApiResponse response = new ApiResponse();
        response.setMessage("User not found");
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @GetMapping(path = "getStudentByEmail")
    public ResponseEntity<ApiResponse> getStudentByEmail(@RequestBody Student student){
        final Optional<Student> studentById = studentService.getOneStudent(student.getEmail());

        if(studentById.isPresent()){
            final Student foundStudent = studentById.get();
            final ApiResponse response = new ApiResponse();
            response.setMessage("Found User Successfully");
            response.setData(foundStudent);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        final ApiResponse response = new ApiResponse();
        response.setMessage("User not found");
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @PatchMapping(path = "updateStudent/{id}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable Long id, @RequestBody Student student){

        // First we have to check if the user exists
        final Optional<Student> searchedStudent = studentService.getOneStudent(id);
        if(searchedStudent.isEmpty()){
            final ApiResponse response = new ApiResponse("User doesn't exist", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


        student.setId(id);
        // If the student exists, we want to update such user
        final Optional<Student> savedStudent = studentService.updateStudent(student);
        if(savedStudent.isEmpty()){
            final ApiResponse response = new ApiResponse("Unable to update user", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        final ApiResponse response = new ApiResponse("Updated user successfully", savedStudent.get());
        return new ResponseEntity<>(response, HttpStatus.OK);


    }


}
