package br.com.xtrategia.fiscon.web;

import java.util.List;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;
import br.com.xtrategia.fiscon.web.pojo.ChaveAutoInfracaoPojo;
import br.com.xtrategia.fiscon.web.pojo.FotoPojo;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;

public class AutoInfracaoAction extends CRUDAction<AutoInfracaoPojo> {

	private static final long serialVersionUID = 1L;

	private List<ChaveAutoInfracaoPojo> listaChave;
	private String listaAutoInfracao;
	
	@Override
	protected AutoInfracaoPojo iniciarPojo() {
		return new AutoInfracaoPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}
	
	public String homologar(){
		commandListar="HomologarListarAutoInfracao";
		commandQuantidadeItens="HomologarQuantidadeItensAutoInfracao";
		return super.listar();
	}
	public String homologarExibir(){
		editar();
		return SUCCESS;
	}
	
	@Override
	public String editar() {
		super.editar();

		for(FotoPojo f: getPojo().getFotoPojos()){
			
			String foto = f.getDado();
			
			f.setFoto(foto);
		}
		
		return SUCCESS;
	}
	
	@Override
	public String salvar() {
		try {
			if (getPojo() != null && getPojo().getId() != null && getPojo().getId() > 0) {
				getPojo().setUsuarioHomologacao((UsuarioPojo) getLogado());
				setPojo((AutoInfracaoPojo) getFachada().execute(getPojo(), "HomologarSalvarAutoInfracao"));
			}
		} catch (FISCONException e) {
			setMensagem("Erro no update");
		}
		return homologar();
	}
	
	public String consultar(){
		return SUCCESS;
	}
	
	@Override
	public String listar() {
		commandListar="ListarAutoInfracao";
		commandQuantidadeItens="QuantidadeItensAutoInfracao";
		return super.listar();
	}

	@SuppressWarnings("unchecked")
	public String listarChaves() {
		commandListar="ListarChavesAutoInfracao";
		commandQuantidadeItens="QuantidadeItensChavesAutoInfracao";
		try {
			if (pagina == null || pagina.intValue()<1){
				pagina=new Long(1);
			}
			
			getPojo().setPagina(pagina);
			
			listaChave = (List<ChaveAutoInfracaoPojo>) getFachada().execute(getPojo(), commandListar)
					.getConteudo();
			
			if(listaChave==null || listaChave.size()==0){
				setMensagem("Nenhum Registro encontrado!");
				paginaUltima=0L;
				return SUCCESS;
			}
			
			paginaUltima=(Long) getFachada().execute(getPojo(), commandQuantidadeItens).getConteudo();
			
			paginaUltima= (paginaUltima/20)+1;
			paginaAnterior=pagina-1;
			if(paginaAnterior.intValue()<0){
				paginaAnterior=new Long(0);
			}
			paginaProxima=pagina+1;
			
		} catch (FISCONException e) {
			setMensagem("Erro na Consulta");
			return ERROR;
		}

		return SUCCESS;
	}

	public List<ChaveAutoInfracaoPojo> getListaChave() {
		return listaChave;
	}

	public void setListaChave(List<ChaveAutoInfracaoPojo> listaChave) {
		this.listaChave = listaChave;
	}
	
	public String getListaAutoInfracao() {
		return listaAutoInfracao;
	}

	public void setListaAutoInfracao(String listaAutoInfracao) {
		this.listaAutoInfracao = listaAutoInfracao;
	}

}
