package biblio.com.avaliacao.controller;

import biblio.com.avaliacao.model.Editora;
import biblio.com.avaliacao.repository.EditoraRepository;
import biblio.com.avaliacao.responses.MessageResponse;
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
public class EditoraController {
    @Autowired
    private EditoraRepository editoraRepository;

    @ApiOperation(value="Mostra lista de editoras")
    @GetMapping("/editora")
    public List<Editora> listarEditora() {

        return editoraRepository.findAll();
    }

    @ApiOperation(value="Mostra uma editora com base no id informado")
    @GetMapping("/editora/{id}")
    public ResponseEntity<Editora> getByIdEditora(@PathVariable(value = "id") long id)
    {
        Optional<Editora> editora = editoraRepository.findById(id);
        if(editora.isPresent())
            return new ResponseEntity<Editora>(editora.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value="Cadastra uma nova editora")
    @PostMapping("/editora")
    public ResponseEntity<?> cadastrarEditora(@Valid @RequestBody Editora editora, BindingResult result)
    {
        Response<Editora> response = new Response<Editora>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        if (editoraRepository.existsByNome(editora.getNome())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erro: Já existe uma editora com esse nome"));
        }
        editoraRepository.save(editora);
        response.setData(editora);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value="Atualiza uma editora")
    @PutMapping("/editora/{id}")
    public ResponseEntity<Response<Editora>> atualizarEditora(@PathVariable(value = "id") long id, @Valid @RequestBody
    Editora novaEditora, BindingResult result)
    {
        Optional<Editora> antigaEditora = editoraRepository.findById(id);
        Response<Editora> response = new Response<Editora>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        if(antigaEditora.isPresent()){
            Editora editora = antigaEditora.get();
            editora.setNome(novaEditora.getNome());
            response.setData(editora);
            editoraRepository.save(editora);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value="Deleta uma editora com base no id informado")
    @DeleteMapping("/editora/{id}")
    public ResponseEntity<Object> deletarEditora(@PathVariable(value = "id") long id)
    {
        Optional<Editora> editora = editoraRepository.findById(id);
        if(editora.isPresent()){
            editoraRepository.delete(editora.get());
            return ResponseEntity.ok("Editora excluída com sucesso");
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
