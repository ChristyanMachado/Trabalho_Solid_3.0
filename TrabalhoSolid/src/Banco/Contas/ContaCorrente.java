package Banco.Contas;

import Banco.Cliente.AbstractCliente;

public class ContaCorrente extends AbstractConta{

    public ContaCorrente(String agencia, AbstractCliente cliente, String senha) {
        super(agencia, cliente, senha);
    }

    @Override
    public TipoConta getTipoConta() {
        return TipoConta.Corrente;
    }
}
