/**
 *
 */

import dao.ClienteDaoMock;
import main.java.br.com.rpires.dao.IClienteDAO;
import main.java.br.com.rpires.domain.Cliente;
import main.java.br.com.rpires.services.ClienteService;
import main.java.br.com.rpires.services.IClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClienteServiceTest {

    private final IClienteService clienteService;

    private Cliente cliente;

    public ClienteServiceTest() {
        IClienteDAO dao = new ClienteDaoMock();
        clienteService = new ClienteService(dao);
    }

    @BeforeEach
    public void init() {
        cliente = new Cliente();
        cliente.setCpf(12312312312L);
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        cliente.setPais("Brasil");
    }

    @Test
    void pesquisarCliente() {
        Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
        assertNotNull(clienteConsultado);
    }

    @Test
    void salvarCliente() {
        Cliente retorno = clienteService.cadastrar(cliente);

        assertNotNull(retorno);
    }

    @Test
    void excluirCliente() {
        clienteService.excluir(cliente);
    }

    @Test
    void alterarCliente() {
        cliente.setNome("Rodrigo Pires");
        clienteService.alterar(cliente);

        assertEquals("Rodrigo Pires", cliente.getNome());
    }
}
