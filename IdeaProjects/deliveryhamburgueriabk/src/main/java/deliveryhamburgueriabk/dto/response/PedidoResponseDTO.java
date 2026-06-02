package deliveryhamburgueriabk.dto.response;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.StatusPedido;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        Long usuarioId,
        String nomeUsuario,
        double valorTotal,
        FormaPagamento formaPagamento,
        StatusPedido statusPedido,
        TipoPedido tipoPedido,
        double taxaEntrega,
        String enderecoEntrega,
        String codigoCupom,
        List<ItemPedidoResponseDTO> itens,
        LocalDateTime criadoEm
) {
    public static PedidoResponseDTO de(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getUsuario().getId(),
                pedido.getUsuario().getNome(),
                pedido.getValorTotal(),
                pedido.getFormaPagamento(),
                pedido.getStatusPedido(),
                pedido.getTipoPedido(),
                pedido.getTaxaEntrega(),
                pedido.getEnderecoEntrega(),
                pedido.getCupom() != null ? pedido.getCupom().getCodigo() : null,
                pedido.getItens().stream()
                        .map(ItemPedidoResponseDTO::de)
                        .toList(),
                pedido.getCriadoEm()
        );
    }
}
