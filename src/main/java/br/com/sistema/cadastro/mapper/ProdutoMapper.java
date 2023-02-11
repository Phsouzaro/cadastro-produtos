package br.com.sistema.cadastro.mapper;

import br.com.sistema.cadastro.controller.request.ProdutoRequest;
import br.com.sistema.cadastro.controller.response.ProdutoDetalhadoResponse;
import br.com.sistema.cadastro.controller.response.ProdutoSimplesResponse;
import br.com.sistema.cadastro.domain.Produto;

import java.math.BigDecimal;

public class ProdutoMapper {
    public static Produto toProdutoDomain(ProdutoRequest request) {
        return Produto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .valor(new BigDecimal(request.getValor()))
                .quantidade(request.getQuantidade())
                .build();
    }

    public static ProdutoSimplesResponse toSimpleProdutoResponse(Produto domain){
        return ProdutoSimplesResponse.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .build();
    }

    public static ProdutoDetalhadoResponse toProdutoDetalhadoResponse(Produto produto) {
        return ProdutoDetalhadoResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .quantidade(produto.getQuantidade())
                .build();
    }
}
