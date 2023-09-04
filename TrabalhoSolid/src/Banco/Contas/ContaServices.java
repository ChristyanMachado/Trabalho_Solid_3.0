package Banco.Contas;

import java.util.Scanner;

public class ContaServices {
    public static void validarSenha(Scanner scanner, AbstractConta conta) throws Exception {
        System.out.println("Digite a senha da conta:");
        String senha = scanner.nextLine();

        if (senha.length() != 4)
            throw new Exception("A senha deve ter exatamente 4 dígitos");

        try {
            Integer.parseInt(senha);
        } catch (NumberFormatException e) {
            throw new Exception("A senha deve conter apenas dígitos numéricos");
        }

        if (!senha.equals(String.valueOf(conta.getSenha())))
            throw new Exception("Senha incorreta");
    }



}
