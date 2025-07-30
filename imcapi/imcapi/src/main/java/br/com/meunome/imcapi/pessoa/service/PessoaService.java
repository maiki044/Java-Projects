package br.com.meunome.imcapi.pessoa.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.meunome.imcapi.pessoa.domain.Pessoa;
import br.com.meunome.imcapi.pessoa.repository.PessoaRepository;
import br.com.meunome.imcapi.pessoa.request.PessoaCreateRequest;
import br.com.meunome.imcapi.pessoa.request.PessoaUpdateNomeRequest;
import br.com.meunome.imcapi.pessoa.request.PessoaUpdateRequest;
import br.com.meunome.imcapi.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa listOne() {
        return new Pessoa("1", "nometeste", "123", new Timestamp(0), "BANANA");
    }

    public Pessoa findById(final String id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
    }

    public Pessoa findByCpf(final String cpf) {
        return pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
    }

    public ArrayList<Pessoa> listAll() {
        final ArrayList<Pessoa> arrayDePessoa = (ArrayList<Pessoa>) pessoaRepository.findAll();

        return arrayDePessoa;
    }

    public Pessoa create(final PessoaCreateRequest pessoaCreateRequest) {
        ValidationUtil.validate(pessoaCreateRequest);

        final Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaCreateRequest.getNome());
        pessoa.setCpf(pessoaCreateRequest.getCpf());
        pessoa.setDataNascimento(pessoaCreateRequest.getDataNascimento());
        pessoa.setSexoBiologico(pessoaCreateRequest.getSexoBiologico());

        return pessoaRepository.save(pessoa);
    }

    public void delete(final String id) {
        pessoaRepository.delete(findById(id));
        if (pessoaRepository.existsById(id))
            new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Pessoa não foi deletada");
    }

    public void deleteByCpf(final String cpf) {
        delete(findByCpf(cpf).getId());
    }

    public Pessoa update(final String id, final PessoaUpdateRequest pessoaUpdateRequest) {
        ValidationUtil.validate(pessoaUpdateRequest);

        final Pessoa pessoa = findById(id);

        pessoa.setNome(pessoaUpdateRequest.getNome());
        pessoa.setCpf(pessoaUpdateRequest.getCpf());
        pessoa.setDataNascimento(pessoaUpdateRequest.getDataNascimento());
        pessoa.setSexoBiologico(pessoaUpdateRequest.getSexoBiologico());

        return pessoaRepository.save(pessoa);
    }

    public Pessoa updateByCpf(final String cpf, PessoaUpdateRequest pessoaUpdateRequest) {
        return update(findByCpf(cpf).getId(), pessoaUpdateRequest);
    }

    public Pessoa updateNome(final String id, final PessoaUpdateNomeRequest pessoaUpdateNomeRequest) {
        ValidationUtil.validate(pessoaUpdateNomeRequest);

        final Pessoa pessoa = findById(id);

        pessoa.setNome(pessoaUpdateNomeRequest.getNome());

        return pessoaRepository.save(pessoa);
    }

    public Pessoa updateNomeByCpf(final String cpf, final PessoaUpdateNomeRequest pessoaUpdateNomeRequest) {
        return updateNome(findByCpf(cpf).getId(), pessoaUpdateNomeRequest);
    }
}
