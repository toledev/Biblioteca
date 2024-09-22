public class Livro {
    private int idLivro;
    private String titulo;
    private Autor autor;
    private Boolean disponivel;
    private String dataCadastro;
    private String dataAtt;

    // Criando construtor
    public Livro(int idLivro, String titulo, Autor autor, Boolean disponivel) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
        this.dataCadastro = "22/09/2024";
        this.dataAtt = "22/09/2024";
    }
    // Getters
    public int getIdLivro() {
        return idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // Setters
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setDataAtualizacao(String dataAtt) {
        this.dataAtt = dataAtt;
    }
}
