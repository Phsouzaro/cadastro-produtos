package br.com.sistema.cadastro.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoSimplesResponse {

    private Long id;

    private String nome;
}
