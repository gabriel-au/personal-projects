package com.brq.mobile.framework.core;

import java.io.Serializable;
import java.util.Map;

/**
 * Classe base para a gera��o de outras classes que utilizem o Pattern Value
 * Object.
 * 
 * @author BRQ Mobile Team
 * 
 */
public abstract class BaseVo implements Serializable {

	private static final long serialVersionUID = 4789582494240585792L;

	/**
	 * Método abstrato responsável por transformar os dados desta classe, em um
	 * objeto do tipo <code>java.util.Map<java.lang.String, java.lang.String></code>.
	 * 
	 * @return objeto do tipo <code>java.util.Map<String, String></code>.
	 */
	public abstract Map<String, Object> toMap();

}
