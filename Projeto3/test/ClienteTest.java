import main.cliente.dao.ClienteDao;
import main.cliente.dao.ClienteDaoImp;
import main.cliente.domain.Cliente;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ClienteTest {

    private ClienteDao clienteDao;

    public ClienteTest() {
        this.clienteDao = new ClienteDaoImp();
    }


    @Test
    void cadastrarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Rodrigo Pires");
        Integer countCad = clienteDao.cadastrar(cliente);
        assertEquals(1, (int) countCad);

        Cliente clienteBD = clienteDao.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteDao.excluir(clienteBD);
        var retorno = clienteDao.buscar(cliente.getCodigo());
        assertNull(retorno);

    }

    @Test
    void buscarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Rodrigo Pires");
        Integer countCad = clienteDao.cadastrar(cliente);
        assertEquals(1, (int) countCad);

        Cliente clienteBD = clienteDao.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteDao.excluir(clienteBD);
        var retorno = clienteDao.buscar(cliente.getCodigo());
        assertNull(retorno);
    }

    @Test
    void excluirTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Rodrigo Pires");
        Integer countCad = clienteDao.cadastrar(cliente);
        assertEquals(1, (int) countCad);

        Cliente clienteBD = clienteDao.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteDao.excluir(clienteBD);
        var retorno = clienteDao.buscar(cliente.getCodigo());
        assertNull(retorno);
    }

    @Test
    void buscarTodosTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Rodrigo Pires");
        Integer countCad = clienteDao.cadastrar(cliente);
        assertEquals(1, (int) countCad);

        Cliente clientes = new Cliente();
        clientes.setCodigo("20");
        clientes.setNome("Teste");
        Integer countCad2 = clienteDao.cadastrar(clientes);
        assertEquals(1, (int) countCad2);

        List<Cliente> list = clienteDao.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        int countDel = 0;
        for (Cliente client : list) {
            clienteDao.excluir(client);
            countDel++;
        }
        assertEquals(list.size(), countDel);

        list = clienteDao.buscarTodos();
        assertEquals(0, list.size());

    }

    @Test
    void atualizarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Rodrigo Pires");
        Integer countCad = clienteDao.cadastrar(cliente);
        assertEquals(1, (int) countCad);

        Cliente clienteBD = clienteDao.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteBD.setCodigo("20");
        clienteBD.setNome("Outro nome");
        Integer countUpdate = clienteDao.atualizar(clienteBD);
        assertEquals(1, (int) countUpdate);

        Cliente clienteBD1 = clienteDao.buscar("10");
        assertNull(clienteBD1);

        Cliente clienteBD2 = clienteDao.buscar("20");
        assertNotNull(clienteBD2);
        assertEquals(clienteBD.getId(), clienteBD2.getId());
        assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
        assertEquals(clienteBD.getNome(), clienteBD2.getNome());

        List<Cliente> list = clienteDao.buscarTodos();
        for (Cliente client : list) {
            clienteDao.excluir(client);
        }
    }
}
