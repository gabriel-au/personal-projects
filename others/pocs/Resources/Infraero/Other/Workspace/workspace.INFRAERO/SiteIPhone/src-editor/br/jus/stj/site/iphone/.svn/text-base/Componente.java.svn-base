package br.jus.stj.site.iphone;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.site.iphone.ui.CodigoJava;
import br.jus.stj.site.iphone.ui.Commentary;

/**
 * Classe para montar os elementos HTML
 * 
 * @author gustavomarcelo
 * 
 */
public abstract class Componente {

	private static Log log = LogFactory.getLog(Componente.class);

	private static boolean readFullPath;

	/** Métodos abastratos */
	public abstract String getCode();

	protected abstract void getMontarItensObrigatoriosOpcionais();

	protected List<Componente> itens = new ArrayList<Componente>();
	protected List<Class<? extends Componente>> itensOpcionais = new ArrayList<Class<? extends Componente>>();
	protected List<Class<? extends Componente>> itensObrigatorio = new ArrayList<Class<? extends Componente>>();

	/*
	 * Construtor padrão
	 */
	public Componente() {
		getMontarItensObrigatoriosOpcionais();
		itensOpcionais.addAll(itensObrigatorio);
		itensOpcionais.add(Commentary.class);
		itensOpcionais.add(CodigoJava.class);
	}

	public void clear() {
		itens.clear();
	}

	public void add(Componente item) throws SystemException {
		if(item==null){
			log.warn("Atencao: "+ this.getClass()+" recebeu um item nulo");
			//throw new SystemException("Item inválido no item " + this.getClass());
			return;
		}
		
		if (validar(item)) {
			itens.add(item);
		} else {
			throw new SystemException("Item inválido:" + item.getClass()
					+ " no item" + this.getClass());
		}
	}

	private boolean validar(Componente item) {
		return itensOpcionais.contains(item.getClass());
	}

	private void validarCodigo() {
		List<Class<? extends Componente>> itensTeste = new ArrayList<Class<? extends Componente>>();

		itensTeste.addAll(itensObrigatorio);

		for (Componente item : itens) {
			itensTeste.remove(item.getClass());
		}
		for (Class<? extends Componente> item : itensTeste) {
			log.info("Atenção: o item " + item.getName()
					+ " não está presente no Objeto " + this.getClass());
		}
	}

	@Override
	public String toString() {
		validarCodigo();
		return getCode();
	}

	protected List<Componente> getItens() {
		return itens;
	}

	protected String getCodeChild() {
		String texto = "";
		for (Componente item : itens) {
			texto += "\n" + item;
		}
		return texto;
	}

	public static void setFullPath(boolean isFullPath) {
		Componente.readFullPath = isFullPath;
	}

	protected boolean isFullPath() {
		return Componente.readFullPath;
	}

}
