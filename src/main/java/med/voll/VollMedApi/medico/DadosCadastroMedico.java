package med.voll.VollMedApi.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.VollMedApi.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank // notblank valida se não é uma e string vazia e se não é nulo (ou seja, se o campo não foi enviado)
        String nome,
        @Email
        @NotBlank
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull // usado quando a propriedade não é string
        Especialidade especialidade,
        @NotNull
        @Valid //valida os bean validations deste DTO
        DadosEndereco endereco
)
{}
