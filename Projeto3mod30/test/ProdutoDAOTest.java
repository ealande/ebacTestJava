/**
 *
 */

import main.java.br.com.rpires.dao.IProdutoDAO;
import main.java.br.com.rpires.dao.ProdutoDAO;
import main.java.br.com.rpires.domain.Produto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author rodrigo.pires
 */
public class ProdutoDAOTest {

    private IProdutoDAO produtoDao;

    public ProdutoDAOTest() {
        produtoDao = new ProdutoDAO();
    }

    @After
    public void end() {
        Collection<Produto> list = produtoDao.buscarTodos();
        list.forEach(prod -> {
            produtoDao.excluir(prod.getCodigo());
        });
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

    private void excluir(String valor) {
        this.produtoDao.excluir(valor);
    }

    @Test
    public void pesquisar() {
        Produto produto = criarProduto("A1");
        Assert.assertNotNull(produto);
        Produto produtoDB = this.produtoDao.consultar(produto.getCodigo());
        Assert.assertNotNull(produtoDB);
        excluir(produtoDB.getCodigo());
    }

    @Test
    public void salvar() {
        Produto produto = criarProduto("A2");
        Assert.assertNotNull(produto);
        excluir(produto.getCodigo());
    }

    @Test
    public void excluir() {
        Produto produto = criarProduto("A3");
        Assert.assertNotNull(produto);
        excluir(produto.getCodigo());
        Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
        assertNull(produtoBD);
    }

    @Test
    public void alterarCliente() {
        Produto produto = criarProduto("A4");
        produto.setNome("Rodrigo Pires");
        produtoDao.alterar(produto);
        Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        Assert.assertEquals("Rodrigo Pires", produtoBD.getNome());

        excluir(produto.getCodigo());
        Produto produtoBD1 = this.produtoDao.consultar(produto.getCodigo());
        assertNull(produtoBD1);
    }

    @Test
    public void buscarTodos() {
        criarProduto("A5");
        criarProduto("A6");
        Collection<Produto> list = produtoDao.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        for (Produto prod : list) {
            excluir(prod.getCodigo());
        }

        list = produtoDao.buscarTodos();
        assertNotNull(list);
        assertEquals(0, list.size());

    }
}
