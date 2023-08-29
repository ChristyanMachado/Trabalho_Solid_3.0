package Banco.Contas;

import Banco.Cliente.AbstractCliente;

import java.util.Random;

public abstract class AbstractConta {
    private String agencia;
    private String numeroConta;
    private String senha;
    private AbstractCliente cliente;
    private double saldo;

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


    private String gerarNumeroAleatorio() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }


}
