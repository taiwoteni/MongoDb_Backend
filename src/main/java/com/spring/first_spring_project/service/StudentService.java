package com.spring.first_spring_project.service;

import com.spring.first_spring_project.models.Student;
import com.spring.first_spring_project.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student createStudent(final Student student){

        return studentRepository.save(student);
    }

    public Optional<Student> getOneStudent(final Long id){

        return studentRepository.findById(id);
    }

    public Optional<Student> getOneStudent(final String email){

        return studentRepository.findStudentByEmail(email);
    }

    public Optional<Student> updateStudent(final Student student){

        final Optional<Student> searchStudent = getOneStudent(student.getId());
        if(searchStudent.isPresent()){
            final Student newStudent = searchStudent.get();
            newStudent.setName(student.getName());
//            newStudent.setEmail(student.getEmail());

            if(student.getDob()!=null){
                newStudent.setDob(student.getDob());
            }

            studentRepository.save(newStudent);
            return Optional.of(newStudent);
        }
        return Optional.empty();
    }



    public void deleteStudent(final Long id){
        studentRepository.deleteById(id);
    }
}
