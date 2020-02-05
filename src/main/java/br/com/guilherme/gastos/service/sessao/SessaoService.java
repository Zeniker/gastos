package br.com.guilherme.gastos.service.sessao;

import br.com.guilherme.gastos.config.SecurityConstants;
import br.com.guilherme.gastos.dto.sessao.RequestLoginDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SessaoService {

    private final AuthenticationManager authenticationManager;

    @Value("${aplicacao.token.key}")
    private String jwtSecret;

    public SessaoService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private Authentication validaCredenciais(RequestLoginDto requestDto) throws BadCredentialsException {

        var authenticationToken = new UsernamePasswordAuthenticationToken(requestDto.getUsuario(),
                requestDto.getSenha());

        return authenticationManager.authenticate(authenticationToken);
    }

    private void injetaJwtNoResponse(HttpServletResponse httpServletResponse, Authentication authentication){

        var user = ((User) authentication.getPrincipal());
        var roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        var signingKey = jwtSecret.getBytes();

        var token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .claim("rol", roles)
                .compact();

        httpServletResponse.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);

    }

    public void login(RequestLoginDto requestDto, HttpServletResponse httpServletResponse){

        Authentication authentication = validaCredenciais(requestDto);

        injetaJwtNoResponse(httpServletResponse, authentication);

    }


}
