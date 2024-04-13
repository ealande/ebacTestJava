package br.com.rpires.dao.mocks;

import br.com.rpires.dao.IContratoDao;
import br.com.rpires.entity.Contrato;

public class ContratoDaoMock implements IContratoDao {

    @Override
    public Contrato buscarContrato(int id) {
        throw new UnsupportedOperationException("Não implementado ainda");
    }

    @Override
    public boolean excluirContrato(int id) {
        throw new UnsupportedOperationException("Não implementado ainda");
    }

    @Override
    public boolean atualizarContrato(Contrato contrato) {
        throw new UnsupportedOperationException("Não implementado ainda");
    }

    @Override
    public void salvar() {
        // Implementação do método salvar para o mock
    }
}
