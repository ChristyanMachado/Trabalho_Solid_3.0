package Banco.Contas;

import Banco.Cliente.AbstractCliente;

public class ContaPoupanca extends AbstractConta{
    public ContaPoupanca(String agencia, AbstractCliente cliente, String senha) {
        super(agencia, cliente, senha);
    }

    @Override
    public TipoConta getTipoConta() {
        return TipoConta.Poupanca;
    }
}
