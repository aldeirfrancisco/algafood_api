package com.algafood.algafoodapi.api.asswmbler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;
import com.algafood.algafoodapi.api.controller.FormaPagamentoController;
import com.algafood.algafoodapi.api.model.dtooutput.FormaPagamentoDTO;
import com.algafood.algafoodapi.core.security.AlgaSecurit;
import com.algafood.algafoodapi.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler
        extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoDTO> {

    public FormaPagamentoModelAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoDTO.class);
    }

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurit algaSecurity;

    @Override
    public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
        FormaPagamentoDTO formaPagamentoModel = createModelWithId(formaPagamento.getId(), formaPagamento);

        modelMapper.map(formaPagamento, formaPagamentoModel);

        if (algaSecurity.podeConsultarFormasPagamento()) {
            formaPagamentoModel.add(algaLinks.linkToFormasPagamento("formasPagamento"));
        }

        return formaPagamentoModel;
    }

    @Override
    public CollectionModel<FormaPagamentoDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        CollectionModel<FormaPagamentoDTO> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarFormasPagamento()) {
            collectionModel.add(algaLinks.linkToFormasPagamento());
        }

        return collectionModel;
    }
}
