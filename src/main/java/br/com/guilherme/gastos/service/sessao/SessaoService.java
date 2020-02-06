package br.com.guilherme.gastos.service.sessao;

import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.dto.sessao.request.RequestLoginDTO;
import br.com.guilherme.gastos.dto.sessao.response.ResponseLoginDTO;
import br.com.guilherme.gastos.exception.UsuarioNaoEncontradoException;
import br.com.guilherme.gastos.repository.UsuarioRepository;
import br.com.guilherme.gastos.security.SecurityConstants;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SessaoService {

    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;

    @Value("${aplicacao.token.key}")
    private String jwtSecret;

    @Value("${aplicacao.token.expiration}")
    private Integer jwtExpiration;

    public SessaoService(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
    }

    private Authentication validaCredenciais(RequestLoginDTO requestDto) throws BadCredentialsException {

        var authenticationToken = new UsernamePasswordAuthenticationToken(requestDto.getUsuario(),
                requestDto.getSenha());

        return authenticationManager.authenticate(authenticationToken);

    }

    private String montaToken(Authentication authentication){
        var user = ((UserDetails) authentication.getPrincipal());
        var roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        var signingKey = jwtSecret.getBytes();

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtExpiration * 1000)))
                .claim("rol", roles)
                .compact();
    }

    /**
     * Realiza autenticação a partir das credenciais enviadas
     *
     * @param requestDto Credenciais
     * @return Objeto contendo o token de autenticação
     */
    public ResponseLoginDTO autenticar(RequestLoginDTO requestDto){

        var authentication = validaCredenciais(requestDto);

        return new ResponseLoginDTO(montaToken(authentication));
    }

    public Usuario getUsuarioAtual() throws UsuarioNaoEncontradoException {

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var optionalUsuario = usuarioRepository.findByEmail(email);

        return optionalUsuario.orElseThrow(UsuarioNaoEncontradoException::new);

    }


}
