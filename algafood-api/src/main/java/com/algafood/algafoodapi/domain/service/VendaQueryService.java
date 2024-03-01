package com.algafood.algafoodapi.domain.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.domain.filtro.VendaDiariaFiltro;
import com.algafood.algafoodapi.domain.model.dto.VendaDiaria;

@Component
public interface VendaQueryService {

    List<VendaDiaria> consultaVendaDiarias(VendaDiariaFiltro filtro, String timeOffSet);
}
