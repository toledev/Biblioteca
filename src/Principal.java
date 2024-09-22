import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Carrega os livros
        biblioteca.carregarLivros("livro.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seja muito bem-vindo a nossa Biblioteca!\nMenu:");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Emprestar livro");
            System.out.println("4. Excluir livro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            // Validação da entrada de menu
            int opcao;
            try {
                opcao = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Opção inválida! Tente novamente.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    biblioteca.adicioneLivros();
                    break;
                case 2:
                    biblioteca.listarLivros();
                    break;
                case 3:
                    System.out.println("Funcionalidade de empréstimo ainda não implementada.");
                    break;
                case 4:
                    biblioteca.excluirLivro();
                    break;
                case 5:
                    biblioteca.salvar("livro.txt");
                    System.out.println("Sistema encerrado.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
