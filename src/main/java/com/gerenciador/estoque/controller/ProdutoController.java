package com.gerenciador.estoque.controller;


import com.gerenciador.estoque.converter.ProdutoConverter;
import com.gerenciador.estoque.model.ProdutoDAO;
import com.gerenciador.estoque.model.ProdutoEntity;
import com.gerenciador.estoque.repository.ProdutoRepository;
import com.gerenciador.estoque.view.ProdutoRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<ProdutoEntity> listarTodos() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public ProdutoDAO salvar(@RequestBody @Validated ProdutoRequest produtoRequest) {
        ProdutoDAO produto = ProdutoConverter.toDao(produtoRequest);
        if (produto.getId() != null) {
            ProdutoEntity existingProduto = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + produto.getId()));
        }
        ProdutoEntity produtoEntity = ProdutoConverter.toEntity(produto);
        return ProdutoConverter.toDao(produtoRepository.save(produtoEntity));
    }
}