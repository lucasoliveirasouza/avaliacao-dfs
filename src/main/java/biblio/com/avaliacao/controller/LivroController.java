package biblio.com.avaliacao.controller;


import biblio.com.avaliacao.model.Autor;
import biblio.com.avaliacao.model.Livro;
import biblio.com.avaliacao.repository.AutorRepository;
import biblio.com.avaliacao.repository.LivroRepository;
import biblio.com.avaliacao.responses.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @ApiOperation(value="Mostra lista de livros")
    @GetMapping("/livro")
    public List<Livro> listarLivros() {

        return livroRepository.findAll();
    }

    @ApiOperation(value="Cadastra um novo livro")
    @PostMapping("/livro")
    public ResponseEntity<Response<Livro>> cadastrarLivro(@Valid @RequestBody Livro livro, BindingResult result)
    {
        Response<Livro> response = new Response<Livro>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        livroRepository.save(livro);
        response.setData(livro);
        return ResponseEntity.ok(response);
    }
}
