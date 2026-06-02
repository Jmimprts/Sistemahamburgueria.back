package deliveryhamburgueriabk.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record CupomRequestDTO(

        @NotBlank(message = "Código do cupom é obrigatório.")
        String codigo,

        @Min(value = 0, message = "Desconto não pode ser negativo.")
        @Max(value = 100, message = "Desconto não pode ultrapassar 100%.")
        double desconto,

        boolean freteGratis,

        @NotNull(message = "Validade é obrigatória.")
        @Future(message = "A validade deve ser uma data futura.")
        LocalDateTime validade
) {}
