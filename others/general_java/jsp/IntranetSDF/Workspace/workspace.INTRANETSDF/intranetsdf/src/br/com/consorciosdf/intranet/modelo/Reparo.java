package br.com.consorciosdf.intranet.modelo;

public class Reparo {
    
    private int id;
    
    private String nomeReparo;
    private String descricaoReparo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeReparo() {
        return nomeReparo;
    }

    public void setNomeReparo(String nomeReparo) {
        this.nomeReparo = nomeReparo;
    }

    public String getDescricaoReparo() {
        return descricaoReparo;
    }

    public void setDescricaoReparo(String descricaoReparo) {
        this.descricaoReparo = descricaoReparo;
    }
    
}
