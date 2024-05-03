package br.com.ebac;

import br.com.ebac.domain.Cliente;
import br.com.ebac.reources.ClienteResource;
import br.com.ebac.usecase.BuscaCliente;
import br.com.ebac.usecase.CadastroCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ClienteResourceTest {

    @InjectMocks
    private ClienteResource clienteResource;

    @MockBean
    private BuscaCliente buscaCliente;

    @MockBean
    private CadastroCliente cadastroCliente;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarPorId() {
        Cliente cliente1 = Cliente.builder().id("1").nome("Rodrigo 1").build();

        when(buscaCliente.buscarPorId("1")).thenReturn(cliente1);

        ResponseEntity<Cliente> result = clienteResource.buscarPorId("1");

        Cliente clienteResult = result.getBody();
        assertThat(clienteResult).isEqualTo(cliente1);
        assertThat(clienteResult.getId()).isEqualTo(cliente1.getId());
        assertThat(clienteResult.getNome()).isEqualTo(cliente1.getNome());
    }

}
