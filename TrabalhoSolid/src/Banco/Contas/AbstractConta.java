package Banco.Contas;

import Banco.Cliente.AbstractCliente;
import Banco.Cliente.ClientePessoaFisica;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public abstract class AbstractConta {
    Map<String, AbstractConta> contas = new HashMap<>();
    private String agencia;
    private String numeroConta;
    private String senha;
    private AbstractCliente cliente;
    private double saldo;

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }


    public AbstractConta(String agencia, AbstractCliente cliente) {
        this.agencia = agencia;
        this.numeroConta = gerarNumeroAleatorio();
        this.senha = gerarNumeroAleatorio();
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public void depositar(double valor) throws Exception {
        if (valor <= 0)
            throw new Exception("Valor de depósito inválido");

        saldo += valor;
        System.out.println("Depósito realizado com sucesso.");
    }

    public void sacar(double valor, String senha) throws Exception {
        if (valor <= 0)
            throw new Exception("Valor de saque inválido");

        if (!this.senha.equals(senha))
            throw new Exception("Senha incorreta");

        if (saldo < valor)
            throw new Exception("Saldo insuficiente");

        saldo -= valor;
        System.out.println("Saque realizado com sucesso.");
    }

    public void transferir(double valor, AbstractConta destino, String senha) throws Exception {
        if (valor <= 0)
            throw new Exception("Valor de transferência inválido");

        if (!this.senha.equals(senha))
            throw new Exception("Senha incorreta");

        if (saldo < valor)
            throw new Exception("Saldo insuficiente");

        saldo -= valor;
        destino.depositar(valor);
        System.out.println("Transferência realizada com sucesso.");
    }

    public abstract Contas getTipoConta();

    private String gerarNumeroAleatorio() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
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
