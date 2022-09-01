package biblio.com.avaliacao.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nome;

    public long getId() {

        return id;
    }

    @NotEmpty(message="Você precisa informar o nome do gênero")
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
