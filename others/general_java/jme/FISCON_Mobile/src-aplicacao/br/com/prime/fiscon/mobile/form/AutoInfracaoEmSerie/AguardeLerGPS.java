package br.com.prime.fiscon.mobile.form.AutoInfracaoEmSerie;

import java.util.Hashtable;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.codequest.mobile.util.GPS;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.negocio.ConsultaEndereco;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

public class AguardeLerGPS extends AguardeForm {
	private GPS gps;

	public AguardeLerGPS(MobileDialog dialog) {
		super(dialog);

	}

	public void processar() {
		try {
			gps = new GPS();
			if (gps.localizar()) {
				ConsultaEndereco consultaEndereco = new ConsultaEndereco();
				Hashtable parameter = new Hashtable();
				//Fake
				/*parameter.put("latitude", Double.toString(-15.823932));
				parameter.put("longitude", Double.toString(-47.901980));*/
				
				parameter.put("latitude", Double.toString((gps.getGlobalPosition()
						.getLatitude())));
				parameter.put("longitude", Double.toString((gps.getGlobalPosition()
						.getLongitude())));

				if (consultaEndereco.consultar(parameter)) {
					AutoInfracaoPojo.getInstance().setEndereco(consultaEndereco.getEnderecoPojo());
					AutoInfracaoPojo.getInstance().setGlobalPosition(
							gps.getGlobalPosition());
					if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
						try {
							CRUDNegocio crudNegocio = new CRUDNegocio();
							crudNegocio.gravar(AutoInfracaoPojo.getInstance().getGlobalPosition());
							crudNegocio.gravar(AutoInfracaoPojo.getInstance().getEndereco());
							crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(), Passo.AUTOINFRACAOCOMPLEMENTO));
							/*BancoDados bd = new BancoDados(BancoDados.AUTOINFRACAO);
							bd.salvarOuAtulizar(AutoInfracaoPojo.getInstance().serializarHashtable());
							new RecuperarAutoInfracao().gravar(new Passo(AutoInfracaoPojo.getInstance().getId(), Passo.AUTOINFRACAOCOMPLEMENTO));*/
						}catch(BancoDadosException be){
							showMensagemErro(getTitle(), "Falha ao salvar dados localmente", this);
						}catch (Exception e) {
							showMensagemErro(getTitle(), "Falha ao salvar dados locamente", this);
						}
					}
					AutoInfracaoConfirmaEndereco autoInfracaoConfirmaEndereco = new AutoInfracaoConfirmaEndereco();
					autoInfracaoConfirmaEndereco.setPrevScreen(this);
					autoInfracaoConfirmaEndereco.show();
					//(new AutoInfracaoComplemento(this)).show();
				}

			} else {
				(new FalhaGPS("Falha na Conexão",
						"Não foi possível conectar ao satélite.", this)).show();

			}
		} catch (Exception e) {
			showMensagemErro(getTitle(), "Falha desconhecida "+e.getMessage(), this);
			e.printStackTrace();
		}
	}

}
