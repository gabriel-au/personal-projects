package br.com.xtrategia.fiscon.command.veiculo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoExtendidoPojo;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;
import br.com.xtrategia.fiscon.web.pojo.FotoPojo;
import br.com.xtrategia.fiscon.web.pojo.VeiculoPojo;

public class GerarMapaInfracaoCommand extends AbstractCommand {

	@SuppressWarnings("unchecked")
	@Override
	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		try {

			AutoInfracaoExtendidoPojo pojo = (AutoInfracaoExtendidoPojo) transferObject;

			List<Object> lista = new ArrayList<Object>();
			String resultado = new String();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"dd/MM/yyyy kk:mm");

			Criteria cri = sessao.createCriteria(AutoInfracaoPojo.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			if ((pojo.getVeiculoPojo() == null || pojo.getVeiculoPojo().getVeiculoPlaca().equals("")) && (pojo.getNumeroAutoInfracao() == null || pojo.getNumeroAutoInfracao().equals(""))
					&& pojo.getDataInicio() == null && pojo.getDataFim() == null){
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, -30);
				cri.add(Restrictions.between("dataInfracao",calendar.getTime() , new Date()));
			}
					
					
			if (pojo.getVeiculoPojo() != null
					&& pojo.getVeiculoPojo().getVeiculoPlaca() != null
					&& !pojo.getVeiculoPojo().getVeiculoPlaca().equals("")) {
				Criteria criVeiculo = sessao.createCriteria(VeiculoPojo.class)
						.add(
								Restrictions.eq("veiculoPlaca", pojo
										.getVeiculoPojo().getVeiculoPlaca()
										.toUpperCase()));

				VeiculoPojo veiculo = (VeiculoPojo) criVeiculo.uniqueResult();

				cri.add(Restrictions.eq("veiculoPojo", veiculo));
			}
			if (pojo.getNumeroAutoInfracao() != null
					&& !pojo.getNumeroAutoInfracao().equals("")) {
				cri.add(Restrictions.eq("numeroAutoInfracao", pojo
						.getNumeroAutoInfracao()));
			}
			if (pojo.getDataInicio() != null && pojo.getDataFim() != null) {
				cri.add(Restrictions.between("dataInfracao", pojo
						.getDataInicio(), pojo.getDataFim()));
			} else {
				if (pojo.getDataInicio() != null) {
					cri.add(Restrictions.gt("dataInfracao", pojo
							.getDataInicio()));

				}

				if (pojo.getDataFim() != null) {
					cri.add(Restrictions.lt("dataInfracao", pojo.getDataFim()));
				}
			}

			lista = cri.list();

			resultado = "var data = {";
			resultado += "\"infracoes\": [";

			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {

				AutoInfracaoPojo autoInfracaoPojo = (AutoInfracaoPojo) iterator
						.next();
				resultado += "{\"numeroinfracao\":\""
						+ autoInfracaoPojo.getNumeroAutoInfracao()
						+ "\",\"datainfracao\":\""
						+ formatter.format(autoInfracaoPojo.getDataInfracao())
						+ "\",\"latitude\":" + autoInfracaoPojo.getLatitude()
						+ ",\"longitude\":" + autoInfracaoPojo.getLongitude()
						+ ",\"codigoinfracao\":\""
						+ autoInfracaoPojo.getInfracaoTipoPojo().getCodigo()
						+ "\",\"nomeinfracao\":\""
						+ autoInfracaoPojo.getInfracaoTipoPojo().getNome()
						+ "\",\"gravidadeinfracao\":\""
						+ autoInfracaoPojo.getInfracaoTipoPojo().getGravidade()
						+ "\",\"veiculoPlaca\":\""
						+ autoInfracaoPojo.getVeiculoPojo().getVeiculoPlaca()
						+ "\",\"marcamodelo\":\""
						+ autoInfracaoPojo.getVeiculoPojo().getMarcaModeloPojo().getNome()+ "\""
						+", fotos:[";

				for (Iterator iterator2 = autoInfracaoPojo.getFotoPojos().iterator(); iterator2.hasNext();) {
					
					FotoPojo fotoPojo = (FotoPojo) iterator2.next();
					resultado += "{\"foto\":\""+fotoPojo.getDado()+"\"},";
				}
				resultado+="]";
						
						resultado+=  "}, \n";
			}
			resultado = resultado.substring(0, resultado.length() - 1);
			resultado += "]}";
			System.out.println(resultado);
			pojo.setConteudo(resultado);
			return pojo;

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new FISCONException("Erro na geração do mapa :"
					+ e.getMessage());
		}
	}

}
