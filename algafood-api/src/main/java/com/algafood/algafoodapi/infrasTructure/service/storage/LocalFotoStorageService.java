package com.algafood.algafoodapi.infrasTructure.service.storage;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.FileCopyUtils;

import com.algafood.algafoodapi.core.storage.StorageProperties;
import com.algafood.algafoodapi.domain.exception.StorageException;
import com.algafood.algafoodapi.domain.service.FotoStorageService;

//@Service
public class LocalFotoStorageService implements FotoStorageService {

    // @Value("${algafood.storage.local.diretorio-fotos}")
    // private Path diretorioFotos;

    @Autowired
    private StorageProperties storagePropeties;

    @Override
    public void armazenar(NovaFoto novaFoto) {

        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(),
                    Files.newOutputStream(arquivoPath));
        } catch (Exception e) {

            throw new StorageException("Não foi possível armazenar arquivo. ", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);
            Files.deleteIfExists(arquivoPath);
        } catch (IOException e) {
            throw new StorageException("Não foi possível excluir arquivo. ", e);
        }
    }

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        try {
            Path arquiPath = getArquivoPath(nomeArquivo);

            FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
                    .inputStream(Files.newInputStream(arquiPath))
                    .build();

            return fotoRecuperada;
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return storagePropeties.getLocal().getDiretorioFotos().resolve(Path.of(nomeArquivo));
    }

}