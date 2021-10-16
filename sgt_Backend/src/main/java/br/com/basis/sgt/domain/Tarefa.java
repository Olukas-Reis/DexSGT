package br.com.basis.sgt.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "tarefa")
@Getter
@Setter
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data_inicial")
    private Date data_incial;

    @Column(name = "data_prevista")
    private Date data_prevista;

    @Column(name = "data_final")
    private Date data_final;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status")
    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tarefa")
    List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Responsavel responsavel;

}
