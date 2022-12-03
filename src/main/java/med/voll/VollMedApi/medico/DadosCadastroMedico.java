package med.voll.VollMedApi.medico;

import med.voll.VollMedApi.endereco.DadosEndereco;

public record DadosCadastroMedico(
    String nome,
    String email,
    String crm,
    Especialidade especialidade,
    DadosEndereco endereco
)
{}
