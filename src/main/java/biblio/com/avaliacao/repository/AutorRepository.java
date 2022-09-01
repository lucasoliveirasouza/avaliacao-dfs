package biblio.com.avaliacao.repository;

import biblio.com.avaliacao.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> { }