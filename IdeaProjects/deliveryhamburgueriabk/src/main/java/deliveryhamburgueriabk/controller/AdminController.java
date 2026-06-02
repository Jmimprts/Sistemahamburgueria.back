package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.dto.request.EntregadorRequestDTO;
import deliveryhamburgueriabk.dto.response.EntregadorResponseDTO;
import deliveryhamburgueriabk.dto.response.UsuarioResponseDTO;
import deliveryhamburgueriabk.enums.Perfil;
import deliveryhamburgueriabk.service.interfaces.IEntregadorService;
import deliveryhamburgueriabk.service.interfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final IUsuarioService usuarioService;
    private final IEntregadorService entregadorService;

    public AdminController(IUsuarioService usuarioService, IEntregadorService entregadorService) {
        this.usuarioService = usuarioService;
        this.entregadorService = entregadorService;
    }

    @PostMapping("/entregadores")
    public ResponseEntity<EntregadorResponseDTO> cadastrarEntregador(
            @Valid @RequestBody EntregadorRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EntregadorResponseDTO.de(
                        entregadorService.cadastrar(
                                dto.nome(),
                                dto.email(),
                                dto.senha(),
                                dto.telefone(),
                                dto.endereco(),
                                dto.veiculo()
                        )
                ));
    }

    @GetMapping("/entregadores")
    public ResponseEntity<List<EntregadorResponseDTO>> listarEntregadores() {
        List<EntregadorResponseDTO> entregadores = entregadorService.listarTodos()
                .stream()
                .map(EntregadorResponseDTO::de)
                .toList();
        return ResponseEntity.ok(entregadores);
    }

    @DeleteMapping("/entregadores/{id}")
    public ResponseEntity<Void> removerEntregador(@PathVariable Long id) {
        entregadorService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<UsuarioResponseDTO>> listarClientes() {
        List<UsuarioResponseDTO> clientes = usuarioService.listarPorPerfil(Perfil.CLIENTE)
                .stream()
                .map(UsuarioResponseDTO::de)
                .toList();
        return ResponseEntity.ok(clientes);
    }
}