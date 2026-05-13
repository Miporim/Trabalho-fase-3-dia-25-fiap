package com.example.smartcollector.service;

import com.example.smartcollector.model.CentroColeta;
import com.example.smartcollector.repository.CentroColetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CentroColetaService {

    private final CentroColetaRepository centroColetaRepository;

    public CentroColetaService(CentroColetaRepository centroColetaRepository) {
        this.centroColetaRepository = centroColetaRepository;
    }

    public List<CentroColeta> listarTodos() {
        return centroColetaRepository.findAll();
    }

    public Optional<CentroColeta> buscarPorId(Long id) {
        return centroColetaRepository.findById(id);
    }

    public CentroColeta salvar(CentroColeta centroColeta) {
        return centroColetaRepository.save(centroColeta);
    }

    public CentroColeta atualizar(Long id, CentroColeta centroColetaAtualizado) {
        CentroColeta centroExistente = centroColetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro de coleta não encontrado"));

        centroExistente.setEndereco(centroColetaAtualizado.getEndereco());
        centroExistente.setVolumeItensTotal(centroColetaAtualizado.getVolumeItensTotal());
        centroExistente.setVolumeItensAtual(centroColetaAtualizado.getVolumeItensAtual());

        return centroColetaRepository.save(centroExistente);
    }

    public void deletar(Long id) {
        if (!centroColetaRepository.existsById(id)) {
            throw new RuntimeException("Centro de coleta não encontrado");
        }

        centroColetaRepository.deleteById(id);
    }
}