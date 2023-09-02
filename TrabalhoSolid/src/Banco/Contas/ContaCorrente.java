package Banco.Contas;

import Banco.Cliente.AbstractCliente;

public class ContaCorrente extends AbstractConta{
    public ContaCorrente(String agencia, AbstractCliente cliente) {
        super(agencia, cliente);
    }

    @Override
    public Contas getTipoConta() {
        return Contas.Corrente;
    }
}
