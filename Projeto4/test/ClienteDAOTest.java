/**
 *
 */

import main.java.br.com.rpires.dao.ClienteDAO;
import main.java.br.com.rpires.dao.IClienteDAO;
import main.java.br.com.rpires.domain.Cliente;
import main.java.br.com.rpires.exceptions.DAOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDAOTest {

    private final IClienteDAO clienteDao;

    public ClienteDAOTest() {
        clienteDao = new ClienteDAO();
    }

    @AfterEach
    public void end() throws DAOException {
        Collection<Cliente> list = clienteDao.buscarTodos();
        list.forEach(clienteDao::excluir);
    }

    @Test
    void pesquisarCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf(12312312312L);
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setPais("Brasil");
        cliente.setTel(1199999999L);
        clienteDao.cadastrar(cliente);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente);
    }

    @Test
    void salvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf(56565656565L);
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        cliente.setPais("Brasil");
        Cliente retorno = clienteDao.cadastrar(cliente);
        assertEquals(retorno.getId(), cliente.getId());

        clienteDao.excluir(cliente);
    }


    @Test
    void excluirCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf(56565656565L);
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        cliente.setPais("Brasil");
        clienteDao.cadastrar(cliente);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente);
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        assertNull(clienteConsultado);
    }

    @Test
    void alterarCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf(56565656565L);
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        cliente.setPais("Brasil");
        Cliente retorno = clienteDao.cadastrar(cliente);
        assertEquals(retorno.getId(), cliente.getId());

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());

        clienteConsultado.setNome("Rodrigo Pires");
        clienteDao.alterar(clienteConsultado);

        Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpf());
        assertNotNull(clienteAlterado);
        assertEquals("Rodrigo Pires", clienteAlterado.getNome());

        clienteDao.excluir(cliente);
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        assertNull(clienteConsultado);
    }

    @Test
    void buscarTodos() {
        Cliente cliente = new Cliente();
        cliente.setCpf(56565656565L);
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        cliente.setPais("Brasil");
        clienteDao.cadastrar(cliente);

        Cliente cliente1 = new Cliente();
        cliente1.setCpf(56565656569L);
        cliente1.setNome("Rodrigo");
        cliente1.setCidade("São Paulo");
        cliente1.setEnd("End");
        cliente1.setEstado("SP");
        cliente1.setNumero(10);
        cliente1.setTel(1199999999L);
        cliente1.setPais("USA");
        Cliente retorno1 = clienteDao.cadastrar(cliente1);
        assertNotNull(retorno1);

        Collection<Cliente> list = clienteDao.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        list.forEach(clienteDao::excluir);

        Collection<Cliente> list1 = clienteDao.buscarTodos();
        assertNotNull(list1);
        assertEquals(0, list1.size());
    }
}
