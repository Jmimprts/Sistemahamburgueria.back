package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.dto.request.ProdutoRequestDTO;
import deliveryhamburgueriabk.dto.response.ProdutoResponseDTO;
import deliveryhamburgueriabk.model.Produto;
import deliveryhamburgueriabk.service.interfaces.IProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final IProdutoService produtoService;

    public ProdutoController(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        List<ProdutoResponseDTO> produtos = produtoService.listarTodos()
                .stream()
                .map(ProdutoResponseDTO::de)
                .toList();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/populares")
    public ResponseEntity<List<ProdutoResponseDTO>> populares() {
        List<ProdutoResponseDTO> produtos = produtoService.listarMaisPopulares()
                .stream()
                .map(ProdutoResponseDTO::de)
                .toList();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ProdutoResponseDTO.de(produtoService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setDescricao(dto.descricao());
        produto.setDisponivel(dto.disponivel());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProdutoResponseDTO.de(produtoService.criar(produto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setDescricao(dto.descricao());
        produto.setDisponivel(dto.disponivel());

        return ResponseEntity.ok(ProdutoResponseDTO.de(produtoService.atualizar(id, produto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        produtoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}