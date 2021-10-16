package br.com.basis.sgt.service;

import br.com.basis.sgt.domain.Responsavel;
import br.com.basis.sgt.repository.ResponsavelRepository;
import br.com.basis.sgt.service.dto.ResponsavelDTO;
import br.com.basis.sgt.service.error.ResponsavelNaoEncontrado;
import br.com.basis.sgt.service.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor

public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelMapper responsavelMapper;

    public List<ResponsavelDTO> ObterTodos (String nome){
        if(nome != null && nome.isEmpty()) {
    return responsavelMapper.toDto(responsavelRepository.EncontrarTodosPorNome(nome));
    }
    return responsavelMapper.toDto(responsavelRepository.findAll());
    }

    public ResponsavelDTO ObterPotId (Long id) {
    Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(ResponsavelNaoEncontrado::new);
    return responsavelMapper.toDto(responsavel);

    }

    public  ResponsavelDTO Salvar(ResponsavelDTO responsavel) {
    Responsavel Responsavel = responsavelMapper.toEntity(responsavel);
    Responsavel ResponsavelSalvar = responsavelRepository.save(Responsavel);
    return responsavelMapper.toDto(ResponsavelSalvar);

    }

    public void Deletar (Long id){
        responsavelRepository.deleteById(id);
    }

    }
