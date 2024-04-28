package dao;

import domain.Cliente;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ClienteSetDAO implements IClienteDAO {
  private final Set<Cliente> set;

  /** Construtor baseado em HashSet */
  public ClienteSetDAO() {
    this.set = new HashSet<>();
  }

  /**
   * @param cliente Recebe as informações do cliente que se deseja cadastrar
   * @return Caso o cliente não seja encontrado no banco de dados, ele será cadastrado e retornará *
   *     true, caso já conste no banco de dados, nada acontecerá no banco e retornará falso
   */
  @Override
  public Boolean cadastrar(Cliente cliente) {
    return set.add(cliente);
  }

  /** @param cpf Recebe apenas o cpf do cliente que se deseja excluir dos registros */
  @Override
  public void excluir(long cpf) {
    Cliente clienteEncontrado = null;
    for (Cliente cliente : this.set) {
      if (cliente.getCpf().equals(cpf)) {
        clienteEncontrado = cliente;
        break;
      }
    }

    if (clienteEncontrado != null) {
      this.set.remove(clienteEncontrado);
    }
  }

  /**
   * @param cliente Recebe apenas o cpf do cliente que se deseja alterar toda e qualquer informação
   */
  @Override
  public void alterar(Cliente cliente) {
    if (this.set.contains(cliente)) {
      this.set.remove(cliente);
      this.cadastrar(cliente);
    }
  }

  /**
   * @param cpf Recebe apenas o cpf do cliente que se deseja consultar
   * @return Retorna o cliente solicitado caso ele exista no banco
   */
  @Override
  public Cliente consultar(long cpf) {
    for (Cliente cliente : this.set) {
      if (cliente.getCpf().equals(cpf)) {
        return cliente;
      }
    }
    return null;
  }

  /** @return Retorna todos os clientes cadastrados no banco de dados */
  @Override
  public Collection<Cliente> buscarTodos() {
    return this.set;
  }
}
