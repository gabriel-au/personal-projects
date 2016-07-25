package br.com.prime.fiscon.mobile.negocio;

import java.util.Hashtable;
import java.util.Vector;

import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.BancoDados;
import br.com.prime.fiscon.mobile.form.AguardeLerGPS;
import br.com.prime.fiscon.mobile.form.AutoInfracaoComplemento;
import br.com.prime.fiscon.mobile.form.AutoInfracaoConsultaCNH;
import br.com.prime.fiscon.mobile.form.AutoInfracaoConsultaCodigoInfracao;
import br.com.prime.fiscon.mobile.form.AutoInfracaoConsultaVeiculo;
import br.com.prime.fiscon.mobile.form.AutoInfracaoFinalizacaoComFoto;
import br.com.prime.fiscon.mobile.form.AutoInfracaoFoto2;
import br.com.prime.fiscon.mobile.form.AutoInfracaoImprimir;
import br.com.prime.fiscon.mobile.form.AutoInfracaoObservacao;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

public class RecuperarAutoInfracao {
	//private static final Logger log = LoggerFactory.getLogger(AutoInfracaoFoto1.class);
	
	public Passo getPasso(Passo passo) throws BancoDadosException{
		BancoDados bd = new BancoDados(passo.getBD());
		Passo tempPasso = new Passo();
		Vector lista;
		lista = bd.listar();
		//Apesar de ser um laço, será recuperado somente um passo devido a utilização do   
		for (int i = 0; i < lista.size(); i++) {
			tempPasso.carregarPasso((Hashtable)lista.elementAt(i));
		}
		return tempPasso;
	}
	
	public void gravar(Passo passo) throws BancoDadosException {
		BancoDados bd = new BancoDados(passo.getBD());
		bd.salvarOuAtulizar(passo);
	}
	
	public void apagar(Passo passo) throws BancoDadosException{
		BancoDados bd = new BancoDados(passo.getBD());
		bd.apagar(passo);
	}
	
	public void carregar() throws Exception{
		AutoInfracaoNegocio autoInfracaoNegocio = new AutoInfracaoNegocio();
		autoInfracaoNegocio.carregar();
	}
	
	public void recuperarAutoInfracao(AutoInfracaoPojo autoInfracaoPojo) throws Exception{
		CRUDNegocio crudNegocio = new CRUDNegocio();
		int passo = 100;
		try{ 
			Passo tmpPasso = (Passo)crudNegocio.getObj(new Passo(AutoInfracaoPojo.getId()));
			passo = tmpPasso.getProximoPasso();
			if(passo==100){
				throw new Exception("Falha ao recuperar auto de infração.");
			}
		}catch(Exception e){
			//log.trace(this.getClass().getName(), e);
			throw new Exception("Falha ao recuperar auto de infração.");
		}
		 switch (passo) {
		case Passo.FOTO2:
			carregar();
			new AutoInfracaoFoto2(null).show();
			
			break;
		case Passo.AGUARDELERGPS:
			carregar();
			new AguardeLerGPS(null).show();
			break;
		case Passo.AUTOINFRACAOCOMPLEMENTO:
			carregar();
			new AutoInfracaoComplemento(null).show();
			break;
		case Passo.AUTOINFRACAOCONSULTACNH:
			carregar();
			new AutoInfracaoConsultaCNH().show();
			break;
		case Passo.AUTOINFRACAOCONSULTAVEICULO:
			carregar();
			new AutoInfracaoConsultaVeiculo().show();
			break;
		case Passo.AUTOINFRACAOFINALIZACAOCOMFOTO:
			carregar();
			new AutoInfracaoFinalizacaoComFoto().show();
			break;
		case Passo.AUTOINFRACAOCODIGOINFRACAO:
			carregar();
			new AutoInfracaoConsultaCodigoInfracao(null).show();
			break;
		case Passo.AUTOINFRACAOIMPRIMIR:
			carregar();
			new AutoInfracaoImprimir(null).show();
			break;
		case Passo.AUTOINFRACAOOBSERVACAO:
			carregar();
			new AutoInfracaoObservacao().show();
			break;
		
		default:
			//log.debug(this.getClass().getName()+"Passo não encontrado "+passo);
			throw new Exception("Passo não encontrado "+passo);
		}
	}
	
}
