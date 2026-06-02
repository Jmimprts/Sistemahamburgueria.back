package deliveryhamburgueriabk.dto.response;

import deliveryhamburgueriabk.enums.Perfil;
import deliveryhamburgueriabk.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String endereco,
        String telefone,
        Perfil perfil
) {
    public static UsuarioResponseDTO de(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getEndereco(),
                usuario.getTelefone(),
                usuario.getPerfil()
        );
    }
}
