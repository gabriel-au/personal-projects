package br.com.xtrategia.fiscon.application.pojo;

import br.com.xtrategia.fiscon.application.Pojo;

public class CorPojo extends Pojo {

    private String codigo; //3
    private String nome; //15
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof CorPojo) {
    		CorPojo pojo = (CorPojo)obj;
    		return pojo.getCodigo()==codigo && pojo.getNome().equals(nome);
		}else{
			return false;
		}
    }
    
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
    
	
	
}
