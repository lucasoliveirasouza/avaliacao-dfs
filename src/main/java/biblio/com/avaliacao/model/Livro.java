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
    private String nome;

    public long getId() {

        return id;
    }

    @NotEmpty(message="Você precisa informar o nome do livro")
    @Length(min=2, max=200, message="O nome do gênero deverá ter entre 2 e 200 caracteres")
    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public void setId(long id) {

        this.id = id;
    }
}
