package biblio.com.avaliacao.model;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message="Você precisa informar o titulo do livro")
    @Length(min=2, max=200, message="O titulo do livro deverá ter entre 2 e 200 caracteres")
    private String titulo;

    private int anoPublicacao;

    @Column(nullable = false)
    @NotEmpty(message="Você precisa informar o ISBN do livro")
    @Length(min=10, max=200, message="O ISBN do livro deverá ter entre 10 e 200 caracteres")
    private String isbn;

    private int quantidade;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Editora editora;

    @ManyToOne
    private Genero genero;
}
