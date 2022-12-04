package med.voll.VollMedApi.controller;

import jakarta.validation.Valid;
import med.voll.VollMedApi.medico.DadosCadastroMedico;
import med.voll.VollMedApi.medico.Medico;
import med.voll.VollMedApi.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
