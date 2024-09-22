
import java.util.Date;

public class Autor {
    private int idAutor;
    private String nomeAutor;
    private String dataNascimento;

    public Autor(int idAutor, String nomeAutor) {
        this.idAutor = idAutor;
        this.nomeAutor = nomeAutor;
        this.dataNascimento = "Desconhecido";
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
}
