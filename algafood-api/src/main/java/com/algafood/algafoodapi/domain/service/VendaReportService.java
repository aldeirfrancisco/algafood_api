package com.algafood.algafoodapi.domain.service;

import com.algafood.algafoodapi.domain.filtro.VendaDiariaFilter;

public interface VendaReportService {
    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
