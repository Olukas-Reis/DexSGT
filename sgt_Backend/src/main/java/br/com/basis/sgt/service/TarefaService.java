package br.com.basis.sgt.service;

import br.com.basis.sgt.domain.Tarefa;
import br.com.basis.sgt.repository.TarefaRepository;
import br.com.basis.sgt.service.dto.TarefaDTO;
import br.com.basis.sgt.service.error.TarefaNaoEncontradaException;
import br.com.basis.sgt.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public List<TarefaDTO> obterTodos(String titulo) {
        // Caso o título seja passado, realiza o filtro por título
        if (titulo != null && !titulo.isEmpty()) {
            return tarefaMapper.toDto(tarefaRepository.EncontarTodosPorTitulo(titulo));
        }
        // Caso não, retorna todos as tarefas no banco
        return tarefaMapper.toDto(tarefaRepository.findAll());
    }

    public TarefaDTO obterPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(TarefaNaoEncontradaException::new);
        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDTO salvar(TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return tarefaMapper.toDto(tarefaSalva);
    }

    public void deletarPorId(Long id) {
        tarefaRepository.deleteById(id);
    }

}
