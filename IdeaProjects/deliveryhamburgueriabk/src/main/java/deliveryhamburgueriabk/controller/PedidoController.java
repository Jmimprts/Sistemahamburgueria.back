package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.dto.request.PedidoRequestDTO;
import deliveryhamburgueriabk.dto.response.PedidoResponseDTO;
import deliveryhamburgueriabk.enums.StatusPedido;
import deliveryhamburgueriabk.service.interfaces.IPedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final IPedidoService pedidoService;

    public PedidoController(IPedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@Valid @RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PedidoResponseDTO.de(
                        pedidoService.criar(
                                dto.usuarioId(),
                                dto.tipoPedido(),
                                dto.formaPagamento(),
                                dto.enderecoEntrega(),
                                dto.produtoIds(),
                                dto.codigoCupom()
                        )
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(PedidoResponseDTO.de(pedidoService.buscarPorId(id)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido status) {
        return ResponseEntity.ok(PedidoResponseDTO.de(pedidoService.atualizarStatus(id, status)));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoResponseDTO>> historico(@PathVariable Long usuarioId) {
        List<PedidoResponseDTO> historico = pedidoService.historicoPorUsuario(usuarioId)
                .stream()
                .map(PedidoResponseDTO::de)
                .toList();
        return ResponseEntity.ok(historico);
    }
}