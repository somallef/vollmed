package med.voll.VollMedApi.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean //indica que estamos devolvendo um objeto para o Spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // o objeto http é fornecido pelo próprio spring
        return http
                .csrf().disable() //desabilita o tratamento contra ataques csrf, pois o padrão de autenticação que será usado (jwt) já garante proteção contra esse tipo de ataque
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // informa ao Spring que a autenticação será do tipo Stateless e não Stateful, isto é, não guardaremos status de autenticação
                .and().build(); //cria o objeto a ser retornado
    }
}
