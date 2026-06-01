package deliveryhamburgueriabk.controller;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.StatusPedido;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.ItemPedido;
import deliveryhamburgueriabk.model.Pedido;
import deliveryhamburgueriabk.model.Produto;
import deliveryhamburgueriabk.repository.PedidoRepository;
import deliveryhamburgueriabk.repository.ProdutoRepository;
import deliveryhamburgueriabk.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoController(PedidoRepository pedidoRepository,
                            ProdutoRepository produtoRepository,
                            UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Map<String, Object> body) {
        Long usuarioId = Long.valueOf(body.get("usuarioId").toString());

        if (!usuarioRepository.existsById(usuarioId)) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "Usuario nao encontrado."));
        }

        FormaPagamento formaPagamento = FormaPagamento.valueOf(body.get("formaPagamento").toString());
        TipoPedido tipoPedido = TipoPedido.valueOf(body.get("tipoPedido").toString());
        List<?> idsRecebidos = (List<?>) body.get("produtoIds");

        if (idsRecebidos == null || idsRecebidos.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "Informe pelo menos um produto."));
        }

        Pedido pedido = new Pedido((int) (System.currentTimeMillis() % 100000), formaPagamento);
        pedido.setTipoPedido(tipoPedido);
        pedido.setStatusPedido(StatusPedido.PENDENTE);

        for (Object id : idsRecebidos) {
            Long produtoId = Long.valueOf(id.toString());
            Produto produto = produtoRepository.findById(produtoId).orElse(null);

            if (produto == null) {
                return ResponseEntity.badRequest().body(Map.of("mensagem", "Produto nao encontrado: " + produtoId));
            }

            pedido.adicionarItem(new ItemPedido(produto, 1));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedido));
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestParam StatusPedido status) {
        return pedidoRepository.findById(id)
                .<ResponseEntity<?>>map(pedido -> {
                    pedido.setStatusPedido(status);
                    return ResponseEntity.ok(pedidoRepository.save(pedido));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
