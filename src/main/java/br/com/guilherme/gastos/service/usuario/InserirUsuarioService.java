package br.com.guilherme.gastos.service.usuario;

import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.dto.sessao.request.RequestRegistrarDTO;
import br.com.guilherme.gastos.exception.EmailJaCadastradoException;
import br.com.guilherme.gastos.repository.UsuarioRepository;
import br.com.guilherme.gastos.utils.ModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@Slf4j
public class InserirUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public InserirUsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra um novo usuário no sistema
     *
     * @param request Dados do novo usuário
     * @throws EmailJaCadastradoException Exceção lançada quando o e-mail enviado já existir no sistema
     */
    public void registrar(RequestRegistrarDTO request) throws EmailJaCadastradoException {

        var optionalUsuario = this.usuarioRepository.findByEmail(request.getEmail());

        if(optionalUsuario.isPresent()){
            throw new EmailJaCadastradoException();
        }

        Usuario usuario = ModelMapper.getMapper().map(request, Usuario.class);
        usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));

        this.usuarioRepository.save(usuario);

    }
}
