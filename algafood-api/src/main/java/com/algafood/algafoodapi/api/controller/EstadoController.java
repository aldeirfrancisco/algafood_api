package com.algafood.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.algafoodapi.domain.model.Estado;
import com.algafood.algafoodapi.domain.repository.EstadoRepository;

@RestController
@RequestMapping("/Estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    private List<Estado> listar() {
        return estadoRepository.listar();
    }
}
