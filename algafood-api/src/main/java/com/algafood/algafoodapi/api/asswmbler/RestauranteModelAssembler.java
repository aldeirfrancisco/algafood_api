package com.algafood.algafoodapi.api.asswmbler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;
import com.algafood.algafoodapi.api.controller.RestauranteController;
import com.algafood.algafoodapi.api.model.dtooutput.RestauranteDTO;
import com.algafood.algafoodapi.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

        public RestauranteModelAssembler() {
                super(RestauranteController.class, RestauranteDTO.class);
        }

        @Autowired
        private ModelMapper modelMapper;
        @Autowired
        private AlgaLinks algaLinks;

        @Override
        public RestauranteDTO toModel(Restaurante restaurante) {
                RestauranteDTO restauranteModel = createModelWithId(restaurante.getId(), restaurante);
                modelMapper.map(restaurante, restauranteModel);
                restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));

                if (restaurante.ativacaoPermitida()) {
                        restauranteModel.add(
                                        algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
                }

                if (restaurante.inativacaoPermitida()) {
                        restauranteModel.add(
                                        algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
                }

                if (restaurante.aberturaPermitida()) {
                        restauranteModel.add(
                                        algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
                }

                if (restaurante.fechamentoPermitido()) {
                        restauranteModel.add(
                                        algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
                }

                restauranteModel.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));

                restauranteModel.getCozinha().add(
                                algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

                if (restauranteModel.getEndereco() != null
                                && restauranteModel.getEndereco().getCidade() != null) {
                        restauranteModel.getEndereco().getCidade().add(
                                        algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
                }

                restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(),
                                "formas-pagamento"));

                restauranteModel.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(),
                                "responsaveis"));

                return restauranteModel;
        }

        @Override
        public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
                return super.toCollectionModel(entities)
                                .add(algaLinks.linkToRestaurantes());
        }

}
