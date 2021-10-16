package br.com.basis.sgt.web.rest;

import br.com.basis.sgt.service.ResponsavelService;
import br.com.basis.sgt.service.dto.ResponsavelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
@RequiredArgsConstructor

public class ResponsavelResource {

    private final ResponsavelService responsavelService;

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>>ObterTodos(@RequestParam(value = "nome", required = false) String nome) {
    return new ResponseEntity<>(responsavelService.ObterTodos(nome), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponsavelDTO>CriarResposavel(@RequestBody ResponsavelDTO responsavel){
    return ResponseEntity.ok(responsavelService.Salvar(responsavel));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO>ObterPorId(@PathVariable("id") Long id){
    return new ResponseEntity<>(responsavelService.ObterPotId(id), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void>DeletarPorId(@PathVariable("id") Long id){
    responsavelService.Deletar(id);
    return new ResponseEntity<>(HttpStatus.OK);
    }


    }
