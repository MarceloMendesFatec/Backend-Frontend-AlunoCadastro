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

@RestController // Define que essa classe é um controlador REST.
@CrossOrigin // Permite solicitações de origens diferentes (Cross-Origin Resource Sharing).

public class StudentController {

    // Cria uma lista de objetos Student vazia.
    private List<Student> objStudents = new ArrayList<>();

    // Cria uma lista de objetos Student e inicializa a lista com três objetos.
    private List<Student> objStudentsTable = Arrays.asList(
            new Student(1, "marcelo da Silva nascimento", "joao.silva@gmail.com", "+55 11 99999-9999","Java","Manhã"),
            new Student(2, "Maria Oliveira Santos", "maria.oliveira@gmail.com", "+55 21 98765-4321","Python","Tarde"),
            new Student(3, "Luiz Gonzaga Souza", "luiz.gonzaga@gmail.com", "+55 31 99999-8888", "Angular","Noite"));

            
    /*
     * Define um método que recebe solicitações HTTP GET no caminho
     * "/students/{id}".
     * O método procura na lista objStudents um objeto Student com o ID especificado
     * na solicitação.
     * Se encontrar, retorna o objeto Student encontrado.
     * Caso contrário, retorna um erro HTTP 404 Not Found.
     */



    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        List<Student> combinedList = new ArrayList<Student>();
        combinedList.addAll(objStudents);
        combinedList.addAll(objStudentsTable);
        Student student = combinedList.stream()
                .filter(p -> p.getId() == id)
                .findFirst() // é usado para obter o primeiro objeto encontrado (ou um objeto vazio se nenhum objeto for encontrado).
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return ResponseEntity.ok(student);
    }

    /*
     * Define um método que recebe solicitações HTTP GET no caminho "/students".
     * O método retorna a lista de objetos Student combinada das listas objStudents
     * e objStudentsTable.
     */
    @GetMapping("students")
    public List<Student> getCourses() {
        List<Student> combinedList = new ArrayList<Student>();
        combinedList.addAll(objStudents);
        combinedList.addAll(objStudentsTable);
        return combinedList;
    }

    /*
     * Define um método que recebe solicitações HTTP POST no caminho "/students".
     * O método adiciona um objeto Student à lista objStudents e retorna um HTTP 201
     * Created com o objeto Student criado.
     */
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
