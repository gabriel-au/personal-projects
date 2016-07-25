package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.negocio.ConsultarVeiculoNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

public class AutoInfracaoConsultaVeiculoAguarde extends AguardeForm {

	public AutoInfracaoConsultaVeiculoAguarde(MobileDialog dialog) {
		super(dialog);
	}

	public void processar() {
		ConsultarVeiculoNegocio c = new ConsultarVeiculoNegocio();
		if (c.consultar(dialog.getMapeamentoAtributos())) {
			AutoInfracaoPojo.getInstance().setVeiculoPojo(c.getVeiculo());
			
			
			if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
				try {
					CRUDNegocio crudNegocio = new CRUDNegocio();
					crudNegocio.gravar(AutoInfracaoPojo.getInstance().getVeiculoPojo());
					crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(), Passo.AUTOINFRACAOCONSULTACNH));
					/*BancoDados bd = new BancoDados(BancoDados.AUTOINFRACAO);
					bd.salvarOuAtulizar(AutoInfracaoPojo.getInstance().serializarHashtable());
					new RecuperarAutoInfracao().gravar(new Passo(AutoInfracaoPojo.getInstance().getId(), Passo.AUTOINFRACAOCONSULTACNH));*/
				}catch(BancoDadosException be){
					showMensagemErro(getTitle(), "Falha ao salvar dados localmente", this);
				}catch (Exception e) {
					showMensagemErro(getTitle(), "Falha ao salvar dados locamente", this);
				}
			}
			
			
			new AutoInfracaoConsultaVeiculoConfirma(dialog).show();

		} else {
			
			new FalhaConsultaVeiculo(getTitle(),"Veículo não encontrado.", this).show();
			//showMensagemErro("Consulta de Veículos", "Veículo não encontrado.",dialog);
			
		}
	}

}
