package appbackend.back.config;

import appbackend.back.Security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    private final AuthenticationProvider authenticationProvider;

    private final UserDetailsService userDetailsService;

    //loc request
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/vdt/all").permitAll()
                        .requestMatchers("/vdt/create").hasAuthority("admin")
                        .requestMatchers("/vdt/update/**").hasAuthority("admin")
                        .requestMatchers("/vdt/delete/**").hasAuthority("admin")
                        .requestMatchers("/order/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/order/all").permitAll()
                        .requestMatchers(HttpMethod.POST,"/order/create").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/**").permitAll()
                        .anyRequest().permitAll()
                )
                .authenticationProvider(authenticationProvider) // Use authenticationProvider bean directly
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }




}
