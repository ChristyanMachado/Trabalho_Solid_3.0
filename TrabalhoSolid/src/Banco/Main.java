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


}