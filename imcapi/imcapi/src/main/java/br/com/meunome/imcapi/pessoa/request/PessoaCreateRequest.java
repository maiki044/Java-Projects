package br.com.meunome.imcapi.pessoa.request;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PessoaCreateRequest {

    @NotBlank(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser nulo")
    String nome;

    @NotBlank(message = "CPF não pode ser vazio")
    @NotNull(message = "CPF não pode ser nulo")
    @Length(min = 11, max = 11, message = "CPF precisa ter 11 caracteres e ser enviado sem mascara")
    String cpf;

    Timestamp dataNascimento;

    String sexoBiologico;

}