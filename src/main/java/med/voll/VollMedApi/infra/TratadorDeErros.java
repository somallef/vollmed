package med.voll.VollMedApi.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // esta anotação diz ao spring que esta classe é responsável por tratar os erros
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class) // por meio desta anotação, o spring entende que quando uma exceção deste tipo for lançada, é este método que deve ser chamado
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // por meio desta anotação, o spring entende que quando uma exceção deste tipo for lançada, é este método que deve ser chamado
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros
                .stream()
                .map(DadosErroValidacao::new).toList() // converte erros em DadosErroValidacao
        );

    }


    private record DadosErroValidacao(String campo, String mensagem) { // já que este dto só será usado aqui, não há a necessidade de cria-lo em arquivo separado
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
