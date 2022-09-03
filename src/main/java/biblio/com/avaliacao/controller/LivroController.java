package biblio.com.avaliacao.controller;


import biblio.com.avaliacao.model.Livro;
import biblio.com.avaliacao.repository.LivroRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    @ApiOperation(value="Mostra lista de livros")
    @GetMapping("/livro")
    public List<Livro> listarLivros() {

        return livroRepository.findAll();
    }


}
