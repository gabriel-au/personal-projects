package br.com.xtrategia.fiscon.teste;

import java.util.Iterator;

import org.hibernate.Session;

import br.com.xtrategia.fiscon.infra.HibernateUtil;
import br.com.xtrategia.fiscon.web.pojo.RestricaoPojo;
import br.com.xtrategia.fiscon.web.pojo.VeiculoPojo;

public class TesteVeiculos {

	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();

		HibernateUtil.currentTransaction();
		
		VeiculoPojo pojo = (VeiculoPojo) session.load(VeiculoPojo.class, new Integer(29192));
		
		System.out.println(pojo.getVeiculoPlaca());
		
		if(pojo.getRestricaoPojos()!=null){
			Iterator<RestricaoPojo> tipos = pojo.getRestricaoPojos().iterator();
			
			while (tipos.hasNext()) {
				RestricaoPojo restricaoPojo = tipos.next();
				System.out.println(restricaoPojo.getRestricaoTipoPojo().getNome());
				
			}
			
		}
		session.close();
	}

}
