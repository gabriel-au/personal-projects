package br.com.xtrategia.fiscon.teste;

import java.util.Date;

import org.hibernate.FlushMode;
import org.hibernate.Session;

import br.com.xtrategia.fiscon.infra.HibernateUtil;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;
import br.com.xtrategia.fiscon.web.pojo.InfracaoTipoPojo;
import br.com.xtrategia.fiscon.web.pojo.MunicipioPojo;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;
import br.com.xtrategia.fiscon.web.pojo.VeiculoPojo;

public class TesteAutoInfracao {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		Session session = HibernateUtil.currentSession();

		HibernateUtil.currentTransaction();
		

		session.setFlushMode(FlushMode.COMMIT);
		/*
		 * VeiculoPojo veiculoPojo = (VeiculoPojo)
		 * session.load(VeiculoPojo.class, new Integer(10)); InfracaoTipoPojo
		 * infracaoTipoPojo = (InfracaoTipoPojo)
		 * session.load(InfracaoTipoPojo.class, new Integer(471)); MunicipioPojo
		 * municipioPojo = (MunicipioPojo) session.load(MunicipioPojo.class, new
		 * Integer(5366)); UsuarioPojo usuarioPojo = (UsuarioPojo)
		 * session.load(UsuarioPojo.class, new Integer(2));
		 */

		for (int i = 100; i < 150; i++) {
			AutoInfracaoPojo pojo = new AutoInfracaoPojo();
			VeiculoPojo veiculoPojo = new VeiculoPojo();
			veiculoPojo.setId(500+i);
			InfracaoTipoPojo infracaoTipoPojo = new InfracaoTipoPojo();
			infracaoTipoPojo.setId(10+i);
			MunicipioPojo municipioPojo = new MunicipioPojo();
			municipioPojo.setId(5366);
			UsuarioPojo usuarioPojo = new UsuarioPojo();
			usuarioPojo.setId((i % 3)+1);

			// CnhPojo cnhPojo = new CnhPojo();

			pojo.setVeiculoPojo(veiculoPojo);
			pojo.setInfracaoTipoPojo(infracaoTipoPojo);
			pojo.setMunicipioPojo(municipioPojo);
			pojo.setUsuarioPojo(usuarioPojo);
			pojo.setCnhPojo(null);

			pojo.setNumeroAutoInfracao("P0000000"+i);

			pojo.setEnderecoLogradouro("QNZ 80");
			pojo.setEnderecoComplemento("Z Norte");
			pojo.setEnderecoBairro("Z");
			pojo.setEnderecoUF("DF");
			pojo.setDataInfracao(new Date());

			// valores calculados
			pojo.setDataEnvio(new Date());

			// valores fixos
			pojo.setOrgaoAutuador("DETRAN-DF");
			pojo.setCodigoOrgaoAutuador("107100");

			// valores vázios
			pojo.setEquipInstrumento("");
			pojo.setCertificado("");
			pojo.setDataVerificacao(null);
			pojo.setMedicaoRealizada("");
			pojo.setLimiteRegulamentado("");
			pojo.setValorConsiderado("");
			pojo.setCodigoRenainf("");
			pojo.setDataExpedicao(null);
			pojo.setValor("");

			session.save(pojo);

		}
		HibernateUtil.commitTransaction();

		session.close();

	}

}
