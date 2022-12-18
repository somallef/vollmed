package med.voll.VollMedApi.controller;

import jakarta.validation.Valid;
import med.voll.VollMedApi.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    @CacheEvict(value = "ListagemMedicos", allEntries = true)
    public ResponseEntity cadastrar(
            @RequestBody //recebe os dados do corpo da requisição
            @Valid //executa as validações do bean validation
            DadosCadastroMedico dados,
            UriComponentsBuilder uriBuilder) {

        var medico = new Medico(dados);
        repository.save(medico);
        //constroi a URI para o cabeçalho Location
        var uri = uriBuilder
                .path("/medicos/{id}")
                .buildAndExpand(medico.getId())
                .toUri();
        return ResponseEntity
                .created(uri) //cria o cabeçalho location com o código 201
                .body(new DadosDetalhamentoMedico(medico)); // devolve o conteudo do recurso criado

    }

    @GetMapping
    @Cacheable(value = "ListagemMedicos")
    public ResponseEntity<Page<DadosListagemMedico>> listar(
            @PageableDefault(size = 10, sort = { "crm" }) //altera as configurações padrão de paginação e ordenação do Spring
            Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao) //retorna uma lista de médicos
                .map(DadosListagemMedico::new); //instancia um obj de listagem e envia o list de médicos para o seu construtor
        return ResponseEntity.ok(page); //retorna conteudo e código 200
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = "ListagemMedicos", allEntries = true)
    public ResponseEntity atualizar(
            @RequestBody //recebe os dados do corpo da requisição
            @Valid //executa as validações do bean validation
            DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "ListagemMedicos", allEntries = true)
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build(); //retorna código 204

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
