package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.dto.request.LoginRequestDTO;
import deliveryhamburgueriabk.dto.request.UsuarioRequestDTO;
import deliveryhamburgueriabk.dto.response.UsuarioResponseDTO;
import deliveryhamburgueriabk.enums.Perfil;
import deliveryhamburgueriabk.model.Usuario;
import deliveryhamburgueriabk.service.interfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponseDTO> registrar(@Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setEndereco(dto.endereco());
        usuario.setTelefone(dto.telefone());
        usuario.setPerfil(Perfil.CLIENTE);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UsuarioResponseDTO.de(usuarioService.cadastrar(usuario)));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(
                UsuarioResponseDTO.de(usuarioService.login(dto.email(), dto.senha()))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(UsuarioResponseDTO.de(usuarioService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodos()
                .stream()
                .map(UsuarioResponseDTO::de)
                .toList();
        return ResponseEntity.ok(usuarios);
    }
}