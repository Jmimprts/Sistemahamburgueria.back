package deliveryhamburgueriabk.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProdutoRequestDTO(

        @NotBlank(message = "Nome do produto é obrigatório.")
        String nome,

        @Positive(message = "O preço deve ser maior que zero.")
        double preco,

        String descricao,

        boolean disponivel
) {}
