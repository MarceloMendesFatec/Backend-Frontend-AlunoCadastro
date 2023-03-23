package com.abutua.alunobackend.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abutua.alunobackend.models.Courses;


@RestController
@CrossOrigin

public class CoursesController {
    
    private List<Courses> objCourse = Arrays.asList(
        new Courses(1,"Java"),
        new Courses(2,"Angular"),
        new Courses(1,"Python")
    );

    @GetMapping("courses")
    public List<Courses> getCourses(){
        return objCourse;
    }
}
