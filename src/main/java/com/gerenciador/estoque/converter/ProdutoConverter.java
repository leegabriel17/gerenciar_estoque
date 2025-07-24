package com.gerenciador.estoque.converter;

import com.gerenciador.estoque.model.ProdutoDAO;
import com.gerenciador.estoque.model.ProdutoEntity;
import com.gerenciador.estoque.view.ProdutoRequest;

public class ProdutoConverter {

    public static ProdutoDAO toDao(ProdutoEntity produto) {
        if (produto == null) return null;

        ProdutoDAO dao = new ProdutoDAO();
        dao.setId(produto.getId());
        dao.setNome(produto.getNome());
        dao.setTipo(produto.getTipo());
        dao.setQuantidade(produto.getQuantidade());
        dao.setPreco(produto.getPreco());
        return dao;
    }

    public static ProdutoDAO toDao(ProdutoRequest produto) {
        if (produto == null) return null;

        ProdutoDAO dao = new ProdutoDAO();
        dao.setId(produto.getId());
        dao.setNome(produto.getNome());
        dao.setTipo(produto.getTipo());
        dao.setQuantidade(produto.getQuantidade());
        dao.setPreco(produto.getPreco());
        return dao;
    }

    public static ProdutoEntity toEntity(ProdutoDAO dao) {
        if (dao == null) return null;

        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(dao.getId());
        produto.setNome(dao.getNome());
        produto.setTipo(dao.getTipo());
        produto.setQuantidade(dao.getQuantidade());
        produto.setPreco(dao.getPreco());
        return produto;
    }
}
