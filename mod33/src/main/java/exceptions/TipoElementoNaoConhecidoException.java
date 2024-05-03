/**
 * 
 */
package main.java.exceptions;

/**
 * @author rodrigo.pires
 *
 */
public class TipoElementoNaoConhecidoException extends RuntimeException {
	

	private static final long serialVersionUID = -2268140970978666251L;

	public TipoElementoNaoConhecidoException(String msg) {
        this(msg, null);
    }

    public TipoElementoNaoConhecidoException(String msg, Throwable e) {
        super(msg, e);
    }

}
