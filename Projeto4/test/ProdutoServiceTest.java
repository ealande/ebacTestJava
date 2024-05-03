/**
 *
 */

import dao.ProdutoDaoMock;
import main.java.br.com.rpires.dao.IProdutoDAO;
import main.java.br.com.rpires.domain.Produto;
import main.java.br.com.rpires.services.IProdutoService;
import main.java.br.com.rpires.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author rodrigo.pires
 */
class ProdutoServiceTest {

    private final IProdutoService produtoService;

    private Produto produto;

    public ProdutoServiceTest() {
        IProdutoDAO dao = new ProdutoDaoMock();
        produtoService = new ProdutoService(dao);
    }

    @BeforeEach
    public void init() {
        produto = new Produto();
        produto.setCodigo("A1");
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
        produto.setMarca("M1");
    }

    @Test
    void pesquisar() {
        Produto produtor = this.produtoService.consultar(produto.getCodigo());
        assertNotNull(produtor);
    }

    @Test
    void salvar() {
        Produto retorno = produtoService.cadastrar(produto);
        assertNotNull(retorno);
    }

    @Test
    void excluir() {
        produtoService.excluir(produto);
    }

    @Test
    void alterarCliente() {
        produto.setNome("Rodrigo Pires");
        produtoService.alterar(produto);

        assertEquals("Rodrigo Pires", produto.getNome());
    }
}
