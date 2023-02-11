package br.com.sistema.cadastro.service;


import br.com.sistema.cadastro.controller.request.ProdutoRequest;
import br.com.sistema.cadastro.controller.response.ProdutoDetalhadoResponse;
import br.com.sistema.cadastro.controller.response.ProdutoSimplesResponse;
import br.com.sistema.cadastro.domain.Produto;
import br.com.sistema.cadastro.mapper.ProdutoMapper;
import br.com.sistema.cadastro.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public Page<ProdutoSimplesResponse> listarProdutos(Pageable pageable) {
        Page<Produto> produtos =  repository.findAll(pageable);
        if(produtos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum registro de produto");
        }

        return produtos.map(ProdutoMapper::toSimpleProdutoResponse);
    }

    @Transactional
    public ProdutoSimplesResponse cadastrarProduto(ProdutoRequest request) {
        Produto produto = ProdutoMapper.toProdutoDomain(request);
        produto.setAtivo(true);
        repository.save(produto);

        return ProdutoMapper.toSimpleProdutoResponse(produto);
    }

    public ProdutoDetalhadoResponse detalharProduto(Long id) {
        var produto = repository.findByIdAndAtivoTrue(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Produto não cadastrado ou inativo na base de dados"));

        return ProdutoMapper.toProdutoDetalhadoResponse(produto);
    }

    @Transactional
    public void inativarProduto(Long id){
        var produto = repository.findByIdAndAtivoTrue(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto buscado não esta inativo"));
        produto.setAtivo(false);

        repository.save(produto);
    }

    @Transactional
    public void ativarProduto(Long id){
        var produto = repository.findByIdAndAtivoFalse(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto buscado não esta ativo"));
        produto.setAtivo(true);

        repository.save(produto);
    }
}
