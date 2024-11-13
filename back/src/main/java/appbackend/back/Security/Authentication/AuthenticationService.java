package appbackend.back.Security.Authentication;


import appbackend.back.Security.JwtService;
import appbackend.back.Security.MyUserDetailsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static appbackend.back.Security.Authentication.AuthenticationResponse.*;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(PasswordEncoder passwordEncoder, JwtService jwtService, MyUserDetailsService myUserDetailsService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = myUserDetailsService.loadUserByUsername(request.getUsername());
        Object userInfo = myUserDetailsService.getUserInfoByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().userinfo(userInfo).token(jwtToken).build();
    }
}
