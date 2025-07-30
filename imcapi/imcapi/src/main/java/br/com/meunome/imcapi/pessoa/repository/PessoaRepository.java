package br.com.meunome.imcapi.pessoa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.meunome.imcapi.pessoa.domain.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, String> {

    Optional<Pessoa> findByCpf(String cpf);

    Iterable<Pessoa> findAllByNomeContainsIgnoreCaseAndCpfContainsIgnoreCaseAndId(String nome, String cpf, String id);
}