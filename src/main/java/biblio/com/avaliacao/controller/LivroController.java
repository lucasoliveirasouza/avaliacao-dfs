package biblio.com.avaliacao.controller;

import biblio.com.avaliacao.model.Livro;
import biblio.com.avaliacao.repository.LivroRepository;
import biblio.com.avaliacao.responses.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    @ApiOperation(value="Mostra lista de livros")
    @GetMapping("/livro")
    public List<Livro> listarLivros() {

        return livroRepository.findAll();
    }

    @ApiOperation(value="Mostra um livro com base no id informado")
    @GetMapping("/livro/{id}")
    public ResponseEntity<Livro> getByIdLivro(@PathVariable(value = "id") long id)
    {
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isPresent())
            return new ResponseEntity<Livro>(livro.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @ApiOperation(value="Atualiza um livro")
    @PutMapping("/livro/{id}")
    public ResponseEntity<Response<Livro>> atualizarLivro(@PathVariable(value = "id") long id, @Valid @RequestBody
    Livro novoLivro, BindingResult result)
    {
        Optional<Livro> antigoLivro = livroRepository.findById(id);
        Response<Livro> response = new Response<Livro>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        if(antigoLivro.isPresent()){
            response.setData(novoLivro);
            livroRepository.save(novoLivro);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value="Deleta um livro com base no id informado")
    @DeleteMapping("/livro/{id}")
    public ResponseEntity<Object> deletarLivro(@PathVariable(value = "id") long id)
    {
        Optional<Livro> genero = livroRepository.findById(id);
        if(genero.isPresent()){
            livroRepository.delete(genero.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
