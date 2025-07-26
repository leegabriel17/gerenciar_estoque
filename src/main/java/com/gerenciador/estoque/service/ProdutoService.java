package com.gerenciador.estoque.service;

import com.gerenciador.estoque.converter.ProdutoConverter;
import com.gerenciador.estoque.model.ProdutoDAO;
import com.gerenciador.estoque.model.ProdutoEntity;
import com.gerenciador.estoque.repository.ProdutoRepository;
import com.gerenciador.estoque.dto.ProdutoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {



    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDAO salvar(ProdutoRequest produtoRequest) {
        final var produtoDao = ProdutoConverter.toDao(produtoRequest);
        final var produtoEntity = ProdutoConverter.toEntity(produtoDao);
        return ProdutoConverter.toDao(produtoRepository.save(produtoEntity));
    }

    public ProdutoDAO atualizar(Long id, ProdutoRequest produtoRequest) {
        try {
            Optional<ProdutoEntity> produtoOpt = produtoRepository.findById(id);
            if (produtoOpt.isEmpty()) {
                throw new RuntimeException("Produto com ID " + id + " não encontrado.");
            }
            ProdutoDAO produtoDAO = ProdutoConverter.toDao(produtoOpt.get());
            produtoDAO.setNome(produtoRequest.getNome());
            produtoDAO.setPreco(produtoRequest.getPreco());
            produtoDAO.setQuantidade(produtoRequest.getQuantidade());
            ProdutoEntity produtoEntity = ProdutoConverter.toEntity(produtoDAO);
            return ProdutoConverter.toDao(produtoRepository.save(produtoEntity));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<ProdutoDAO> listarTodos() {
        List<ProdutoEntity> ProdutoDao = produtoRepository.findAll();
        return ProdutoDao.stream()
                .map(ProdutoConverter::toDao)
                .toList();
    }

    public ProdutoDAO buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .map(ProdutoConverter::toDao)
                .orElse(null);
    }
}