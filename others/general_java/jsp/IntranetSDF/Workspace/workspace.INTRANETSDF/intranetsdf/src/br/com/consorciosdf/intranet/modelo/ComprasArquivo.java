package br.com.consorciosdf.intranet.modelo;

import java.util.Date;

public class ComprasArquivo {
    
    private int id;
    
    private String descricao;
    private String fileName;
    
    private Date dataInclusao;
    private Date dataAlteracao;
    
    private Compras compras;
    private ComprasArquivoTipo comprasArquivoTipo;
    private Usuario usuarioInclusao;
    private Usuario usuarioAlteracao;
    private Paginacao paginacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public ComprasArquivoTipo getComprasArquivoTipo() {
        return comprasArquivoTipo;
    }

    public void setComprasArquivoTipo(ComprasArquivoTipo comprasArquivoTipo) {
        this.comprasArquivoTipo = comprasArquivoTipo;
    }

    public Usuario getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(Usuario usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public Usuario getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public Paginacao getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(Paginacao paginacao) {
        this.paginacao = paginacao;
    }

    public Compras getCompras() {
        return compras;
    }

    public void setCompras(Compras compras) {
        this.compras = compras;
    }
    
}
