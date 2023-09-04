package com.algafood.algafoodapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algafood.algafoodapi.modelo.Cliente;
import com.algafood.algafoodapi.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {

    @Autowired
    AtivacaoClienteService ativaCliente;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Cliente cliente = new Cliente("aldeir", "dide123@silva", "343434234");
        ativaCliente.ativar(cliente);
        return "Hello!";
    }
}