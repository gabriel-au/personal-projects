package br.com.xtrategia.fiscon.teste;

import org.hibernate.Session;

import br.com.xtrategia.fiscon.infra.HibernateUtil;
import br.com.xtrategia.fiscon.web.pojo.CorPojo;

public class TesteConexao {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		Session session = HibernateUtil.currentSession();
		
		HibernateUtil.currentTransaction();
		
		CorPojo pojo = new CorPojo(true,"99","teste cor");
		
		session.save(pojo);
		
		HibernateUtil.commitTransaction();
		
		session.close();
		
	}

}
