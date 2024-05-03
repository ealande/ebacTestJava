/**
 * 
 */
package main.java.dao.anotacao;

import java.lang.annotation.*;

/**
 * @author rodrigo.pires
 *
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColunaTabela {

	String dbName();
	
	String setJavaName();
}
