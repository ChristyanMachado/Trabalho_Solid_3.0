package Banco.Cliente;

public abstract class AbstractCliente {
private String cpf;
private String nome;

    protected AbstractCliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

}
