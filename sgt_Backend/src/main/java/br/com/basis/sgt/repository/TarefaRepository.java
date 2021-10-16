package br.com.basis.sgt.repository;


import br.com.basis.sgt.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> { // Classe responsável por realizar as consultas no banco

    List<Tarefa> findAllByTitulo(String titulo); //Encontra todas as tarefas por Título


    @Query("from Tarefa where titulo like %:titulo%")
    List<Tarefa>EncontarTodosPorTitulo(@Param("titulo") String titulo);


}
/**
 * Encontra todos as tarefas por Título
 * Também captura tarefas que contenham somente parte dos títulos
 * Isso ocorre graças ao uso do LIKE juntamente com os %%
 * @param titulo
 * @return
 */