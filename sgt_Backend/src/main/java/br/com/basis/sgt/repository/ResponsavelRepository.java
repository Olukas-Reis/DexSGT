package br.com.basis.sgt.repository;


import br.com.basis.sgt.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResponsavelRepository  extends JpaRepository <Responsavel, Long> { // Classe responsável por realizar as consultas no banco

    List<Responsavel>findAllByNome(String nome);//Encontra todas os responsaveis por nome


    @Query("from Responsavel where nome like %:nome%")
    List<Responsavel>EncontrarTodosPorNome(@Param("nome") String nome);


}
/**
 * Encontra todos as tarefas por Título
 * Também captura tarefas que contenham somente parte dos títulos
 * Isso ocorre graças ao uso do LIKE juntamente com os %%
 * @param nome
 * @return
 */