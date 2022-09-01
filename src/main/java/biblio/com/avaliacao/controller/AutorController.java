package biblio.com.avaliacao.controller;

import biblio.com.avaliacao.model.Autor;
import biblio.com.avaliacao.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;
    // A anotação @RequestMapping permite definir uma rota
    @RequestMapping(value = "/autor", method = RequestMethod.GET)
    public List<Autor> buscarAutores() {
        return autorRepository.findAll();
    }
}
