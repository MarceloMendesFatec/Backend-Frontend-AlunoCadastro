package com.abutua.alunobackend.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.alunobackend.models.Student;

@RestController
@CrossOrigin

public class StudentController {

    private List<Student> objStudents = new ArrayList<>();

    private List<Student> objStudentsTable = Arrays.asList(
        new Student(1,"marcelo da Silva nascimento","joao.silva@gmail.com","+55 11 99999-9999",456,3),
         new Student(2,"Maria Oliveira Santos", "maria.oliveira@gmail.com", "+55 21 98765-4321", 123, 2),
         new Student(3,"Luiz Gonzaga Souza", "luiz.gonzaga@gmail.com", "+55 31 99999-8888", 789, 1)
    );

    /* ==================================================== */
    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {

        Student student = objStudents.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return ResponseEntity.ok(student);
    }

    /* =========================================================== */
    @GetMapping("students")
    public List<Student> getCourses() {
        List<Student> combinedList = new ArrayList<Student>();
        combinedList.addAll(objStudents);
        combinedList.addAll(objStudentsTable);
        return combinedList;
    }

    /* ============================================================ */

    @PostMapping("students")
    public ResponseEntity<Student> save(@RequestBody Student students) {
        students.setId((objStudents.size() + 1) + 3);
        objStudents.add(students);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(students.getId())
                .toUri();

        return ResponseEntity.created(location).body(students);
    }

}