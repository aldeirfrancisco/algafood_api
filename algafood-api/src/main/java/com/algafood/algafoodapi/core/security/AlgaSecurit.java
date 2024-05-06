package com.algafood.algafoodapi.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.domain.repository.PedidoRepository;
import com.algafood.algafoodapi.domain.repository.RestauranteRepository;

@Component
public class AlgaSecurit {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUsuarioId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("id_usuario");
    }

    public boolean gerenciaRestaurante(Long restauranteId) {
        if (restauranteId == null) {
            return false;
        }

        return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
    }

    public boolean gerenciaRestauranteDoPedido(Long pedidoId) {
        return pedidoRepository.isPedidoGerenciadoPor(pedidoId, getUsuarioId());
    }

    public boolean usuarioAutenticadoIgual(Long usuarioId) {
        return getUsuarioId() != null && usuarioId != null
                && getUsuarioId().equals(usuarioId);
    }
}
