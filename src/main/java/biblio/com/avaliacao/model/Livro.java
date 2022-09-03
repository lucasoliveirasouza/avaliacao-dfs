package biblio.com.avaliacao.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Editora editora;

    @ManyToOne
    private Genero genero;


    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @NotEmpty(message="Você precisa informar o titulo do livro")
    @Length(min=2, max=200, message="O titulo do livro deverá ter entre 2 e 200 caracteres")
    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public int getAnoPublicacao() {

        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {

        this.anoPublicacao = anoPublicacao;
    }

    public String getIsbn() {

        return isbn;
    }

    @NotEmpty(message="Você precisa informar o ISBN do livro")
    @Length(min=10, max=200, message="O ISBN do livro deverá ter entre 10 e 200 caracteres")

    public void setIsbn(String isbn) {

        this.isbn = isbn;
    }

    public int getQuantidade() {

        return quantidade;
    }

    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }
}
