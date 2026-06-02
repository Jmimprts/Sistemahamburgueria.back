package deliveryhamburgueriabk.dto.response;

import deliveryhamburgueriabk.model.Entregador;

public record EntregadorResponseDTO(
        Long id,
        Long usuarioId,
        String nome,
        String email,
        String telefone,
        String veiculo
) {
    public static EntregadorResponseDTO de(Entregador entregador) {
        return new EntregadorResponseDTO(
                entregador.getId(),
                entregador.getUsuario().getId(),
                entregador.getUsuario().getNome(),
                entregador.getUsuario().getEmail(),
                entregador.getUsuario().getTelefone(),
                entregador.getVeiculo()
        );
    }
}
