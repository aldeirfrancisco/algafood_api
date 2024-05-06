package com.algafood.algafoodapi.core.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.METHOD;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

    public @interface Cozinhas {
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
        @Retention(RetentionPolicy.RUNTIME) // vai ser lida em tempo de execução
        @Target(METHOD) // AONDE VAI SER USADO A Annotation, no caso so método
        public @interface PodeEditar {
        }

        @PreAuthorize("@algaSecurit.podeConsultarCozinhas()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }
    }

    public @interface Restaurantes {

        @PreAuthorize("@algaSecurit.podeGerenciarCadastroRestaurantes()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarCadastro {
        }

        @PreAuthorize("@algaSecurit.podeGerenciarFuncionamentoRestaurantes(#restauranteId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarFuncionamento {
        }

        @PreAuthorize("@algaSecurit.podeConsultarRestaurantes()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }
    }

    public @interface Pedidos {
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
                + "@algaSecurit.usuarioAutenticadoIgual(returnObject.cliente.id) or "
                + "@algaSecurit.gerenciaRestaurante(returnObject.restaurante.id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeBuscar {
        }

        @PreAuthorize("@algaSecurit.podePesquisarPedidos(#filtro.clienteId, #filtro.restauranteId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodePesquisar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeCriar {
        }

        @PreAuthorize("@algaSecurit.podeGerenciarPedidos(#codigoPedido)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarPedidos {
        }
    }

    public @interface FormasPagamento {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar {
        }

        @PreAuthorize("@algaSecurit.podeConsultarFormasPagamento()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }

        public @interface Cidades {

            @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
            @Retention(RetentionPolicy.RUNTIME)
            @Target(METHOD)
            public @interface PodeEditar {
            }

            @PreAuthorize("@algaSecurit.podeConsultarCidades()")
            @Retention(RetentionPolicy.RUNTIME)
            @Target(METHOD)
            public @interface PodeConsultar {
            }

        }

        public @interface Estados {

            @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
            @Retention(RetentionPolicy.RUNTIME)
            @Target(METHOD)
            public @interface PodeEditar {
            }

            @PreAuthorize("@algaSecurit.podeConsultarEstados()")
            @Retention(RetentionPolicy.RUNTIME)
            @Target(METHOD)
            public @interface PodeConsultar {
            }

        }
    }

    public @interface UsuariosGruposPermissoes {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
                + "@algaSecurit.usuarioAutenticadoIgual(#usuarioId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarPropriaSenha {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
                + "@algaSecurit.usuarioAutenticadoIgual(#usuarioId))")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarUsuario {
        }

        @PreAuthorize("@algaSecurit.podeEditarUsuariosGruposPermissoes()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar {
        }

        @PreAuthorize("@algaSecurit.podeConsultarUsuariosGruposPermissoes()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }

    }

    public @interface Estatisticas {

        @PreAuthorize("@algaSecurit.podeConsultarEstatisticas()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }

    }
}
