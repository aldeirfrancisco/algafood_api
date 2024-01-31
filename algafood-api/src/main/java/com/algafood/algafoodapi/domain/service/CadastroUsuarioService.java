package com.algafood.algafoodapi.domain.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.algafoodapi.domain.Exception.CidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.Exception.EntidadeEmUsoException;
import com.algafood.algafoodapi.domain.Exception.NegocioException;
import com.algafood.algafoodapi.domain.Exception.UsuarioNaoEncontradoException;

import com.algafood.algafoodapi.domain.model.Usuario;
import com.algafood.algafoodapi.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

    private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void excluir(Long cidadeId) {
        try {
            usuarioRepository.deleteById(cidadeId);
            usuarioRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Usuario buscarOuFalhar(Long ususarioId) {

        return usuarioRepository.findById(ususarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(ususarioId));

    }
}
