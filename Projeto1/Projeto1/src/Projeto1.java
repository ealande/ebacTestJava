import dao.ClienteMapDAO;
import dao.ClienteSetDAO;
import dao.IClienteDAO;
import domain.Cliente;
import javax.swing.*;

public class Projeto1 {
  /**
   * Essa variável pode ser implementada tanto pelo ClienteSetDAO como pelo ClienteMapDAO
   *
   * @see ClienteMapDAO
   * @see ClienteSetDAO
   */
  private static IClienteDAO iClienteDAO;

  private static String opcao = "";
  private static String dados = "";

  public static void main(String[] args) {
    iClienteDAO = new ClienteSetDAO();
    String cpf;

    opcao =
        JOptionPane.showInputDialog(
            null,
            "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para"
                + " sair",
            "Green dinner",
            JOptionPane.INFORMATION_MESSAGE);

    while (!isOpcaoValida(opcao)) {
      if (opcao.equals("")) {
        sair();
      }
      opcao =
          JOptionPane.showInputDialog(
              null,
              "Opção inválida! Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para"
                  + " alteração ou 5 para sair",
              "Green dinner",
              JOptionPane.INFORMATION_MESSAGE);
    }

    while (isOpcaoValida(opcao)) {
      switch (opcao) {
        case "5":
          sair();
          break;
        case "4":
          cpf =
              JOptionPane.showInputDialog(
                  null,
                  "Digite o número do CPF sem pontos e sem traço do cliente que deseja alterar",
                  "Alteração",
                  JOptionPane.INFORMATION_MESSAGE);
          alterar(cpf);
          break;
        case "3":
          cpf =
              JOptionPane.showInputDialog(
                  null,
                  "Digite o número do CPF sem pontos e sem traço do cliente que deseja excluir",
                  "Exclusão",
                  JOptionPane.INFORMATION_MESSAGE);
          excluir(cpf);
          break;
        case "2":
          cpf =
              JOptionPane.showInputDialog(
                  null,
                  "Digite o número do CPF sem pontos e sem traço do cliente que deseja consultar",
                  "Exclusão",
                  JOptionPane.INFORMATION_MESSAGE);
          consultar(cpf);
          break;
        default:
          dados =
              JOptionPane.showInputDialog(
                  null,
                  "Digite os dados do cliente separados por vírgula, conforme exemplo:"
                      + " Nome,CPF,Telefone,Endereço,Cidade,Estado",
                  "Cadastro",
                  JOptionPane.INFORMATION_MESSAGE);
          cadastrar(dados);
          break;
      }
    }
  }

  private static void consultar(String cpf) {
    Cliente cliente = iClienteDAO.consultar(Long.parseLong(cpf.trim()));
    if (cliente != null) {

      opcao =
          JOptionPane.showInputDialog(
              null,
              cliente
                  + "\n"
                  + "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou"
                  + " 5 para sair",
              "Consulta",
              JOptionPane.INFORMATION_MESSAGE);
    } else {
      opcao =
          JOptionPane.showInputDialog(
              null,
              "Cliente do cpf "
                  + cpf
                  + " não encontrado.\n"
                  + "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou"
                  + " 5 para sair",
              "Consulta",
              JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private static void excluir(String cpf) {
    iClienteDAO.excluir(Long.parseLong(cpf.trim()));
    opcao =
        JOptionPane.showInputDialog(
            null,
            "Cliente do CPF "
                + cpf
                + " excluído com sucesso.\n"
                + "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5"
                + " para sair",
            "Exclusão",
            JOptionPane.INFORMATION_MESSAGE);
  }

  private static void alterar(String cpf) {
    Cliente cliente = iClienteDAO.consultar(Long.parseLong(cpf.trim()));
    dados =
        JOptionPane.showInputDialog(
            null,
            "Digite os dados do cliente "
                + cliente
                + " separados por vírgula, conforme exemplo:"
                + " Nome,CPF,Telefone,Endereço,Cidade,Estado",
            "Alteração",
            JOptionPane.INFORMATION_MESSAGE);
    String[] dadosSeparados = dados.split(",");
    Cliente clienteASerAtualizado =
        new Cliente(
            dadosSeparados[0],
            dadosSeparados[1],
            dadosSeparados[2],
            dadosSeparados[3],
            dadosSeparados[4],
            dadosSeparados[5]);

    if (clienteASerAtualizado.equals(cliente)) {
      iClienteDAO.alterar(clienteASerAtualizado);
      opcao =
          JOptionPane.showInputDialog(
              null,
              "Cliente atualizado com sucesso.\n"
                  + "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou"
                  + " 5 para sair",
              "Atualização",
              JOptionPane.INFORMATION_MESSAGE);
    } else {
      opcao =
          JOptionPane.showInputDialog(
              null,
              "Cliente não foi atualizado atualizado pois o CPF não é o mesmo.\n"
                  + "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou"
                  + " 5 para sair",
              "Atualização",
              JOptionPane.INFORMATION_MESSAGE);
    }
  }

  /**
   * @param opcao Número digitado pelo usuário
   * @return retorna true caso o usuário digite de 1 a 5 e false caso seja alguma opção inválida
   */
  private static boolean isOpcaoValida(String opcao) {
    return opcao.equals("1")
        || opcao.equals("2")
        || opcao.equals("3")
        || opcao.equals("4")
        || opcao.equals("5");
  }

  private static void sair() {
    StringBuilder clientesCadastrados = new StringBuilder();
    for (Cliente cliente : iClienteDAO.buscarTodos()) {
      clientesCadastrados.append(cliente.toString()).append("\n");
    }

    JOptionPane.showMessageDialog(null, "Clientes cadastrados: " + clientesCadastrados);
    System.exit(0);
  }

  private static void cadastrar(String dados) {
    String[] dadosSeparados = dados.split(",");
    Cliente cliente =
        new Cliente(
            dadosSeparados[0],
            dadosSeparados[1],
            dadosSeparados[2],
            dadosSeparados[3],
            dadosSeparados[4],
            dadosSeparados[5]);

    Boolean isCadastrado = iClienteDAO.cadastrar(cliente);

    if (isCadastrado) {
      opcao =
          JOptionPane.showInputDialog(
              null,
              "Cliente cadastrado com sucesso.\n"
                  + "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou"
                  + " 5 para sair",
              "Sucesso",
              JOptionPane.INFORMATION_MESSAGE);
    } else {
      opcao =
          JOptionPane.showInputDialog(
              null,
              "Cliente foi cadastrado anteriormente.\n"
                  + "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou"
                  + " 5 para sair",
              "Erro",
              JOptionPane.INFORMATION_MESSAGE);
    }
  }
}
