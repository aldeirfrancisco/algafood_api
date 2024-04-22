package com.algafood.algafoodapi.domain.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.domain.filtro.VendaDiariaFilter;
import com.algafood.algafoodapi.domain.model.dto.VendaDiaria;

@Component
public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffSet);
}
