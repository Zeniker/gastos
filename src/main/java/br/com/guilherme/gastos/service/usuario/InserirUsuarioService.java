package br.com.guilherme.gastos.service.usuario;

import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.dto.sessao.request.RequestRegistrarDTO;
import br.com.guilherme.gastos.exception.EmailJaCadastradoException;
import br.com.guilherme.gastos.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InserirUsuarioService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public InserirUsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra um novo usuário no sistema
     *
     * @param requestDTO Dados do novo usuário
     * @throws EmailJaCadastradoException Exceção lançada quando o e-mail enviado já existir no sistema
     */
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
