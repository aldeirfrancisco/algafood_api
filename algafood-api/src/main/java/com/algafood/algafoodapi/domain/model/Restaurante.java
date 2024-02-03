package com.algafood.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algafood.algafoodapi.core.validation.Groups;

import com.algafood.algafoodapi.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descrcaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // o notBlank está agrupa em um grupo de validação, vai validar todos que
    // temGroups.CadastroRestaurante.class e iguinorar os outros.
    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @PositiveOrZero // (message = "{TaxaFrete.invalida}")
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    /*
     * todos que termina com toOne usa a estrategia eager loading p
     * padrão"carregamento antecipado" toda associacão vai ser carregada junto com a
     * entidade. Gerando varios select.
     * obs: quando a associação é eager pode ser que ele faz um join ou vario select
     * separados
     */
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = Boolean.TRUE;

    private Boolean aberto = Boolean.FALSE;

    @CreationTimestamp
    @JoinColumn(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @JoinColumn(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    public void ativar() {
        this.setAtivo(true);
    }

    public void inativar() {
        this.setAtivo(false);
    }

    public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {

        return this.getFormasPagamento().add(formaPagamento);
    }

    public boolean removeFormaPagamento(FormaPagamento formaPagamento) {
        return this.getFormasPagamento().remove(formaPagamento);
    }

    public void abrir() {
        setAberto(true);
    }

    public void fechar() {
        setAberto(false);
    }
}