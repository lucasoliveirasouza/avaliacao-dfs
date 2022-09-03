package biblio.com.avaliacao.model;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity

@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message="Você precisa informar o nome do autor")
    @Length(min=2, max=200, message="O nome deverá ter entre 2 e 200 caracteres")
    private String nome;
}
