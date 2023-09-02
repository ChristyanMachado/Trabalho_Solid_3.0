package Banco.Contas;

import Banco.Cliente.AbstractCliente;

public class ContaPoupanca extends AbstractConta{
    public ContaPoupanca(String agencia, AbstractCliente cliente) {
        super(agencia, cliente);
    }

    @Override
    public Contas getTipoConta() {
        return Contas.Poupanca;
    }
}
