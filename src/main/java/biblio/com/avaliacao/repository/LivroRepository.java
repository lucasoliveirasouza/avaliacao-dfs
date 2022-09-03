package biblio.com.avaliacao.repository;

import biblio.com.avaliacao.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
