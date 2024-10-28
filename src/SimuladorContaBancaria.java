import java.util.Scanner;

class Usuario {
    private String cpf;
    private String senha;

    public Usuario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }
}

class ContaBancaria {
    private String titular;
    private double saldo;
    private String senha;

    public ContaBancaria(String titular, String senha) {
        this.titular = titular;
        this.saldo = 0.0;
        this.senha = senha;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado com sucesso!%n", valor);
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public boolean sacar(double valor, String senha) {
        if (this.senha.equals(senha)) {
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                System.out.printf("Saque de R$ %.2f realizado com sucesso!%n", valor);
                return true;
            } else {
                System.out.println("Saque inválido. Verifique o valor e seu saldo.");
                return false;
            }
        } else {
            System.out.println("Senha incorreta. Saque não realizado.");
            return false;
        }
    }

    public void consultarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }
}

public class SimuladorContaBancaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cadastro do usuário
        System.out.print("Digite o CPF do titular: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a senha do titular: ");
        String senhaCadastro = scanner.nextLine();

        Usuario usuario = new Usuario(cpf, senhaCadastro);

        // Criação da conta bancária
        ContaBancaria conta = new ContaBancaria(usuario.getCpf(), senhaCadastro);

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Consultar Saldo");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor a ser depositado: R$ ");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    break;

                case 2:
                    System.out.print("Digite a senha para sacar: ");
                    String senhaSaque = scanner.next();
                    System.out.print("Digite o valor a ser sacado: R$ ");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque, senhaSaque);
                    break;

                case 3:
                    conta.consultarSaldo();
                    break;

                case 4:
                    System.out.println("Saindo... Obrigado!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 4);

        scanner.close();
    }
}
