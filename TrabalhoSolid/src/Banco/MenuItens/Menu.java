package Banco.MenuItens;

import Banco.Cliente.ClientePessoaFisica;
import Banco.Contas.AbstractConta;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static void exibirMenu() {
        Map<String, ClientePessoaFisica> clientes = new HashMap<>();
        Map<String, AbstractConta> contas = new HashMap<>();
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Cliente Pessoa Física");
            System.out.println("2 - Cadastrar Conta Corrente");
            System.out.println("3 - Cadastrar Conta Poupança");
            System.out.println("4 - Efetuar Depósito");
            System.out.println("5 - Efetuar Saque");
            System.out.println("6 - Efetuar Transferência");
            System.out.println("7 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 7:
                    loop = false;
            }

        }
    }

}
