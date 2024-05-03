/**
 *
 */

import main.java.br.com.rpires.dao.*;
import main.java.br.com.rpires.domain.Cliente;
import main.java.br.com.rpires.domain.Produto;
import main.java.br.com.rpires.domain.Venda;
import main.java.br.com.rpires.domain.Venda.Status;
import main.java.br.com.rpires.exceptions.DAOException;
import main.java.br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class VendaDAOTest {

    private final IVendaDAO vendaDao;

    private final IClienteDAO clienteDao;

    private final IProdutoDAO produtoDao;

    private Cliente cliente;

    private Produto produto;

    public VendaDAOTest() {
        vendaDao = new VendaDAO();
        clienteDao = new ClienteDAO();
        produtoDao = new ProdutoDAO();
    }

    @BeforeEach
    public void init() {
        this.cliente = cadastrarCliente();
        this.produto = cadastrarProduto("A1", BigDecimal.TEN);
    }

    @AfterEach
    public void end() {
        excluirVendas();
        excluirProdutos();
        clienteDao.excluir(this.cliente);
    }

    private void excluirVendas() {
        Collection<Venda> list = this.vendaDao.buscarTodos();
        for (Venda venda : list) {
            this.vendaDao.excluir(venda);
        }
    }


    private void excluirProdutos() {
        Collection<Produto> list = this.produtoDao.buscarTodos();
        for (Produto prod : list) {
            this.produtoDao.excluir(prod);
        }
    }

    @Test
    void pesquisar() {
        Venda venda = criarVenda("A1");
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        Venda vendaConsultada = vendaDao.consultar(venda.getCodigo());
        assertNotNull(vendaConsultada);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
    }

    @Test
    void salvar() {
        Venda venda = criarVenda("A2");
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);

        assertEquals(venda.getValorTotal(), BigDecimal.valueOf(20));
        assertEquals(Status.INICIADA, venda.getStatus());

        Venda vendaConsultada = vendaDao.consultar(venda.getCodigo());
        assertNotNull(vendaConsultada.getId());
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
    }


    @Test
    void cancelarVenda() {
        String codigoVenda = "A3";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        vendaDao.cancelarVenda(venda);

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        assertEquals(codigoVenda, vendaConsultada.getCodigo());
        assertEquals(Status.CANCELADA, vendaConsultada.getStatus());
    }

    @Test
    void adicionarMaisProdutosDoMesmo() {
        String codigoVenda = "A4";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(produto, 1);

        assertEquals(3, (int) vendaConsultada.getQuantidadeTotalProdutos());
        BigDecimal valorTotal = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_DOWN);
        assertEquals(vendaConsultada.getValorTotal(), valorTotal);
        assertEquals(Status.INICIADA, vendaConsultada.getStatus());
    }

    @Test
    void adicionarMaisProdutosDiferentes() {
        String codigoVenda = "A5";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);

        assertEquals(3, (int) vendaConsultada.getQuantidadeTotalProdutos());
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertEquals(vendaConsultada.getValorTotal(), valorTotal);
        assertEquals(Status.INICIADA, vendaConsultada.getStatus());
    }

    @Test
    void salvarVendaMesmoCodigoExistente() {
        Venda venda = criarVenda("A6");
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);


        assertThrows(DAOException.class, () -> {
            vendaDao.cadastrar(venda);
        });

        assertEquals(Status.INICIADA, venda.getStatus());
    }

    @Test
    void removerProduto() {
        String codigoVenda = "A7";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        assertEquals(3, (int) vendaConsultada.getQuantidadeTotalProdutos());
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertEquals(vendaConsultada.getValorTotal(), valorTotal);


        vendaConsultada.removerProduto(prod, 1);
        assertEquals(2, (int) vendaConsultada.getQuantidadeTotalProdutos());
        valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertEquals(vendaConsultada.getValorTotal(), valorTotal);
        assertEquals(Status.INICIADA, vendaConsultada.getStatus());
    }

    @Test
    void removerApenasUmProduto() {
        String codigoVenda = "A8";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        assertEquals(3, (int) vendaConsultada.getQuantidadeTotalProdutos());
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertEquals(vendaConsultada.getValorTotal(), valorTotal);


        vendaConsultada.removerProduto(prod, 1);
        assertEquals(2, (int) vendaConsultada.getQuantidadeTotalProdutos());
        valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertEquals(vendaConsultada.getValorTotal(), valorTotal);
        assertEquals(Status.INICIADA, vendaConsultada.getStatus());
    }

    @Test
    void removerTodosProdutos() {
        String codigoVenda = "A9";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        assertEquals(3, (int) vendaConsultada.getQuantidadeTotalProdutos());
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertEquals(vendaConsultada.getValorTotal(), valorTotal);


        vendaConsultada.removerTodosProdutos();
        assertEquals(0, (int) vendaConsultada.getQuantidadeTotalProdutos());
        assertEquals(vendaConsultada.getValorTotal(), BigDecimal.valueOf(0));
        assertEquals(Status.INICIADA, vendaConsultada.getStatus());
    }

    @Test
    void finalizarVenda() {
        String codigoVenda = "A10";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        vendaDao.finalizarVenda(venda);

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());
    }

    @Test
    void tentarAdicionarProdutosVendaFinalizada() {
        String codigoVenda = "A11";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        vendaDao.finalizarVenda(venda);
        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());

        vendaConsultada.adicionarProduto(this.produto, 1);

    }

    private Produto cadastrarProduto(String codigo, BigDecimal valor) {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(valor);
        produtoDao.cadastrar(produto);
        return produto;
    }

    private Cliente cadastrarCliente() throws TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(12312312312L);
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        clienteDao.cadastrar(cliente);
        return cliente;
    }

    private Venda criarVenda(String codigo) {
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        venda.setDataVenda(Instant.now());
        venda.setCliente(this.cliente);
        venda.setStatus(Status.INICIADA);
        venda.adicionarProduto(this.produto, 2);
        return venda;
    }

}
