package deliveryhamburgueriabk.dto.response;

import deliveryhamburgueriabk.model.ItemPedido;

public record ItemPedidoResponseDTO(
        Long id,
        Long produtoId,
        String nomeProduto,
        int quantidade,
        double subtotal
) {
    public static ItemPedidoResponseDTO de(ItemPedido item) {
        return new ItemPedidoResponseDTO(
                item.getId(),
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getSubtotal()
        );
    }
}
