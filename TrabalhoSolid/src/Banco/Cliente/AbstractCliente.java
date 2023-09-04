package Banco.Cliente;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCliente {
    Map<String, ClientePessoaFisica> clientes = new HashMap<>();
private String cpf;
private String nome;

    protected AbstractCliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}
