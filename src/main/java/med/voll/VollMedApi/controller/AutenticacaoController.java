package med.voll.VollMedApi.controller;

import jakarta.validation.Valid;
import med.voll.VollMedApi.domain.usuario.DadosAutenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity login(
            @RequestBody
            @Valid
            DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); //passamos os dados do dto do dominio da aplicação para que o spring crie o seu proprio dto
        var auth = authManager.authenticate(token); //passamos o token (dto do spring) para que seja realizada a autenticação

        return ResponseEntity.ok().build();

    }
}
