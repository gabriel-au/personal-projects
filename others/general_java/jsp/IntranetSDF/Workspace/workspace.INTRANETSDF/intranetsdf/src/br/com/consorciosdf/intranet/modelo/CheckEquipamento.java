package br.com.consorciosdf.intranet.modelo;

import java.util.Date;
import java.util.List;

public class CheckEquipamento {
    
    private CheckEquipamentoPerfil checkEquipamentoPerfil;
    private Equipamento equipamento;
    private Procedimento procedimento;
    private StatusProcedimento statusProcedimentoEnt;
    private StatusProcedimento statusProcedimentoSai;
    private Usuario usuario;
    private Paginacao paginacao;
    
    private List procedimentos;
    
    private int id;
    private int numLacreInmetro;
    private int numSerie;
    private int numGabinete;
    private int numUPR;
    private int numCamera;
    private int temperaturaTrabalho;
    private int induntanciaLaco;
    private int intensidadeCorrente;
    private int tensaoEntradaAntiRaio;
    private int tensaoSaidaAntiRaio;
    private int resistividadeMalhaAterramento;
    private int tensaoSaidaNobreak;
    
    private String numFlash;
    private String observacao;
    private String execColeta;
    
    private Date dataInicio;
    private Date dataFim;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public StatusProcedimento getStatusProcedimentoEnt() {
        return statusProcedimentoEnt;
    }

    public void setStatusProcedimentoEnt(StatusProcedimento statusProcedimentoEnt) {
        this.statusProcedimentoEnt = statusProcedimentoEnt;
    }
    
    public StatusProcedimento getStatusProcedimentoSai() {
        return statusProcedimentoSai;
    }

    public void setStatusProcedimentoSai(StatusProcedimento statusProcedimentoSai) {
        this.statusProcedimentoSai = statusProcedimentoSai;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public List getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List procedimentos) {
        this.procedimentos = procedimentos;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public int getNumUPR() {
        return numUPR;
    }

    public void setNumUPR(int numUPR) {
        this.numUPR = numUPR;
    }

    public int getNumCamera() {
        return numCamera;
    }

    public void setNumCamera(int numCamera) {
        this.numCamera = numCamera;
    }

    public String getNumFlash() {
        return numFlash;
    }

    public void setNumFlash(String numFlash) {
        this.numFlash = numFlash;
    }

    public String getExecColeta() {
        return execColeta;
    }

    public void setExecColeta(String execColeta) {
        this.execColeta = execColeta;
    }

    public Paginacao getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(Paginacao paginacao) {
        this.paginacao = paginacao;
    }

    public CheckEquipamentoPerfil getCheckEquipamentoPerfil() {
        return checkEquipamentoPerfil;
    }

    public void setCheckEquipamentoPerfil(CheckEquipamentoPerfil checkEquipamentoPerfil) {
        this.checkEquipamentoPerfil = checkEquipamentoPerfil;
    }

    public int getNumGabinete() {
        return numGabinete;
    }

    public void setNumGabinete(int numGabinete) {
        this.numGabinete = numGabinete;
    }

    public int getNumLacreInmetro() {
        return numLacreInmetro;
    }

    public void setNumLacreInmetro(int numLacreInmetro) {
        this.numLacreInmetro = numLacreInmetro;
    }

    public int getTemperaturaTrabalho() {
        return temperaturaTrabalho;
    }

    public void setTemperaturaTrabalho(int temperaturaTrabalho) {
        this.temperaturaTrabalho = temperaturaTrabalho;
    }

    public int getInduntanciaLaco() {
        return induntanciaLaco;
    }

    public void setInduntanciaLaco(int induntanciaLaco) {
        this.induntanciaLaco = induntanciaLaco;
    }

    public int getIntensidadeCorrente() {
        return intensidadeCorrente;
    }

    public void setIntensidadeCorrente(int intensidadeCorrente) {
        this.intensidadeCorrente = intensidadeCorrente;
    }

    public int getTensaoEntradaAntiRaio() {
        return tensaoEntradaAntiRaio;
    }

    public void setTensaoEntradaAntiRaio(int tensaoEntradaAntiRaio) {
        this.tensaoEntradaAntiRaio = tensaoEntradaAntiRaio;
    }

    public int getTensaoSaidaAntiRaio() {
        return tensaoSaidaAntiRaio;
    }

    public void setTensaoSaidaAntiRaio(int tensaoSaidaAntiRaio) {
        this.tensaoSaidaAntiRaio = tensaoSaidaAntiRaio;
    }

    public int getResistividadeMalhaAterramento() {
        return resistividadeMalhaAterramento;
    }

    public void setResistividadeMalhaAterramento(int resistividadeMalhaAterramento) {
        this.resistividadeMalhaAterramento = resistividadeMalhaAterramento;
    }

    public int getTensaoSaidaNobreak() {
        return tensaoSaidaNobreak;
    }

    public void setTensaoSaidaNobreak(int tensaoSaidaNobreak) {
        this.tensaoSaidaNobreak = tensaoSaidaNobreak;
    }
    
}
