package med.voll.VollMedApi.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.VollMedApi.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
