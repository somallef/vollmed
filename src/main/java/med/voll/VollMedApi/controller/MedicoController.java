package med.voll.VollMedApi.controller;

import jakarta.validation.Valid;
import med.voll.VollMedApi.medico.DadosCadastroMedico;
import med.voll.VollMedApi.medico.DadosListagemMedico;
import med.voll.VollMedApi.medico.Medico;
import med.voll.VollMedApi.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(
            @RequestBody //recebe os dados do corpo da requisição
            @Valid //executa as validações do bean validation
            DadosCadastroMedico dados) {
        repository.save(new Medico(dados));

    }

    @GetMapping
    public List<DadosListagemMedico> listar() {
        return repository.findAll() //retorna uma lista de médicos
                .stream() //cria um fluxo/pipeline de transformação que será executado pelo map
                .map(DadosListagemMedico::new) //instancia um obj de listagem e envia o list de médicos para o seu construtor
                .toList(); //converte para o tipo lista
    }
}
