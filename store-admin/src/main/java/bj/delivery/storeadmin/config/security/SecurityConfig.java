package bj.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity // security 활성화
public class SecurityConfig {

    private List<String> OPEN_API = List.of(
            "/open-api/**"
    );

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // Cross-site request forgery --> rest api 서버이기에 불필요함
                .authorizeHttpRequests(x -> x
                        // resource에 대해서는 모든 요청 허용
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                        // SWAGGER 는 인증 없이 통과
                        .mvcMatchers(
                                SWAGGER.toArray(new String[0])
                        ).permitAll()

                        // OPEN-API 는 인증 없이 통과
                        .mvcMatchers(
                                OPEN_API.toArray(new String[0])
                        ).permitAll()

                        // 그 외 모든 요청은 인증 사용
                        .anyRequest().authenticated())

                .formLogin(Customizer.withDefaults()) // 디폴트 로그인 폼 사용하기
        ;

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        // hash로 암호화 (단방향)
        return new BCryptPasswordEncoder();
    }
}
