package com.example.smartcollector.service;

import com.example.smartcollector.model.Catador;
import com.example.smartcollector.model.Usuario;
import com.example.smartcollector.repository.CatadorRepository;
import com.example.smartcollector.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatadorService {

    private final CatadorRepository catadorRepository;
    private final UsuarioRepository usuarioRepository;

    public CatadorService(CatadorRepository catadorRepository, UsuarioRepository usuarioRepository) {
        this.catadorRepository = catadorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Catador> listarTodos() {
        return catadorRepository.findAll();
    }

    public Optional<Catador> buscarPorId(Long id) {
        return catadorRepository.findById(id);
    }

    public Catador salvar(Catador catador) {
        Long usuarioId = catador.getUsuario().getId();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        catador.setUsuario(usuario);

        return catadorRepository.save(catador);
    }

    public Catador atualizar(Long id, Catador catadorAtualizado) {
        Catador catadorExistente = catadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catador não encontrado"));

        catadorExistente.setCapacidadeVolumeTotal(catadorAtualizado.getCapacidadeVolumeTotal());

        return catadorRepository.save(catadorExistente);
    }

    public void deletar(Long id) {
        if (!catadorRepository.existsById(id)) {
            throw new RuntimeException("Catador não encontrado");
        }

        catadorRepository.deleteById(id);
    }
}