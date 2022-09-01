package biblio.com.avaliacao.controller;

import biblio.com.avaliacao.model.Autor;
import biblio.com.avaliacao.repository.AutorRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;


    @ApiOperation(value="Mostra lista de autores")
    @GetMapping("/autor")
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @ApiOperation(value="Mostra um autor com base no id informado")
    @GetMapping("/autor/{id}")
    public ResponseEntity<Autor> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Autor> autor = autorRepository.findById(id);
        if(autor.isPresent())
            return new ResponseEntity<Autor>(autor.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
