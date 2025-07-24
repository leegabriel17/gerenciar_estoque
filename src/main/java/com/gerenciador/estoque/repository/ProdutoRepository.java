package com.gerenciador.estoque.repository;

import com.gerenciador.estoque.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}