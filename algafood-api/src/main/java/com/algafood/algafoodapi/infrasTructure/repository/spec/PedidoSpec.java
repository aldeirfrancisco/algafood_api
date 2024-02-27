package com.algafood.algafoodapi.infrasTructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.algafood.algafoodapi.domain.model.Pedido;
import com.algafood.algafoodapi.domain.repository.filter.PedidoFiltro;

public class PedidoSpec {

    public static Specification<Pedido> usandoFiltro(PedidoFiltro filtro) {
        return (root, query, builder) -> {
            if (Pedido.class.equals(query.getResultType())) {
                root.fetch("restaurante").fetch("cozinha"); // resolvendo n + 1
                root.fetch("cliente");
            }

            var predicate = new ArrayList<Predicate>();
            if (filtro.getClienteId() != null) {
                predicate.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }
            if (filtro.getRestauranteId() != null) {
                predicate.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoFim() != null) {
                predicate.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }

            return builder.and(predicate.toArray(new Predicate[0]));
        };
    }
}
