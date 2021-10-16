package br.com.basis.sgt.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {

    private Long id;
    private String titulo;
    private Date data_inicial;
    private Date data_prevista;
    private Date data_final;
    private String descricao;
    private String status;
    private List<ComentarioDTO> comentarios;
}
