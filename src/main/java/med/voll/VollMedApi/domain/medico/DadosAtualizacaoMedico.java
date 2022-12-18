package med.voll.VollMedApi.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.VollMedApi.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
