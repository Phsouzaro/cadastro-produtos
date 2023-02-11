package br.com.sistema.cadastro.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotBlank
    private String valor;

    @NotNull
    private int quantidade;
}
