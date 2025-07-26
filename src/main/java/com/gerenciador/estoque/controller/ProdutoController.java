// src/main/java/com/gerenciador/estoque/controller/ProdutoController.java
package com.gerenciador.estoque.controller;

import com.gerenciador.estoque.service.ProdutoService;
import com.gerenciador.estoque.dto.ProdutoRequest;
import com.gerenciador.estoque.model.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/save")
    public ProdutoDAO salvar(@RequestBody ProdutoRequest produtoRequest) {
        return produtoService.salvar(produtoRequest);
    }

    @PutMapping("/{id}")
    public ProdutoDAO atualizar(@PathVariable Long id, @RequestBody ProdutoRequest produtoRequest) {
        return produtoService.atualizar(id, produtoRequest);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }

    @GetMapping("/list")
    public List<ProdutoDAO> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoDAO buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }
}