package br.com.consorciosdf.intranet.modelo;

public class Defeito {
    
    private int id;
    
    private String nomeDefeito;
    private String descricaoDefeito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDefeito() {
        return nomeDefeito;
    }

    public void setNomeDefeito(String nomeDefeito) {
        this.nomeDefeito = nomeDefeito;
    }

    public String getDescricaoDefeito() {
        return descricaoDefeito;
    }

    public void setDescricaoDefeito(String descricaoDefeito) {
        this.descricaoDefeito = descricaoDefeito;
    }
    
}
