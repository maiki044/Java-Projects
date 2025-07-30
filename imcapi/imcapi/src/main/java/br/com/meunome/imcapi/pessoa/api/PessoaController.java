package br.com.meunome.imcapi.pessoa.api;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meunome.imcapi.pessoa.domain.Pessoa;
import br.com.meunome.imcapi.pessoa.request.PessoaCreateRequest;
import br.com.meunome.imcapi.pessoa.request.PessoaUpdateNomeRequest;
import br.com.meunome.imcapi.pessoa.request.PessoaUpdateRequest;
import br.com.meunome.imcapi.pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping("/listarUm")
    public ResponseEntity<Pessoa> listOne() {
        return new ResponseEntity<Pessoa>(pessoaService.listOne(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable(value = "id", required = true) final String id) {
        return new ResponseEntity<Pessoa>(pessoaService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> findByCpf(@PathVariable(value = "cpf", required = true) final String cpf) {
        return new ResponseEntity<Pessoa>(pessoaService.findByCpf(cpf), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Pessoa>> listAll() {
        return new ResponseEntity<ArrayList<Pessoa>>(pessoaService.listAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Pessoa> create(@RequestBody(required = true) final PessoaCreateRequest pessoaCreateRequest) {
        return new ResponseEntity<Pessoa>(pessoaService.create(pessoaCreateRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Pessoa> deleteById(@PathVariable(value = "id", required = true) final String id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().header(HttpHeaders.CONTENT_LENGTH, "0").build();
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> deleteByCpf(@PathVariable(value = "cpf", required = true) final String cpf) {
        pessoaService.deleteByCpf(cpf);
        return ResponseEntity.noContent().header(HttpHeaders.CONTENT_LENGTH, "0").build();
    }

    @PatchMapping("/updateNome/id/{id}")
    public ResponseEntity<Pessoa> updateNome(@PathVariable(value = "id", required = true) final String id,
            @RequestBody(required = true) final PessoaUpdateNomeRequest pessoaUpdateNomeRequest) {
        return new ResponseEntity<Pessoa>(pessoaService.updateNome(id, pessoaUpdateNomeRequest), HttpStatus.OK);
    }

    @PatchMapping("/updateNome/cpf/{cpf}")
    public ResponseEntity<Pessoa> updateNomeByCpf(@PathVariable(value = "cpf", required = true) final String cpf,
            @RequestBody(required = true) final PessoaUpdateNomeRequest pessoaUpdateNomeRequest) {
        return new ResponseEntity<Pessoa>(pessoaService.updateNomeByCpf(cpf, pessoaUpdateNomeRequest), HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable(value = "id", required = true) final String id,
            @RequestBody(required = true) final PessoaUpdateRequest pessoaUpdateRequest) {
        return new ResponseEntity<Pessoa>(pessoaService.update(id, pessoaUpdateRequest), HttpStatus.OK);
    }

    @PutMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> updateByCpf(@PathVariable(value = "cpf", required = true) final String cpf,
            @RequestBody(required = true) final PessoaUpdateRequest pessoaUpdateRequest) {
        return new ResponseEntity<Pessoa>(pessoaService.updateByCpf(cpf, pessoaUpdateRequest), HttpStatus.OK);
    }
}
