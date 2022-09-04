package biblio.com.avaliacao.controller;

import biblio.com.avaliacao.model.Genero;
import biblio.com.avaliacao.repository.GeneroRepository;
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
public class GeneroController {
    @Autowired
    private GeneroRepository generoRepository;

    @ApiOperation(value="Mostra lista de gêneros")
    @GetMapping("/genero")
    public List<Genero> listarGenero() {

        return generoRepository.findAll();
    }

    @ApiOperation(value="Mostra um gênero com base no id informado")
    @GetMapping("/genero/{id}")
    public ResponseEntity<Genero> getByIdGenero(@PathVariable(value = "id") long id)
    {
        Optional<Genero> genero = generoRepository.findById(id);
        if(genero.isPresent())
            return new ResponseEntity<Genero>(genero.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value="Cadastra um novo gênero")
    @PostMapping("/genero")
    public ResponseEntity<?> cadastrarGenero(@Valid @RequestBody Genero genero, BindingResult result)
    {
        Response<Genero> response = new Response<Genero>();
        if (generoRepository.existsByNome(genero.getNome())) {
            response.getErrors().add("Já existe um gênero com esse nome");
        }
        if (result.hasErrors() || response.getErrors().size() > 0) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        generoRepository.save(genero);
        response.setData(genero);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value="Atualiza um gênero")
    @PutMapping("/genero/{id}")
    public ResponseEntity<Response<Genero>> atualizarGenero(@PathVariable(value = "id") long id, @Valid @RequestBody
    Genero novoGenero, BindingResult result)
    {
        Optional<Genero> antigoGenero = generoRepository.findById(id);
        Response<Genero> response = new Response<Genero>();

        if (result.hasErrors() ) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        if(antigoGenero.isPresent()){
            Genero genero = antigoGenero.get();
            genero.setNome(novoGenero.getNome());
            response.setData(genero);
            generoRepository.save(genero);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value="Deleta um gênero com base no id informado")
    @DeleteMapping("/genero/{id}")
    public ResponseEntity<Object> deletarGenero(@PathVariable(value = "id") long id)
    {
        Optional<Genero> genero = generoRepository.findById(id);
        if(genero.isPresent()){
            generoRepository.delete(genero.get());
            return ResponseEntity.ok("Gênero excluído com sucesso");
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
