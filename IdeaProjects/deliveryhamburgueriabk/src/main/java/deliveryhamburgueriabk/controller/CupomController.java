package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.dto.request.CupomRequestDTO;
import deliveryhamburgueriabk.dto.response.CupomResponseDTO;
import deliveryhamburgueriabk.model.Cupom;
import deliveryhamburgueriabk.service.interfaces.ICupomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cupons")
@CrossOrigin(origins = "*")
public class CupomController {

    private final ICupomService cupomService;

    public CupomController(ICupomService cupomService) {
        this.cupomService = cupomService;
    }

    @PostMapping
    public ResponseEntity<CupomResponseDTO> criar(@Valid @RequestBody CupomRequestDTO dto) {
        Cupom cupom = new Cupom();
        cupom.setCodigo(dto.codigo());
        cupom.setDesconto(dto.desconto());
        cupom.setFreteGratis(dto.freteGratis());
        cupom.setValidade(dto.validade());
        cupom.setAtivo(true);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CupomResponseDTO.de(cupomService.criar(cupom)));
    }

    @GetMapping
    public ResponseEntity<List<CupomResponseDTO>> listarAtivos() {
        List<CupomResponseDTO> cupons = cupomService.listarAtivos()
                .stream()
                .map(CupomResponseDTO::de)
                .toList();
        return ResponseEntity.ok(cupons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CupomResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(CupomResponseDTO.de(cupomService.buscarPorId(id)));
    }

    @GetMapping("/validar/{codigo}")
    public ResponseEntity<CupomResponseDTO> validar(@PathVariable String codigo) {
        return ResponseEntity.ok(CupomResponseDTO.de(cupomService.validar(codigo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        cupomService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
