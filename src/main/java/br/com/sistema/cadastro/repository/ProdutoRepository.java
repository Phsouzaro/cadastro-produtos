package br.com.sistema.cadastro.repository;

import br.com.sistema.cadastro.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByIdAndAtivoTrue(Long id);

    Optional<Produto> findByIdAndAtivoFalse(Long id);
}
