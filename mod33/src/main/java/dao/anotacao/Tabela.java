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
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tabela {

	String value();
}
