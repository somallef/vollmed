package med.voll.VollMedApi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    private SecurityFilter securityFilter;

    @Bean //O @Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realize a sua injeção de dependência em outras classes.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // o objeto http é fornecido pelo próprio spring
        return http
                .csrf().disable() //desabilita o tratamento contra ataques csrf, pois o padrão de autenticação que será usado (jwt) já garante proteção contra esse tipo de ataque
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // informa ao Spring que a autenticação será do tipo Stateless e não Stateful, isto é, não guardaremos status de autenticação
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //executa o filtro da API antes do filtro do Spring
                .build(); //cria o objeto a ser retornado
    }

    //Este método "ensina" ao Spring como deve ser injetado o objeto AuthenticationManager no Controller de autenticação
    @Bean //O @Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realize a sua injeção de dependência em outras classes.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // determina que o spring deve usar este algoritmo para hashing de senhas
    }
}
