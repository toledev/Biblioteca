import java.util.Date;

public static void main(String[] args) {
    Biblioteca biblioteca = new Biblioteca();
    biblioteca.carregarLivros("livro.txt");
    Scanner scanner = new Scanner(System.in);

    Autor autor1 = new Autor(1, "Olavo de Carvalho");
    Livro livro1 = new Livro(1, "As tranças do rei careca", autor1);

    Autor autor2 = new Autor(2, "George R. Martin");
    Livro livro2 = new Livro(2, "As crônicas de gelo e fogo", autor2);

    biblioteca.adicionarAutor(String.valueOf(autor1));
    biblioteca.adicionarAutor(String.valueOf(autor2));

    biblioteca.adicionarLivro(livro1);
    biblioteca.adicionarLivro(livro2);

    while (true) {
        System.out.println("\nMenu:");
        System.out.println("1. Adicionar livro");
        System.out.println("2. Listar livros");
        System.out.println("3. Emprestar livro");
        System.out.println("4. Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            biblioteca.adicioneLivros();
        } else if (opcao == 2) {
            biblioteca.listarLivros();
        } else if (opcao == 4) {
            biblioteca.salvar("livros.txt");
            System.out.println("Encerrando o sistema.");
            break;
        } else {
            System.out.println("Opção inválida!\nEncerrando o sistema.");
            break;
        }
    }


}