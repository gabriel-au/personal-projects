package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterCheckEquipamentoControl;
import br.com.consorciosdf.intranet.controle.ManterUserControl;
import br.com.consorciosdf.intranet.modelo.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletCheckListSave extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        ManterCheckEquipamentoControl manterCheckEquipamentoControl = new ManterCheckEquipamentoControl();
        ManterUserControl manterUserControl = new ManterUserControl();
        CheckEquipamento checkEquipamento;
        CheckEquipamentoPerfil checkEquipamentoPerfil;
        Usuario usuario;
        Equipamento equipamento;
        Procedimento procedimento;
        StatusProcedimento statusProcedimentoEnt;
        StatusProcedimento statusProcedimentoSai;
        
        List listProcedimentos = null;
        
        if (request.getParameter("equipamentoSelect") != null ||
                request.getParameter("tecnico") != null ||
                request.getParameter("numInmetro") != null ||
                request.getParameter("numSerie") != null ||
                request.getParameter("numGabinete") != null ||
                request.getParameter("numUPR") != null ||
                request.getParameter("numCamera") != null ||
                request.getParameter("numFlash") != null ||
                request.getParameter("tempTrabalho") != null ||
                request.getParameter("induntanciaLaco") != null ||
                request.getParameter("intensidadeCorrente") != null ||
                request.getParameter("tensaoEntradaAntiRaio") != null ||
                request.getParameter("resistividadeMalhaAterramento") != null ||
                request.getParameter("tensaoSaidaAntiRaio") != null ||
                request.getParameter("tensaoSaidaNobreak") != null ||
                request.getParameter("execColeta") != null ||
                request.getParameter("dt_ini_hr") != null ||
                request.getParameter("dt_ini_min") != null ||
                request.getParameter("dt_ini_dia") != null ||
                request.getParameter("dt_ini_mes") != null ||
                request.getParameter("dt_ini_ano") != null ||
                request.getParameter("dt_fim_hr") != null ||
                request.getParameter("dt_fim_min") != null ||
                request.getParameter("dt_fim_dia") != null ||
                request.getParameter("dt_fim_mes") != null ||
                request.getParameter("dt_fim_ano") != null) {
            if (!request.getParameter("equipamentoSelect").trim().equals("") ||
                    !request.getParameter("tecnico").trim().equals("") ||
                    !request.getParameter("numInmetro").trim().equals("") ||
                    !request.getParameter("numSerie").trim().equals("") ||
                    !request.getParameter("numUPR").trim().equals("") ||
                    !request.getParameter("numCamera").trim().equals("") ||
                    !request.getParameter("numFlash").trim().equals("") ||
                    !request.getParameter("tempTrabalho").trim().equals("") ||
                    !request.getParameter("induntanciaLaco").trim().equals("") ||
                    !request.getParameter("intensidadeCorrente").trim().equals("") ||
                    !request.getParameter("tensaoEntradaAntiRaio").trim().equals("") ||
                    !request.getParameter("resistividadeMalhaAterramento").trim().equals("") ||
                    !request.getParameter("tensaoSaidaAntiRaio").trim().equals("") ||
                    !request.getParameter("tensaoSaidaNobreak").trim().equals("") ||
                    !request.getParameter("execColeta").trim().equals("")) {
                
                int numInmetro = Integer.parseInt(request.getParameter("numInmetro"));
                int numSerie = Integer.parseInt(request.getParameter("numSerie"));
                int numGabinete = 0;
                
                if (request.getParameter("numGabinete").trim().equals("")) {
                    numGabinete = Integer.parseInt(request.getParameter("numGabinete"));
                }
                
                int numUPR = Integer.parseInt(request.getParameter("numUPR"));
                int numCamera = Integer.parseInt(request.getParameter("numCamera"));
                int tempTrabalho = Integer.parseInt(request.getParameter("tempTrabalho"));
                int induntanciaLaco = Integer.parseInt(request.getParameter("induntanciaLaco"));
                int intensidadeCorrente = Integer.parseInt(request.getParameter("intensidadeCorrente"));
                int tensaoEntradaAntiRaio = Integer.parseInt(request.getParameter("tensaoEntradaAntiRaio"));
                int resistividadeMalhaAterramento = Integer.parseInt(request.getParameter("resistividadeMalhaAterramento"));
                int tensaoSaidaAntiRaio = Integer.parseInt(request.getParameter("tensaoSaidaAntiRaio"));
                int tensaoSaidaNobreak = Integer.parseInt(request.getParameter("tensaoSaidaNobreak"));
                
                String equip = request.getParameter("equipamentoSelect");
                equipamento = new Equipamento();
                equipamento.setIdEquipamento(Integer.parseInt(equip));
                
                String tecnicoMatricula = "";
                int perfil = -1;
                
                if (request.getParameter("tecnico") != null) {
                    if (!request.getParameter("tecnico").trim().equals("")) {
                        tecnicoMatricula = request.getParameter("tecnico");
                        perfil = Integer.parseInt(request.getParameter("perfilChecagem"));
                        //Usuario userVerify = new Usuario();
                        //userVerify.setUser(usr);
                        //userVerify.setMatriculaUsuario(tecnicoMatricula);
                        
                        //Usuario usuarioPerfil = manterUserControl.viewUser(userVerify);
                        //perfil = usuarioPerfil.getPerfilUsuario();
                    } else {
                        response.sendRedirect(request.getContextPath() + "/checkListEquipamento.jsp?sucess=1");
                    }
                } else {
                    tecnicoMatricula = (String) session.getAttribute("userMatricula");
                    perfil = Integer.parseInt(request.getParameter("perfilChecagem"));
                }
                
                usuario = new Usuario();
                usuario.setMatriculaUsuario(tecnicoMatricula);
                
                checkEquipamentoPerfil = new CheckEquipamentoPerfil();
                checkEquipamentoPerfil.setId(perfil);
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                String strDataInicio = request.getParameter("dt_ini_hr") + ":" +
                                        request.getParameter("dt_ini_min") + ":00 " + 
                                        request.getParameter("dt_ini_dia") + "/" +
                                        request.getParameter("dt_ini_mes") + "/" +
                                        request.getParameter("dt_ini_ano");
                String strDataFim = request.getParameter("dt_fim_hr") + ":" +
                                    request.getParameter("dt_fim_min") + ":00 " +
                                    request.getParameter("dt_fim_dia") + "/" +
                                    request.getParameter("dt_fim_mes") + "/" +
                                    request.getParameter("dt_fim_ano");
                
                Date dataInicio = (Date) session.getAttribute("dataInicioCheckList");
                Date dataFim = new Date();
                
                try {
                    dataInicio = dateFormat.parse(strDataInicio);
                    dataFim = dateFormat.parse(strDataFim);
                } catch (ParseException e) {
                    System.out.println("Erro(ServletSaveCheckList.parseDate: " + e.getMessage());
                } 
                
                checkEquipamento = new CheckEquipamento();
                checkEquipamento.setEquipamento(equipamento);
                checkEquipamento.setUsuario(usuario);
                checkEquipamento.setNumLacreInmetro(numInmetro);
                checkEquipamento.setNumSerie(numSerie);
                checkEquipamento.setNumGabinete(numGabinete);
                checkEquipamento.setNumUPR(numUPR);
                checkEquipamento.setNumCamera(numCamera);
                checkEquipamento.setNumFlash(request.getParameter("numFlash"));
                checkEquipamento.setTemperaturaTrabalho(tempTrabalho);
                checkEquipamento.setInduntanciaLaco(induntanciaLaco);
                checkEquipamento.setIntensidadeCorrente(intensidadeCorrente);
                checkEquipamento.setTensaoEntradaAntiRaio(tensaoEntradaAntiRaio);
                checkEquipamento.setResistividadeMalhaAterramento(resistividadeMalhaAterramento);
                checkEquipamento.setTensaoSaidaAntiRaio(tensaoSaidaAntiRaio);
                checkEquipamento.setTensaoSaidaNobreak(tensaoSaidaNobreak);
                checkEquipamento.setExecColeta(request.getParameter("execColeta"));
                checkEquipamento.setObservacao(request.getParameter("obs"));
                checkEquipamento.setDataInicio(dataInicio);
                checkEquipamento.setDataFim(dataFim);
                
                int idCheck = manterCheckEquipamentoControl.salvarChecagem(checkEquipamento);
                
                listProcedimentos = manterCheckEquipamentoControl.recuperarProcedimentos(perfil);
                int countSave = 0;
                
                if (idCheck != 0) {
                    for (int i=0; i < listProcedimentos.size(); i++) {
                        procedimento = (Procedimento) listProcedimentos.get(i);
                        String parametroEnt = String.valueOf(procedimento.getIdProcedimento()) + "_ent";
                        String parametroSai = String.valueOf(procedimento.getIdProcedimento()) + "_sai";
                        
                        if (request.getParameter(parametroEnt) != null) {
                            String statusProcEnt = request.getParameter(parametroEnt);
                            String statusProcSai = "";
                            
                            statusProcedimentoEnt = new StatusProcedimento();
                            statusProcedimentoEnt.setIdStatusProcedimento(Integer.parseInt(statusProcEnt));
                            
                            if (request.getParameter(parametroSai) != null) {
                                statusProcSai = request.getParameter(parametroSai);
                                statusProcedimentoSai = new StatusProcedimento();
                                statusProcedimentoSai.setIdStatusProcedimento(Integer.parseInt(statusProcSai));
                            } else {
                                statusProcedimentoSai = null;
                            }

                            checkEquipamento = new CheckEquipamento();
                            checkEquipamento.setId(idCheck);
                            checkEquipamento.setProcedimento(procedimento);
                            checkEquipamento.setStatusProcedimentoEnt(statusProcedimentoEnt);
                            checkEquipamento.setStatusProcedimentoSai(statusProcedimentoSai);
                            checkEquipamento.setDataInicio(dataInicio);
                            checkEquipamento.setDataFim(dataFim);

                            if (manterCheckEquipamentoControl.salvarProcedimentoChecagem(checkEquipamento)) {
                                countSave++;
                            }
                            
                        } else {
                            response.sendRedirect(request.getContextPath() + "/error.jsp");
                        }
                    }
                }
                
                
                
                if (countSave == listProcedimentos.size() && countSave != 0) {
                    response.sendRedirect(request.getContextPath() + "/checkListEquipamento.jsp?sucess=3");
                } else {
                    response.sendRedirect(request.getContextPath() + "/checkListEquipamento.jsp?sucess=2");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/checkListEquipamento.jsp?sucess=1");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/checkListEquipamento.jsp?sucess=1");
        }
    }
}
