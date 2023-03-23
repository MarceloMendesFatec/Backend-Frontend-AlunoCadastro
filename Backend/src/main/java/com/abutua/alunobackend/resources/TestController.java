package com.abutua.alunobackend.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    
    @GetMapping("teste")
    public String  getTeste(){
        return  " Frase de efeito";
    }
}
