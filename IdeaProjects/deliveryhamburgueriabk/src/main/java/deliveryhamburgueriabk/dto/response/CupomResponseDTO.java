package deliveryhamburgueriabk.dto.response;

import deliveryhamburgueriabk.model.Cupom;

import java.time.LocalDateTime;

public record CupomResponseDTO(
        Long id,
        String codigo,
        double desconto,
        boolean freteGratis,
        LocalDateTime validade,
        boolean ativo
) {
    public static CupomResponseDTO de(Cupom cupom) {
        return new CupomResponseDTO(
                cupom.getId(),
                cupom.getCodigo(),
                cupom.getDesconto(),
                cupom.isFreteGratis(),
                cupom.getValidade(),
                cupom.isAtivo()
        );
    }
}
