package biblio.com.avaliacao.repository;

import biblio.com.avaliacao.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
    Boolean existsByNome(String nome);
}
