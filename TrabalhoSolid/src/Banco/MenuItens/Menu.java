package Banco.MenuItens;

import Banco.Cliente.AbstractCliente;
import Banco.Cliente.ClientePessoaFisica;
import Banco.Contas.AbstractConta;
import Banco.Contas.ContaCorrente;
import Banco.Contas.ContaPoupanca;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static Map<String, ClientePessoaFisica> clientes = new HashMap<>();
    private static Map<String, ContaCorrente> contasCorrente = new HashMap<>();
    private static Map<String, ContaPoupanca> contasPoupanca = new HashMap<>();

    public static void exibirMenu() {
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("\n=-=-=-=-=-=-=-=-=-MENU-=-=-=-=-=-=-=-=-=-\n" +
                               "||          Escolha uma opção:         ||");
            System.out.println("|| 1 - Cadastrar Cliente Pessoa Física ||");
            System.out.println("|| 2 - Cadastrar Conta Corrente        ||");
            System.out.println("|| 3 - Cadastrar Conta Poupança        ||");
            System.out.println("|| 4 - Efetuar Depósito                ||");
            System.out.println("|| 5 - Efetuar Saque                   ||");
            System.out.println("|| 6 - Efetuar Transferência           ||");
            System.out.println("|| 7 -            SAIR                 ||");
            System.out.println("=-=-=-=-=-=-=-=-=-MENU-=-=-=-=-=-=-=-=-=-");
            System.out.print(" Opção:");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");
            switch (opcao) {
                case 1:
                    System.out.println("Efetuando Cadastro de Cliente");
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    System.out.println("Efetuando Cadastro de Conta Corrente");
                    cadastrarContaCorrente(scanner);
                    break;
                case 3:
                    System.out.println("Efetuando Cadastro de Conta Poupança");
                    cadastrarContaPoupanca(scanner);
                    break;
                case 4:
                    System.out.println("Efetuando Deposito");
                    efetuarDeposito(scanner);
                    break;
                case 5:
                    System.out.println("Efetuando Saque");
                    efetuarSaque(scanner);
                    break;
                case 6:
                    System.out.println("Efetuando Transferencia");
                    efetuarTransferencia(scanner);
                    break;
                case 7:
                    System.out.println("\nENCERRANDO SISTEMA...\n" +
                            "\nENCERRANDO SISTEMA...\n" +
                            "\nENCERRANDO SISTEMA...\n");
                    loop = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.print("Digite o CPF do cliente:" +
                "\nCPF:");
        String cpf = scanner.nextLine();

        System.out.print("Digite o nome do cliente:" +
                "\nNome:");
        String nome = scanner.nextLine();

        ClientePessoaFisica cliente = new ClientePessoaFisica(cpf, nome);
        clientes.put(cpf, cliente);

        System.out.println("Cliente cadastrado com sucesso.");
    }

    private static void cadastrarContaCorrente(Scanner scanner) {
        System.out.print("Digite o CPF do cliente:" +
                "\nCPF:");
        String cpf = scanner.nextLine();

        AbstractCliente cliente = clientes.get(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite o código da agência:" +
                "\nCódigo:");
        String agencia = scanner.nextLine();

        System.out.print("Digite a senha da conta:" +
                "\nSenha:");
        String senha = scanner.nextLine();

        try {
            AbstractConta.validarSenhaCriacao(senha);
            ContaCorrente contaCorrente = new ContaCorrente(agencia, cliente, senha);
            contasCorrente.put(contaCorrente.getAgencia() + contaCorrente.getNumeroConta(), contaCorrente);
            System.out.println("Conta corrente cadastrada com sucesso.");
            System.out.println("Conta Numero: "+ contaCorrente.getNumeroConta()+" Agencia: " + contaCorrente.getAgencia());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cadastrarContaPoupanca(Scanner scanner) {
        System.out.print("Digite o CPF do cliente:" +
                "\nCPF:");
        String cpf = scanner.nextLine();

        AbstractCliente cliente = clientes.get(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite o código da agência:" +
                "\nCódigo:");
        String agencia = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Digite a senha da conta:" +
                "\nSenha:");
        String senha = scanner.nextLine();
        scanner.nextLine();

        try {
            AbstractConta.validarSenhaCriacao(senha);
            ContaPoupanca contaPoupanca = new ContaPoupanca(agencia, cliente, senha);
            contasPoupanca.put(contaPoupanca.getAgencia() + contaPoupanca.getNumeroConta(), contaPoupanca);
            System.out.println("Conta poupança cadastrada com sucesso.");
            System.out.println("Conta Numero: "+ contaPoupanca.getNumeroConta()+" Agencia: " + contaPoupanca.getAgencia());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void efetuarDeposito(Scanner scanner) {
        System.out.print("======Selecione o Tipo de Conta=======\n" +
                         "1- Conta Corrente | 2 - Conta Poupança\n" +
                         "Conta:");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        Map<String, ? extends AbstractConta> contas;
        if (tipoConta == 1) {
            contas = contasCorrente;
        } else if (tipoConta == 2) {
            contas = contasPoupanca;
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        try {
            AbstractConta contaOrigem = AbstractConta.buscarConta(scanner, contas);
            AbstractConta.validarSenha(scanner, contaOrigem);

            System.out.println("Digite o valor do depósito:");
            double valor = scanner.nextDouble();

            contaOrigem.depositar(valor);
            System.out.println("    Status\n" +
                    "Conta Numero: "+ contaOrigem.getNumeroConta()+" Agencia: " + contaOrigem.getAgencia()+
                    "\nCliente: "+ contaOrigem.getNome());
            System.out.printf("\nSaldo: R$%.2f", contaOrigem.getSaldo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void efetuarSaque(Scanner scanner) {
        System.out.print("======Selecione o Tipo de Conta=======\n" +
                "1- Conta Corrente | 2 - Conta Poupança\n" +
                "Conta:");
        int tipoConta = scanner.nextInt();
            scanner.nextLine();

        Map<String, ? extends AbstractConta> contas;
        if (tipoConta == 1) {
            contas = contasCorrente;
        } else if (tipoConta == 2) {
            contas = contasPoupanca;
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        try {
            AbstractConta contaOrigem = AbstractConta.buscarConta(scanner, contas);
            AbstractConta.validarSenha(scanner, contaOrigem);

            System.out.println("Digite o valor do saque:");
            double valor = scanner.nextDouble();

            contaOrigem.sacar(valor);

            System.out.println("    Status\n" +
                    "Conta Numero: "+ contaOrigem.getNumeroConta()+" Agencia: " + contaOrigem.getAgencia()+
            "\nCliente: "+ contaOrigem.getNome());
            System.out.printf("\nSaldo: R$%.2f", contaOrigem.getSaldo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void efetuarTransferencia(Scanner scanner) {
        System.out.print("======Selecione o Tipo de Conta da Origem=======\n" +
                "1- Conta Corrente | 2 - Conta Poupança\n" +
                "Conta:");
        int tipoContaOrigem = scanner.nextInt();
        scanner.nextLine();

        Map<String, ? extends AbstractConta> contasOrigem;
        if (tipoContaOrigem == 1) {
            contasOrigem = contasCorrente;
        } else if (tipoContaOrigem == 2) {
            contasOrigem = contasPoupanca;
        } else {
            System.out.println("Tipo de conta inválido para a origem.");
            return;
        }

        System.out.print("\n======Selecione o Tipo de Conta do Destino=======\n" +
                "1- Conta Corrente | 2 - Conta Poupança\n" +
                "Conta:");
        int tipoContaDestino = scanner.nextInt();
        scanner.nextLine();

        Map<String, ? extends AbstractConta> contasDestino;
        if (tipoContaDestino == 1) {
            contasDestino = contasCorrente;
        } else if (tipoContaDestino == 2) {
            contasDestino = contasPoupanca;
        } else {
            System.out.println("Tipo de conta inválido para o destino.");
            return;
        }

        try {
            AbstractConta contaOrigem = AbstractConta.buscarConta(scanner, contasOrigem);
            AbstractConta.validarSenha(scanner, contaOrigem);

            System.out.println("Digite o código da agência de destino:");
            String agenciaDestino = scanner.nextLine();

            System.out.println("Digite o número da conta de destino:");
            String numeroContaDestino = scanner.nextLine();

            AbstractConta contaDestino = contasDestino.get(agenciaDestino + numeroContaDestino);
            if (contaDestino == null) {
                System.out.println("Conta de destino não encontrada.");
                return;
            }

            System.out.println("Digite o valor da transferência:");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor < 0) {
                System.out.println("Valor inválido.");
                return;
            }

            if (valor > contaOrigem.getSaldo()) {
                System.out.println("Saldo insuficiente na conta de origem.");
                return;
            }
            contaOrigem.transferir(valor, contaDestino);

            System.out.println("\nStatus Conta Origem");
            System.out.print("Conta Numero: "+ contaOrigem.getNumeroConta()+" Agencia: " + contaOrigem.getAgencia()+
                    "\nCliente: "+ contaOrigem.getNome());
            System.out.printf("\nSaldo: %.2f\n", contaOrigem.getSaldo());

            System.out.println("\nStatus Conta Destino");
            System.out.print("Conta Numero: "+ contaDestino.getNumeroConta()+" Agencia: " + contaDestino.getAgencia()+
                    "\nCliente: "+ contaDestino.getNome());
            System.out.printf("\nSaldo: %.2f\n", contaDestino.getSaldo());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
