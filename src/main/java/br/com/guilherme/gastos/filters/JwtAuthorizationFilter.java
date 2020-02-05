package br.com.guilherme.gastos.filters;

import br.com.guilherme.gastos.config.SecurityConstants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Log
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final String jwtSecret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  String jwtSecret) {
        super(authenticationManager);

        this.jwtSecret = jwtSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String path = request.getRequestURI();
        if ("/sessao/login".equals(path)) {
            chain.doFilter(request, response);
            return;
        }

        var authentication = getAuthentication(request);

        if(authentication == null){
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest httpServletRequest){

        var token = httpServletRequest.getHeader(SecurityConstants.TOKEN_HEADER);
        if(!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            try{
                var signingKey = jwtSecret.getBytes();

                var parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));

                var username = parsedToken
                        .getBody()
                        .getSubject();

                var authorities = ((List<?>) parsedToken.getBody().get("rol")).stream()
                        .map(authority -> new SimpleGrantedAuthority((String) authority))
                        .collect(Collectors.toList());

                if (!StringUtils.isEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            } catch (ExpiredJwtException exception) {
                logger.warn(String.format("Request to parse expired JWT : %s failed : %s", token, exception.getMessage()),
                        exception);

            } catch (UnsupportedJwtException exception) {
                logger.warn(String.format("Request to parse unsupported JWT : %s failed : %s", token, exception.getMessage()),
                        exception);

            } catch (MalformedJwtException exception) {
                logger.warn(String.format("Request to parse invalid JWT : %s failed : %s", token, exception.getMessage()),
                        exception);

            } catch (IllegalArgumentException exception) {
                logger.warn(String.format("Request to parse empty or null JWT : %s failed : %s", token, exception.getMessage()),
                        exception);

            }
        }

        return null;

    }
}
