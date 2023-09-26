package com.algafood.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.algafoodapi.domain.repository.CozinhaRepository;
import com.algafood.algafoodapi.domain.model.Cozinha;

//@ResponseBody garante que a resposta do metodo vai ser o corpo da requeiscao
//@Controller
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    // @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{cozinhaId}") // @PathVariable("cozinhaId") long id
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Cozinha conzinha = cozinhaRepository.buscar(cozinhaId);

        if (conzinha != null) {
            return ResponseEntity.ok(conzinha);
        }

        return ResponseEntity
                .notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Cozinha cozinha) {
        cozinhaRepository.adicionar(cozinha);
    }

}
