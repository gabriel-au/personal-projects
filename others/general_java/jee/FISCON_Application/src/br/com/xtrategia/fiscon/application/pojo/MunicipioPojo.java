package br.com.xtrategia.fiscon.application.pojo;

import br.com.xtrategia.fiscon.application.Pojo;

public class MunicipioPojo extends Pojo {

    private String codigo; //4
    private String nome; //40
    private String uf; //2
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof MunicipioPojo) {
    		MunicipioPojo pojo = (MunicipioPojo)obj;
    		return pojo.getCodigo()==codigo && pojo.getNome().equals(nome)&& pojo.getUf().equals(uf);
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
    
	
	
}
