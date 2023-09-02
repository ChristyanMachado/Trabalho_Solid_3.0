package Banco;

import Banco.Cliente.ClientePessoaFisica;
import Banco.Contas.AbstractConta;
import Banco.Contas.ContaCorrente;
import Banco.Contas.ContaPoupanca;
import Banco.MenuItens.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main extends Menu {
    public static void main(String[] args)  {
        exibirMenu();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do cliente:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o CPF do cliente:");
                    String cpf = scanner.nextLine();
                    ClientePessoaFisica cliente = new ClientePessoaFisica(nome, cpf) {
                    };
                    clientes.put(cpf, cliente);
                    break;

                case 2:
                    cadastrarConta(clientes, contas, ContaCorrente.class, scanner);
                    break;

                case 3:
                    cadastrarConta(clientes, contas, ContaPoupanca.class, scanner);
                    break;

                case 4:
                    efetuarDeposito(contas, scanner);
                    break;

                case 5:
                    efetuarSaque(contas, scanner);
                    break;

                case 6:
                    efetuarTransferencia(contas, scanner);
                    break;

                case 7:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    private static void cadastrarConta(Map<String, ClientePessoaFisica> clientes, Map<String, Conta> contas, Class<? extends Conta> tipoConta, Scanner scanner) {
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.nextLine();
        ClientePessoaFisica cliente = clientes.get(cpf);

        if (cliente != null) {
            try {
                Conta conta = tipoConta.getDeclaredConstructor(String.class, ClientePessoaFisica.class).newInstance("123", cliente);
                contas.put(conta.getNumeroConta(), conta);
                System.out.println("Conta cadastrada com sucesso.");
            } catch (Exception e) {
                System.out.println("Erro ao cadastrar a conta.");
            }
        } else {
            System.out.println("Cliente não encontrado. Cadastre o cliente primeiro.");
        }
    }

}