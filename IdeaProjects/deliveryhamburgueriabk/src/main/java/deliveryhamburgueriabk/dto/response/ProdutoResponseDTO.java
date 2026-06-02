package deliveryhamburgueriabk.dto.response;

import deliveryhamburgueriabk.model.Produto;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        double preco,
        String descricao,
        boolean disponivel,
        int totalVendas
) {
    public static ProdutoResponseDTO de(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getDescricao(),
                produto.isDisponivel(),
                produto.getTotalVendas()
        );
    }
}
