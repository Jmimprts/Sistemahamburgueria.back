package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.dto.response.PedidoResponseDTO;
import deliveryhamburgueriabk.service.interfaces.IEntregaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregas")
@CrossOrigin(origins = "*")
public class EntregaController {

    private final IEntregaService entregaService;

    public EntregaController(IEntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PatchMapping("/{pedidoId}/sair")
    public ResponseEntity<PedidoResponseDTO> sairParaEntrega(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(PedidoResponseDTO.de(entregaService.sairParaEntrega(pedidoId)));
    }

    @PatchMapping("/{pedidoId}/confirmar")
    public ResponseEntity<PedidoResponseDTO> confirmarEntrega(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(PedidoResponseDTO.de(entregaService.confirmarEntrega(pedidoId)));
    }
}