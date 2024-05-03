/**
 * 
 */
package main.java.br.com.rpires.services.generic;

import main.java.br.com.rpires.dao.Persistente;
import main.java.br.com.rpires.exceptions.DAOException;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author rodrigo.pires
 *
 */
public interface IGenericService <T extends Persistente, E extends Serializable> {
	
	/**
     * Método para cadastrar novos registro no banco de dados
     *
     * @param entity a ser cadastrado
     * @return retorna verdadeiro para cadastrado e falso para não cadastrado
	 * @throws DAOException 
     */
    public Boolean cadastrar(T entity);

    /**
     * Método para excluir um registro do banco de dados
     *
     * @param valor chave única do dado a ser excluído
     * @throws DAOException 
     */
    public void excluir(E valor);

    /**
     *Método para alterar um registro no bando de dados.
     *
     * @param entity a ser atualizado
     * @throws DAOException 
     */
    public void alterar(T entity);

    /**
     * Método para consultar um registro no banco de dados
     *
     * @param valor chave única do dado a ser consultado
     * @return
     * @throws DAOException 
     */
    public T consultar(E valor);

    /**
     * Método que irá retornar todos os registros do banco de dados de uma determinado dado ou tabela
     *
     * @return Registros encontrados
     * @throws DAOException 
     */
    public Collection<T> buscarTodos();

}
