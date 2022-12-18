package med.voll.VollMedApi.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // esta anotação diz ao spring que esta classe é responsável por tratar os erros
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class) // por meio desta anotação, o spring entende que quando uma exceçõa deste tipo for lançada, é este método que deve ser chamado
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();

    }
}
