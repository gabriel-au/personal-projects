package br.com.consorciosdf.intranet.modelo;

public class Andamento {
    
    private int id;
    
    private String nomeAndamento;
    private String descricaoAndamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAndamento() {
        return nomeAndamento;
    }

    public void setNomeAndamento(String nomeAndamento) {
        this.nomeAndamento = nomeAndamento;
    }

    public String getDescricaoAndamento() {
        return descricaoAndamento;
    }

    public void setDescricaoAndamento(String descricaoAndamento) {
        this.descricaoAndamento = descricaoAndamento;
    }
}
