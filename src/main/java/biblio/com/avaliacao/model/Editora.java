package biblio.com.avaliacao.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nome;

    public long getId() {

        return id;
    }

    @NotEmpty(message="Você precisa informar o nome da editora")
    @Length(min=2, max=200, message="O nome da editora deverá ter entre 2 e 200 caracteres")
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
