package biblio.com.avaliacao.repository;

import biblio.com.avaliacao.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Boolean existsByNome(String nome);
}