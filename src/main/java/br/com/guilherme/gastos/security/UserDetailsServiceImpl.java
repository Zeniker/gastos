package br.com.guilherme.gastos.security;

import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findByEmail(email);

        if(optionalUsuario.isEmpty()){
            throw new UsernameNotFoundException(email);
        }

        return new UserDetailsImpl(optionalUsuario.get());
    }
}
