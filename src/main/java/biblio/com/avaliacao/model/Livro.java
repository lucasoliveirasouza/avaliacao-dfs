package biblio.com.avaliacao.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String titulo;

    private int anoPublicacao;

    @Column(nullable = false)
    private String isbn;

    private int quantidade;

    public long getId() {
        return id;
    }

    @NotEmpty(message="Você precisa informar o titulo do livro")
    @Length(min=2, max=200, message="O titulo do livro deverá ter entre 2 e 200 caracteres")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(long id) {
        this.id = id;
    }
}
