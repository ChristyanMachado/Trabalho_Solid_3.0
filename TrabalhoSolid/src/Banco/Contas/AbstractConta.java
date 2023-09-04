package Banco.Contas;

import Banco.Cliente.AbstractCliente;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public abstract class AbstractConta {
    private static Map<String, AbstractConta> contas = new HashMap<>();
    private String agencia;
    private String numeroConta;
    private String senha;
    private AbstractCliente cliente;
    private double saldo;

    protected AbstractConta(String agencia, AbstractCliente cliente, String senha) {
        this.agencia = agencia;
        this.numeroConta = gerarNumeroAleatorio();
        this.senha = senha;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public String getAgencia() {
        return agencia;
    }
    public String getSenha() {
        return senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setSenha(String senha) {
        try {
            AbstractConta.validarSenhaCriacao(senha);
            this.senha = senha;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public AbstractCliente getCliente() {
        return cliente;
    }

    public abstract TipoConta getTipoConta();

    private String gerarNumeroAleatorio() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    public static void validarSenhaCriacao(String senha) throws Exception {
        if (senha.length() != 4)
            throw new Exception("A senha deve ter exatamente 4 dígitos");

        try {
            Integer.parseInt(senha);
        } catch (NumberFormatException e) {
            throw new Exception("A senha deve conter apenas dígitos numéricos");
        }
    }

    public void depositar(double valor) throws Exception {
        if (valor <= 0)
            throw new Exception("Valor de depósito inválido");

        saldo += valor;
        System.out.println("Depósito realizado com sucesso.");
    }

    public void sacar(double valor) throws Exception {
        if (valor <= 0)
            throw new Exception("Valor de saque inválido");

        if (saldo < valor)
            throw new Exception("Saldo insuficiente");

        saldo -= valor;
        System.out.println("Saque realizado com sucesso.");
    }

    public void transferir(double valor, AbstractConta destino) throws Exception {
        if (valor <= 0)
            throw new Exception("Valor de transferência inválido");

        if (saldo < valor)
            throw new Exception("Saldo insuficiente");

        saldo -= valor;
        destino.depositar(valor);
        System.out.println("Transferência realizada com sucesso.");
    }

    public static void validarSenha(Scanner scanner, AbstractConta conta) throws Exception {
        System.out.println("Digite a senha da conta:");
        String senha = scanner.nextLine();
        scanner.nextLine();

        if (conta.getSenha().equals(senha))
            throw new Exception("Senha incorreta");
    }

    public static AbstractConta buscarConta(Scanner scanner, Map<String, ? extends AbstractConta> contas) throws Exception {
        System.out.println("Digite o código da agência:");
        String agencia = scanner.nextLine();

        System.out.println("Digite o número da conta:");
        String numeroConta = scanner.nextLine();

        AbstractConta conta = contas.get(agencia + numeroConta);
        if (conta == null)
            throw new Exception("Conta não encontrada");

        return conta;
    }
    public static AbstractConta buscarConta(Scanner scanner) throws Exception {
        System.out.println("Digite o código da agência:");
        String agencia = scanner.nextLine();

        System.out.println("Digite o número da conta:");
        String numeroConta = scanner.nextLine();

        AbstractConta conta = contas.get(agencia + numeroConta);
        if (conta == null)
            throw new Exception("Conta não encontrada");

        return conta;
    }

}
