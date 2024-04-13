package br.com.rpires.dao;

import br.com.rpires.entity.Contrato;

public interface IContratoDao {
    void salvar();

    Contrato buscarContrato(int id);

    boolean excluirContrato(int id);

    boolean atualizarContrato(Contrato contrato);
}
