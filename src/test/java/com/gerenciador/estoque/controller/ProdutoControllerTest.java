package com.gerenciador.estoque.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciador.estoque.dto.ProdutoRequest;
import com.gerenciador.estoque.model.ProdutoDAO;
import com.gerenciador.estoque.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrarProdutoComSucesso() throws Exception {
        ProdutoRequest produtoRequest = new ProdutoRequest(1L, "Produto Teste", "tipo teste", 10, BigDecimal.valueOf(54.99));

        mockMvc.perform(post("/produtos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(produtoRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveAtualizarProdutoComSucesso() throws Exception {
        ProdutoRequest produtoRequest = new ProdutoRequest(1L, "Produto Atualizado", "tipo teste", 20, BigDecimal.valueOf(99.99));
        ProdutoDAO produtoDAO = new ProdutoDAO(1L, "Produto Atualizado", "tipo teste", 20, BigDecimal.valueOf(99.99));

        Mockito.when(produtoService.atualizar(Mockito.eq(1L), Mockito.any())).thenReturn(produtoDAO);

        mockMvc.perform(put("/produtos/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(produtoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Atualizado"));
    }

    @Test
    void deveDeletarProdutoComSucesso() throws Exception {
        Mockito.doNothing().when(produtoService).deletar(1L);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarTodosProdutos() throws Exception {
        ProdutoDAO produto1 = new ProdutoDAO(1L, "Produto 1", "tipo 1", 10, BigDecimal.valueOf(10.0));
        ProdutoDAO produto2 = new ProdutoDAO(2L, "Produto 2", "tipo 2", 20, BigDecimal.valueOf(20.0));

        Mockito.when(produtoService.listarTodos()).thenReturn(Arrays.asList(produto1, produto2));

        mockMvc.perform(get("/produtos/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    void deveBuscarProdutoPorId() throws Exception {
        ProdutoDAO produto = new ProdutoDAO(1L, "Produto Teste", "tipo teste", 10, BigDecimal.valueOf(54.99));

        Mockito.when(produtoService.buscarPorId(1L)).thenReturn(produto);

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}