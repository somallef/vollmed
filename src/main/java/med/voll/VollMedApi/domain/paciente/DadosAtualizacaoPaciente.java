package med.voll.VollMedApi.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.VollMedApi.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
