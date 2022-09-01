package biblio.com.avaliacao.controller;

import biblio.com.avaliacao.model.Autor;
import biblio.com.avaliacao.repository.AutorRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;


    @ApiOperation(value="Mostra lista de autores")
    @GetMapping("/autor")
    public List<Autor> buscarAutores() {
        return autorRepository.findAll();
    }
}
