package domain;

import java.util.Objects;

public class Cliente {
  private final String nome;
  private final Long cpf;
  private final Long telefone;
  private final String endereco;
  private final String cidade;
  private final String estado;

  /**
   * @param nome Nome do cliente
   * @param cpf CPF do cliente no formato string, que será convertido para Long
   * @param telefone Telefone do cliente no formato string, que será convertido para Long
   * @param endereco Endereço completo de residência do cliente
   * @param cidade Cidade de residência do cliente
   * @param estado Estado de residência do cliente
   */
  public Cliente(
      String nome, String cpf, String telefone, String endereco, String cidade, String estado) {
    this.nome = nome;
    this.cpf =
        Long.valueOf(
            cpf.trim()); // Com o trim estamos tirando os espaços caso o usuário tenha inserido eles
    this.telefone = Long.valueOf(telefone.trim());
    this.endereco = endereco;
    this.cidade = cidade;
    this.estado = estado;
  }

  public String getNome() {
    return nome;
  }

  public Long getCpf() {
    return cpf;
  }

  public Long getTelefone() {
    return telefone;
  }

  public String getEndereco() {
    return endereco;
  }

  public String getCidade() {
    return cidade;
  }

  public String getEstado() {
    return estado;
  }

  /**
   * @param object Recebe o cliente que se deseja comparar
   * @return Retorna verdadeiro caso o cliente especificado seja igual ao outro e falso caso seja
   *     diferente
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Cliente cliente = (Cliente) object;
    return cpf.equals(cliente.cpf);
  }

  /** @return Retorna o número do hash único da entidade criada */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 79 * hash + Objects.hash(cpf);
    return hash;
  }

  /** @return Retorna apenas o nome e CPF do cliente */
  @Override
  public String toString() {
    return "Cliente{" + "nome='" + nome + '\'' + ", cpf=" + cpf + '}';
  }
}
