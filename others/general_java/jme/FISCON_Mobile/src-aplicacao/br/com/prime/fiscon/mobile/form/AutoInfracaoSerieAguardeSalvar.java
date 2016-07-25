package br.com.prime.fiscon.mobile.form;

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.codequest.mobile.util.GravarEstatistica;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.negocio.EnviarAutoInfracaoSerie;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class AutoInfracaoSerieAguardeSalvar extends AguardeForm {

	public AutoInfracaoSerieAguardeSalvar(MobileDialog dialog) {
		super(dialog);
		// TODO Auto-generated constructor stub
	}

	public void processar() {
		EnviarAutoInfracaoSerie enviarAutoInfracao = new EnviarAutoInfracaoSerie();
		// salva a infracao para cada veiculo
		AutoInfracaoPojo p = AutoInfracaoPojo.getInstance();
		p.setDataInfracao(new Date());
		Vector veiculos = AutoInfracaoPojo.getInstance().getVeiculos();
		Vector erros = new Vector();
		for (Enumeration e = veiculos.elements(); e.hasMoreElements();) {
			VeiculoPojo vp = (VeiculoPojo) e.nextElement();
			AutoInfracaoPojo.getInstance().setVeiculoPojo(vp);
			try {
				if (enviarAutoInfracao.enviar(AutoInfracaoPojo.getInstance()
						.serializarHashtable())) {

					AutoInfracaoPojo.getInstance().getVeiculoPojo()
							.setNumeroAutoInfracao(
									enviarAutoInfracao.getMapa().get(
											"numeroautoinfracao")
											+ "");
					AutoInfracaoPojo.getInstance().getVeiculoPojo().setStatus(
							AutoInfracaoPojo.ENVIADO);
					
					String par[] = GravarEstatistica.LerConsula();
					GravarEstatistica.gravarConsulta(1, 0, 0);

				} else {
					showMensagemErro(getTitle(), "Falha no envio "
							+ enviarAutoInfracao.getMapa().toString(),
							new AutoInfracaoImprimir(this));
				}
			} catch (Exception ex) {
				erros.addElement(vp);
			}
		}
		// se não houveram erros
		if (erros.isEmpty()) {
			AutoInfracaoPojo.getInstance().setStatus(AutoInfracaoPojo.ENVIADO);
			AutoInfracaoPojo.getInstance().setIndice(0);
			new AutoInfracaoSerieConfirmaImprimir(dialog).show();
		} else {
			System.out.println("HOUVERAM ERROS");
		}

		try {
			CRUDNegocio crudNegocio = new CRUDNegocio();
			crudNegocio.gravar(AutoInfracaoPojo.getInstance());
			crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(),
					Passo.AUTOINFRACAOIMPRIMIR));
		} catch (BancoDadosException ex) {
			ex.printStackTrace();
		}

		setMenuText("Cancelar", "");
	}

	public void executarLeft() {
		new AutoInfracaoFinalizacaoComFoto(this).show();
	}

}
