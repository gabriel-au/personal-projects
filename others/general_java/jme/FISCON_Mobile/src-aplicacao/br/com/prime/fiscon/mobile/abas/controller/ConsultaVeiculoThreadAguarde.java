package br.com.prime.fiscon.mobile.abas.controller;

import br.com.codequest.mobile.ControllThread;
import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.negocio.ConsultarVeiculoNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

public class ConsultaVeiculoThreadAguarde extends ControllThread {
	public ConsultaVeiculoThreadAguarde(MobileDialog dialog) {
		super(dialog);
	}

	public void executarRegra() {
		ConsultarVeiculoNegocio c = new ConsultarVeiculoNegocio();
		if (c.consultar(getDialog().getMapeamentoAtributos())) {
			AutoInfracaoPojo.getInstance().setVeiculoPojo(c.getVeiculo());
			getDialog().carregarTela(null);
			
			if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
				try {
					CRUDNegocio crudNegocio = new CRUDNegocio();
					crudNegocio.gravar(AutoInfracaoPojo.getInstance().getVeiculoPojo());
					crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(), Passo.AUTOINFRACAOCONSULTACNH));
				}catch(BancoDadosException be){
					getDialog().showMensagemErro(getDialog().getTitle(), "Falha ao salvar dados localmente");
				}catch (Exception e) {
					getDialog().showMensagemErro(getDialog().getTitle(), "Falha ao salvar dados locamente");
				}
			}
			
		} else {
			getDialog().showMensagemErro(getDialog().getTitle(),"Veículo não encontrado.");
		}


	}

}
