package deliveryhamburgueriabk.dto.request;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.TipoPedido;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRequestDTO(

        @NotNull(message = "ID do usuário é obrigatório.")
        Long usuarioId,

        @NotNull(message = "Tipo do pedido é obrigatório.")
        TipoPedido tipoPedido,

        @NotNull(message = "Forma de pagamento é obrigatória.")
        FormaPagamento formaPagamento,

        String enderecoEntrega,

        String codigoCupom,

        @NotEmpty(message = "O pedido deve ter pelo menos um produto.")
        List<Long> produtoIds
) {}
