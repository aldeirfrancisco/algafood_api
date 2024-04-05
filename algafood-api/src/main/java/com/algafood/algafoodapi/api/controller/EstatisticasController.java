package com.algafood.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.algafoodapi.domain.filtro.VendaDiariaFiltro;
import com.algafood.algafoodapi.domain.model.dto.VendaDiaria;
import com.algafood.algafoodapi.domain.service.VendaQueryService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("vendas-diarias")
    public List<VendaDiaria> consultaVendaDiarias(VendaDiariaFiltro filtro,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
        return vendaQueryService.consultaVendaDiarias(filtro, timeOffSet);
    }

}
