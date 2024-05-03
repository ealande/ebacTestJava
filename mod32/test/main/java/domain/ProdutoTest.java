package main.java.domain;

import main.java.dao.ProdutoDao;
import main.java.dao.ProdutoDaoImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {
    private ProdutoDao produtoDao;

    public ProdutoTest() {
        produtoDao = new ProdutoDaoImp();
    }

    @Test
    @DisplayName("Deve cadastrar um novo produto no banco de dados")
    void teste01() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setDescricao("Novo produto");
        produto.setNome("Mouse");
        produto = produtoDao.cadastrar(produto);

        assertNotNull(produto);
        assertNotNull(produto.getId());
    }

    @Test
    @DisplayName("deve excluir o produto no banco de dados")
    void teste02() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setDescricao("Novo produto");
        produto.setNome("Mouse");
        produto = produtoDao.cadastrar(produto);
        produtoDao.excluir(produto);

        Produto prod = produtoDao.buscarPorId(produto);
        assertNull(prod);
    }

    @Test
    @DisplayName("deve buscar um produto no banco de dados")
    void teste03() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setDescricao("Novo produto");
        produto.setNome("Mouse");
        produtoDao.cadastrar(produto);

        Produto prod = produtoDao.buscarPorId(produto);

        assertNotNull(prod);
        assertNotNull(prod.getId());
        assertEquals(produto.getId(), prod.getId());
    }

    @Test
    @DisplayName("deve retornar uma lista de produtos no banco de dados")
    void teste04() {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setDescricao("Novo produto");
        produto1.setNome("Mouse");
        produtoDao.cadastrar(produto1);

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setDescricao("Novo produto");
        produto2.setNome("Mouse");
        produtoDao.cadastrar(produto2);

        List<Produto> lista = produtoDao.buscarTodos();

        assertNotNull(lista);
        assertEquals(2, lista.size());
    }
}