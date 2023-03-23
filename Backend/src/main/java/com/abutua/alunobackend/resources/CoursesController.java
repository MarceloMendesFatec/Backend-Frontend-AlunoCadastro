package com.abutua.alunobackend.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abutua.alunobackend.models.Courses;


@RestController // Define que essa classe é um controlador REST.
@CrossOrigin // Permite solicitações de origens diferentes (Cross-Origin Resource Sharing).

public class CoursesController {

    // Cria uma lista de objetos Courses e inicializa a lista com três objetos.
    private List<Courses> objCourse = Arrays.asList(
            new Courses(1, "Java"),
            new Courses(2, "Angular"),
            new Courses(1, "Python"));

    // Define um método que recebe solicitações HTTP GET no caminho "/courses".
    // O método retorna a lista de objetos Courses.
    @GetMapping("courses")
    public List<Courses> getCourses() {
        return objCourse;
    }
}
