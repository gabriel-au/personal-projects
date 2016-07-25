package br.com.xtrategia.fiscon.command.veiculo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;
import br.com.xtrategia.fiscon.web.pojo.CnhPojo;
import br.com.xtrategia.fiscon.web.pojo.InfracaoTipoPojo;
import br.com.xtrategia.fiscon.web.pojo.MunicipioPojo;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;
import br.com.xtrategia.fiscon.web.pojo.VeiculoPojo;

/**
 * realiza o login do usuário
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class InserirInfracaoCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {

		// fazer a inclusão
		AutoInfracaoPojo pojo = (AutoInfracaoPojo) transferObject;
		// valores calculados
		pojo.setDataEnvio(new Date());
		// Recupera usuario
		Criteria criteria = this.sessao.createCriteria(UsuarioPojo.class);
		criteria.add(Restrictions.eq("matricula", pojo.getUsuarioPojo()
				.getMatricula()));
		pojo.setUsuarioPojo((UsuarioPojo) criteria.uniqueResult());
		// valores fixos
		pojo.setOrgaoAutuador("DETRAN-DF");
		pojo.setCodigoOrgaoAutuador("107100");
		pojo.setNumeroAutoInfracao("P" + pojo.getUsuarioPojo().getMatricula()
				+ ("" + pojo.getDataEnvio().getTime()).substring(2));

		// valores recuperados
		// Recupera InfracaoTipo
		criteria = this.sessao.createCriteria(InfracaoTipoPojo.class);
		criteria.add(Restrictions.eq("codigo", pojo.getInfracaoTipoPojo()
				.getCodigo()));
		pojo.setInfracaoTipoPojo((InfracaoTipoPojo) criteria.uniqueResult());
		// Recupera Municipio
		criteria = this.sessao.createCriteria(MunicipioPojo.class);
		criteria.add(Restrictions.eq("nome", "BRASILIA"));
		criteria.add(Restrictions.eq("uf", "DF"));
		pojo.setMunicipioPojo((MunicipioPojo) criteria.uniqueResult());

		// Recupera Veiculo
		criteria = this.sessao.createCriteria(VeiculoPojo.class);
		criteria.add(Restrictions.eq("veiculoPlaca", pojo.getVeiculoPojo()
				.getVeiculoPlaca()));
		pojo.setVeiculoPojo((VeiculoPojo) criteria.uniqueResult());

		// Recupera CNH
		if (pojo.getCnhPojo() != null) {
			criteria = this.sessao.createCriteria(CnhPojo.class);
			criteria.add(Restrictions.eq("cnh", pojo.getCnhPojo().getCnh()));
			CnhPojo cnhPojo;
			if ((cnhPojo = (CnhPojo) criteria.uniqueResult()) != null) {
				pojo.setCnhPojo(cnhPojo);
			} else {
				/**
				 * Será colocado como nulo para até que seja alterado a
				 * modelagem da tabela de CNH para que permita a inclusão sem a
				 * filiação_mae, habilitação, validade e emissão
				 * 
				 * */
				// pojo.setCnhPojo(null);

				Set<AutoInfracaoPojo> autoInfracaoPojos = new HashSet<AutoInfracaoPojo>();
				autoInfracaoPojos.add(pojo);
				pojo.getCnhPojo().setAutoInfracaoPojos(autoInfracaoPojos);

			}
		}
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

		try {
			this.sessao.save(pojo);
		} catch (HibernateException e) {
			throw new FISCONException("Erro na inclusao:" + e.getMessage());
		}

		return pojo;

	}
}