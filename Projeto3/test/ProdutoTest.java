import main.cliente.dao.ClienteDaoImp;
import main.cliente.domain.Cliente;
import main.produto.dao.ProdutoDao;
import main.produto.dao.ProdutoDaoImp;
import main.produto.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ProdutoTest {

    private ProdutoDao produtoDao;
    Produto produto = new Produto();


    public ProdutoTest() {
        this.produtoDao = new ProdutoDaoImp();
        produto.setCodigo("10");
        produto.setNome("mouse");
    }


    @Test
    void cadastrarTest() throws Exception {
        Integer countCad = produtoDao.cadastrar(produto);
        assertEquals(1, (int) countCad);

        Produto produtoBD = produtoDao.buscar("10");
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        produtoDao.excluir(produtoBD);
        var retorno = produtoDao.buscar(produto.getCodigo());
        assertNull(retorno);

    }

    @Test
    void buscarTest() throws Exception {
        Integer countCad = produtoDao.cadastrar(produto);
        assertEquals(1, (int) countCad);

        Produto produtoBD = produtoDao.buscar("10");
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        produtoDao.excluir(produtoBD);
        var retorno = produtoDao.buscar(produto.getCodigo());
        assertNull(retorno);
    }

    @Test
    void excluirTest() throws Exception {
        Integer countCad = produtoDao.cadastrar(produto);
        assertEquals(1, (int) countCad);

        Produto produtoBD = produtoDao.buscar("10");
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        produtoDao.excluir(produtoBD);
        var retorno = produtoDao.buscar(produto.getCodigo());
        assertNull(retorno);
    }

    @Test
    void buscarTodosTest() throws Exception {
        Integer countCad = produtoDao.cadastrar(produto);
        assertEquals(1, (int) countCad);

        Produto produto1 = new Produto();
        produto1.setCodigo("20");
        produto1.setNome("Teste");
        Integer countCad2 = produtoDao.cadastrar(produto1);
        assertEquals(1, (int) countCad2);

        List<Produto> list = produtoDao.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        int countDel = 0;
        for (Produto prodt : list) {
            produtoDao.excluir(prodt);
            countDel++;
        }
        assertEquals(list.size(), countDel);

        list = produtoDao.buscarTodos();
        assertEquals(0, list.size());

    }

    @Test
    void atualizarTest() throws Exception {
        Integer countCad = produtoDao.cadastrar(produto);
        assertEquals(1, (int) countCad);

        Produto produtoBD = produtoDao.buscar("10");
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        produtoBD.setCodigo("20");
        produtoBD.setNome("Outro nome");
        Integer countUpdate = produtoDao.atualizar(produtoBD);
        assertEquals(1, (int) countUpdate);

        Produto produtoBD1 = produtoDao.buscar("10");
        assertNull(produtoBD1);

        Produto produtoBD2 = produtoDao.buscar("20");
        assertNotNull(produtoBD2);
        assertEquals(produtoBD.getId(), produtoBD2.getId());
        assertEquals(produtoBD.getCodigo(), produtoBD2.getCodigo());
        assertEquals(produtoBD.getNome(), produtoBD2.getNome());

        List<Produto> list = produtoDao.buscarTodos();
        for (Produto prodt : list) {
            produtoDao.excluir(prodt);
        }
    }
}
