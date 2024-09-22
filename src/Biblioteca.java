import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private List<Livro> livros;
    private List<Autor> autores;
    private int proximoIdLivro = 1;
    private int proximoIdAutor = 1;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.autores = new ArrayList<>();
    }

    public Autor adicionarAutor(String nome) {
        Autor novoAutor = new Autor(proximoIdAutor++, nome);
        autores.add(novoAutor);
        return novoAutor;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void listarAutores() {
        for (Autor autor : autores) {
            System.out.println(autor.getIdAutor() + ": " + autor.getNomeAutor());
        }
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            String statusDisp = livro.isDisponivel() ? "(Este livro está disponível para empréstimo!)" : "(Este livro não está disponível para empréstimo.)";
            System.out.println(livro.getIdLivro() + ": " + livro.getTitulo() + " - Autor: " + livro.getAutor().getNomeAutor() + " " + statusDisp);
        }
    }

    public void adicioneLivros() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o título do livro: ");
        String titulo = scanner.nextLine();

        System.out.println("Informe o nome do autor: ");
        String nomeAutor = scanner.nextLine();

        Boolean disponivel = false;
        while (true) {
            System.out.println("Deseja liberar o empréstimo desse livro? (Digite 1 para SIM, 2 para NÃO)");
            int resposta = scanner.nextInt();
            if (resposta == 1) {
                disponivel = true;
                System.out.println("Livro liberado para empréstimo!");
                break;
            } else if (resposta == 2) {
                disponivel = false;
                System.out.println("Livro bloqueado para empréstimo!");
                break;
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

        Autor autorExiste = null;
        for (Autor autor : autores) {
            if (autor.getNomeAutor().equalsIgnoreCase(nomeAutor)) {
                autorExiste = autor;
                System.out.println("Este autor já existe!");
                break;
            }
        }

        Autor autor;
        if (autorExiste == null) {
            autor = adicionarAutor(nomeAutor);
        } else {
            autor = autorExiste;
        }

        Livro livro = new Livro(proximoIdLivro++, titulo, autor, disponivel);

        adicionarLivro(livro);

        System.out.println("Livro '" + titulo + "' do autor '" + autor.getNomeAutor() + "' adicionado com sucesso!");
    }

    public void salvar(String nomeArquivo) {
        String pasta = "C:\\Users\\ryanf\\Documents\\Biblioteca";
        File pastaDir = new File(pasta);

        // Verifica se a pasta existe, se não, cria
        if (!pastaDir.exists()) {
            pastaDir.mkdir();
        }

        // Define o caminho completo do arquivo
        File arquivo = new File(pastaDir, nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            // Salvando autores
            for (Autor autor : autores) {
                writer.write("autor;" + autor.getIdAutor() + ";" + autor.getNomeAutor());
                writer.newLine();
            }

            // Salvando livros, incluindo o estado de disponibilidade
            for (Livro livro : livros) {
                writer.write("livro;" + livro.getIdLivro() + ";" + livro.getTitulo() + ";" + livro.getAutor().getIdAutor() + ";" + livro.isDisponivel());
                writer.newLine();
            }

            System.out.println("Livros salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os livros: " + e.getMessage());
        }
    }

    public void carregarLivros(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados[0].equals("autor")) {
                    // Carrega autor
                    int id = Integer.parseInt(dados[1]);
                    String nomeAutor = dados[2];
                    Autor autor = new Autor(id, nomeAutor);
                    autores.add(autor);
                    proximoIdAutor = Math.max(proximoIdAutor, id + 1);
                } else if (dados[0].equals("livro")) {
                    // Carrega livro
                    int id = Integer.parseInt(dados[1]);
                    String titulo = dados[2];
                    int idAutor = Integer.parseInt(dados[3]);
                    boolean disponivel = Boolean.parseBoolean(dados[4]);

                    Autor autorExistente = null;

                    // Verifica se o autor já existe
                    for (Autor autor : autores) {
                        if (autor.getIdAutor() == idAutor) {
                            autorExistente = autor;
                            break;
                        }
                    }

                    // Adiciona o livro à lista
                    if (autorExistente != null) {
                        livros.add(new Livro(id, titulo, autorExistente, disponivel));
                        proximoIdLivro = Math.max(proximoIdLivro, id + 1); // Atualiza o próximo ID do livro
                    }
                }
            }
            System.out.println("Livros e autores carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os livros e autores: " + e.getMessage());
        }
    }

    public void excluirLivro() {
        Scanner scanner = new Scanner(System.in);

        listarLivros();

        System.out.println("Informe o ID do livro que deseja excluir: ");
        int idLivro = scanner.nextInt();

        Livro livroRemover = null;
        for (Livro livro : livros) {
            if (livro.getIdLivro() == idLivro) {
                livroRemover = livro;
                break;
            }
        }

        if (livroRemover != null) {
            livros.remove(livroRemover);
            System.out.println("Livro removido com sucesso!");

            salvar("livro.txt");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }


}
