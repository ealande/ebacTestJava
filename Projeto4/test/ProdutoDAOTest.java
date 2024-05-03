/**
 *
 */

import main.java.br.com.rpires.dao.IProdutoDAO;
import main.java.br.com.rpires.dao.ProdutoDAO;
import main.java.br.com.rpires.domain.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author rodrigo.pires
 */
class ProdutoDAOTest {

    private final IProdutoDAO produtoDao;

    public ProdutoDAOTest() {
        produtoDao = new ProdutoDAO();
    }

    @AfterEach
    public void end() {
        Collection<Produto> list = produtoDao.buscarTodos();
        list.forEach(produtoDao::excluir);
    }

    private Produto criarProduto(String codigo) {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
        produto.setMarca("M1");
        produtoDao.cadastrar(produto);
        return produto;
    }

    private void excluir(Produto valor) {
        this.produtoDao.excluir(valor);
    }

    @Test
    void pesquisar() {
        Produto produto = criarProduto("A1");
        assertNotNull(produto);
        Produto produtoDB = this.produtoDao.consultar(produto.getCodigo());
        assertNotNull(produtoDB);
        excluir(produtoDB);
    }

    @Test
    void salvar() {
        Produto produto = criarProduto("A2");
        assertNotNull(produto);
        excluir(produto);
    }

    @Test
    void excluir() {
        Produto produto = criarProduto("A3");
        assertNotNull(produto);
        excluir(produto);
        Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
        assertNull(produtoBD);
    }

    @Test
    void alterarCliente() {
        Produto produto = criarProduto("A4");
        produto.setNome("Rodrigo Pires");
        produtoDao.alterar(produto);
        Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertEquals("Rodrigo Pires", produtoBD.getNome());

        excluir(produto);
        Produto produtoBD1 = this.produtoDao.consultar(produto.getCodigo());
        assertNull(produtoBD1);
    }

    @Test
    void buscarTodos() {
        criarProduto("A5");
        criarProduto("A6");
        Collection<Produto> list = produtoDao.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        for (Produto prod : list) {
            excluir(prod);
        }

        list = produtoDao.buscarTodos();
        assertNotNull(list);
        assertEquals(0, list.size());

    }
}
