package med.voll.VollMedApi.domain.consulta.validacoes.cancelamento;

import med.voll.VollMedApi.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
