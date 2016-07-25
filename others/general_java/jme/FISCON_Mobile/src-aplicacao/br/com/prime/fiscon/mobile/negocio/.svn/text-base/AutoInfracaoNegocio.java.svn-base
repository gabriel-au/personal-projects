package br.com.prime.fiscon.mobile.negocio;

import java.util.Hashtable;
import java.util.Vector;

import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.BancoDados;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.CNHPojo;
import br.com.prime.fiscon.mobile.pojo.EnderecoPojo;
import br.com.prime.fiscon.mobile.pojo.FotoPojo;
import br.com.prime.fiscon.mobile.pojo.GlobalPosition;
import br.com.prime.fiscon.mobile.pojo.InfracaoTipoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class AutoInfracaoNegocio {

	public void getPasso() throws BancoDadosException {
		BancoDados bd = new BancoDados(BancoDados.RECUPERACAO);
		Vector lista;
		lista = bd.listar();
		for (int i = 0; i < lista.size(); i++) {
			AutoInfracaoPojo.getInstance().carregar(
					(Hashtable) lista.elementAt(i));
		}

	}

	public void gravar() throws Exception {

		CRUDNegocio crudNegocio = new CRUDNegocio();

		crudNegocio.gravar(AutoInfracaoPojo.getInstance().getVeiculoPojo());
		crudNegocio
				.gravar(AutoInfracaoPojo.getInstance().getInfracaoTipoPojo());
		crudNegocio.gravar(AutoInfracaoPojo.getInstance().getEndereco());
		crudNegocio.gravar(AutoInfracaoPojo.getInstance().getCnh());
		crudNegocio.gravar(AutoInfracaoPojo.getInstance().getGlobalPosition());
		crudNegocio.gravar(AutoInfracaoPojo.getInstance().getUsuarioPojo());
		crudNegocio.gravar(AutoInfracaoPojo.getInstance());

	}
	
	public AutoInfracaoPojo carregar() throws Exception{
		CRUDNegocio crudNegocio = new CRUDNegocio();
		String mensagemErro="Falha ao recuperar o auto de infração";
		try{
			AutoInfracaoPojo.setPojo((AutoInfracaoPojo)crudNegocio.getObj(AutoInfracaoPojo.getInstance()));
		
			FotoPojo fotoPlaca = (FotoPojo)crudNegocio.getObj(new FotoPojo(AutoInfracaoPojo.getId(),FotoPojo.PLACA));
			if(fotoPlaca!=null){
				AutoInfracaoPojo.getInstance().setPrimeiraFotoPojo(fotoPlaca);
			}else{
				throw new Exception(mensagemErro);
			}
			FotoPojo fotoVeiculo = (FotoPojo)crudNegocio.getObj(new FotoPojo(AutoInfracaoPojo.getId(), FotoPojo.VEICULO));
			if(fotoVeiculo!=null){
				AutoInfracaoPojo.getInstance().setSegundaFotoPojo(fotoVeiculo);
			}else{
				throw new Exception(mensagemErro);
			}
			AutoInfracaoPojo.getInstance().setCnh((CNHPojo)crudNegocio.getObj(new CNHPojo(AutoInfracaoPojo.getId())));
			AutoInfracaoPojo.getInstance().setVeiculoPojo((VeiculoPojo)crudNegocio.getObj(new VeiculoPojo(AutoInfracaoPojo.getId())));
			//AutoInfracaoPojo.getInstance().setUsuarioPojo((UsuarioPojo)crudNegocio.getObj(new UsuarioPojo(AutoInfracaoPojo.getId())));
			AutoInfracaoPojo.getInstance().setEndereco((EnderecoPojo)crudNegocio.getObj(new EnderecoPojo(AutoInfracaoPojo.getId())));
			AutoInfracaoPojo.getInstance().setInfracaoTipoPojo((InfracaoTipoPojo)crudNegocio.getObj(new InfracaoTipoPojo(AutoInfracaoPojo.getId())));
			AutoInfracaoPojo.getInstance().setGlobalPosition((GlobalPosition)crudNegocio.getObj(new GlobalPosition(AutoInfracaoPojo.getId())));
			return AutoInfracaoPojo.getInstance();
		}catch(Exception e){
			//log.trace(this.getClass().getName(), e);
			
			throw new Exception("Falha ao carregar objetos "+AutoInfracaoPojo.getInstance().getHashtable().toString());
			
		}
		
	}

	public void apagar() throws Exception {
		CRUDNegocio crudNegocio = new CRUDNegocio();
		
		crudNegocio.apagar(AutoInfracaoPojo.getInstance());
		crudNegocio.apagar(AutoInfracaoPojo.getInstance().getVeiculoPojo());
		crudNegocio.apagar(AutoInfracaoPojo.getInstance().getInfracaoTipoPojo());
		crudNegocio.apagar(AutoInfracaoPojo.getInstance().getEndereco());
		crudNegocio.apagar(AutoInfracaoPojo.getInstance().getCnh());
		crudNegocio.apagar(AutoInfracaoPojo.getInstance().getGlobalPosition());
		//crudNegocio.apagar(AutoInfracaoPojo.getInstance().getUsuarioPojo());
		crudNegocio.apagar(AutoInfracaoPojo.getInstance().getPrimeiraFotoPojo());
		crudNegocio.apagar(AutoInfracaoPojo.getInstance().getSegundaFotoPojo());
		crudNegocio.apagar(crudNegocio.getObj(new Passo(AutoInfracaoPojo.getId())));
	}

}
