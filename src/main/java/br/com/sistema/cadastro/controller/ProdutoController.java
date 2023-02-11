package br.com.sistema.cadastro.controller;

import br.com.sistema.cadastro.controller.request.ProdutoRequest;
import br.com.sistema.cadastro.controller.response.ProdutoDetalhadoResponse;
import br.com.sistema.cadastro.controller.response.ProdutoSimplesResponse;
import br.com.sistema.cadastro.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoSimplesResponse> cadastrarProduto(@Valid @RequestBody ProdutoRequest request){
        ProdutoSimplesResponse produto = service.cadastrarProduto(request);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoSimplesResponse>> listarProdutos(Pageable pageable){
        Page<ProdutoSimplesResponse> produtos = service.listarProdutos(pageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalhadoResponse> detalharProduto(@PathVariable Long id){
        return ResponseEntity.ok(service.detalharProduto(id));
    }

    @PostMapping("/inativar/{id}")
    public ResponseEntity<Void> inativarProduto(@PathVariable Long id){
        service.inativarProduto(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarProduto(@PathVariable Long id){
        service.ativarProduto(id);
        return ResponseEntity.ok().build();
    }
}
