package br.com.guilherme.gastos.service.sessao;

import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.dto.sessao.request.RequestRegistrarDTO;
import br.com.guilherme.gastos.exception.EmailJaCadastradoException;
import br.com.guilherme.gastos.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistrarService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrarService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrar(RequestRegistrarDTO requestDTO) throws EmailJaCadastradoException {

        var optionalUsuario = usuarioRepository.findByEmail(requestDTO.getEmail());

        if(optionalUsuario.isPresent()){
            throw new EmailJaCadastradoException();
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(requestDTO.getEmail());
        usuario.setNome(requestDTO.getNome());
        usuario.setSenha(passwordEncoder.encode(requestDTO.getSenha()));

        usuarioRepository.save(usuario);

    }
}
