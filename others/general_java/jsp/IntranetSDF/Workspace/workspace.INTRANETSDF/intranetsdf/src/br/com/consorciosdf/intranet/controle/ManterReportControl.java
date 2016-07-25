package br.com.consorciosdf.intranet.controle;

import br.com.consorciosdf.intranet.modelo.*;
import br.com.consorciosdf.intranet.persistencia.ManterReportDAO;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ManterReportControl {
    
    private ManterReportDAO dao;
    
    public boolean uploadReport(FileItem item, String path) throws IOException {
        boolean retorno = false;

        //Pega o diretorio /tmp dentro do diretorio atual de onde a aplicacao esta rodando
        String caminho = path + "/"; //getServletContext().getRealPath("/tmp") + "/";
        
        // Cria o diretorio caso ele nao exista
        File diretorio = new File(caminho);
        if (!diretorio.exists()){
            diretorio.mkdir();
        }
        
        // Mandar o arquivo para o diret�rio informado
        String nome = item.getName();
        String arq[] = nome.split("\\\\");

        for (int i = 0; i < arq.length; i++) {
            nome = arq[i];
        }
        
        //this.fileName = nome;

        File file = new File(diretorio, nome);
        FileOutputStream output = new FileOutputStream(file);
        InputStream is = item.getInputStream();
        byte[] buffer = new byte[5120]; //5MB
        int nLidos;
        while ((nLidos = is.read(buffer)) >= 0) {
            output.write(buffer, 0, nLidos);
        }
        
        retorno = true;

        output.flush();
        output.close();
        
        return retorno;
    }
    
    public boolean importReport(String path, String fileName, RelatorioTipo relatorioTipo, Usuario usuarioInclusao) throws IOException {
        boolean retorno = false;
        
        SimpleDateFormat dateFormat = null; //new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        //SimpleDateFormat dateFormatData = new SimpleDateFormat("dd/MM/yyyy");
        //SimpleDateFormat dateFormatHora = new SimpleDateFormat("HH:mm:ss");
        
        List listImport = null;
        Relatorio relatorio = null;
        
        //String path = getServletContext().getRealPath("/tmp") + "/";
        String fileImport = path + "/" + fileName;
        
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileImport));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        
        int numRows = sheet.getLastRowNum();
        
        if (numRows > 0) {
            dao = new ManterReportDAO();
            listImport = new ArrayList();
            
            for (int i=0; i <= numRows; i++) {
                HSSFRow row = sheet.getRow(i);
                int numCells = row.getLastCellNum();
                relatorio = new Relatorio();
                relatorio.setRelatorioTipo(relatorioTipo);
                relatorio.setUsuarioInclusao(usuarioInclusao);
                
                String dtInicialData = "";
                String dtInicialHora = "";

                for (int x=0; x < numCells; x++) {
                    HSSFCell cell = row.getCell((short) x);
                    HSSFRichTextString richTextString = null;
                    
                    if (cell != null) {
                        switch(x) {
                            //data inicial
                            case 0:
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                    dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    dtInicialData = dateFormat.format(cell.getDateCellValue());
                                    
                                    //System.out.println("Row " + i + " Cell " + x + " - Data Inicial: " + dtInicialData);
                                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                    richTextString = cell.getRichStringCellValue();
                                    
                                    if (!richTextString.getString().trim().equals("")) {
                                        dtInicialData = richTextString.getString().trim();
                                        
                                        //System.out.println("Row " + i + " Cell " + x + " - Data Inicial: " + dtInicialData);
                                    }
                                }
                                break;
                            //hora inicial
                            case 1:
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                    dateFormat = new SimpleDateFormat("HH:mm:ss");
                                    dtInicialHora = dateFormat.format(cell.getDateCellValue());
                                    String data = dtInicialHora + " " + dtInicialData;
                                    
                                    //System.out.println("Row " + i + " Cell " + x + " - Hora Inicial: " + dtInicialHora);

                                    if (!dtInicialHora.trim().equals("") && !dtInicialData.trim().equals("")) {
                                        try {
                                            dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                                            relatorio.setDataInicial(dateFormat.parse(data));
                                        } catch (ParseException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                    richTextString = cell.getRichStringCellValue();
                                    
                                    if (!richTextString.getString().trim().equals("")) {
                                        dtInicialHora = richTextString.getString().trim();
                                        String data = dtInicialHora + " " + dtInicialData;
                                        
                                        //System.out.println("Row " + i + " Cell " + x + " - Hora Inicial: " + dtInicialHora);

                                        if (!dtInicialHora.trim().equals("") && !dtInicialData.trim().equals("")) {
                                            try {
                                                dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                                                relatorio.setDataInicial(dateFormat.parse(data));
                                            } catch (ParseException ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    }
                                }
                                break;
                            //equipamento
                            case 2:
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                    richTextString = cell.getRichStringCellValue();
                                    
                                    //System.out.println("Row " + i + " Cell " + x + " - Equipamento: " + richTextString.getString().trim());

                                    if (!richTextString.getString().trim().equals("")) {
                                        Equipamento equipamento = new Equipamento();
                                        equipamento.setNomeEquipamento(richTextString.getString().trim());

                                        relatorio.setEquipamento(equipamento);
                                    }
                                }
                                break;
                            //tecnico
                            case 3:
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                    richTextString = cell.getRichStringCellValue();
                                    
                                    //System.out.println("Row " + i + " Cell " + x + " - Tecnico: " + richTextString.getString().trim());

                                    if (!richTextString.getString().trim().equals("")) {
                                        Usuario tecnico = new Usuario();
                                        tecnico.setMatriculaUsuario(richTextString.getString().trim());

                                        relatorio.setTecnico(tecnico);
                                    }
                                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                    Double matriculaDouble = Double.valueOf(cell.getNumericCellValue());
                                    String matriculaUsuario = String.valueOf(matriculaDouble.intValue());

                                    if (matriculaUsuario.length() == 3) {
                                        matriculaUsuario = "0" + matriculaUsuario;
                                    } else if (matriculaUsuario.length() == 2) {
                                        matriculaUsuario = "00" + matriculaUsuario;
                                    } else if (matriculaUsuario.length() == 1) {
                                        matriculaUsuario = "000" + matriculaUsuario;
                                    }

                                    //System.out.println("Row " + i + " Cell " + x + " - Tecnico: " + matriculaUsuario);

                                    Usuario tecnico = new Usuario();
                                    tecnico.setMatriculaUsuario(matriculaUsuario);

                                    relatorio.setTecnico(tecnico);
                                }
                                break;
                            //observacao
                            case 4:
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                    richTextString = cell.getRichStringCellValue();
                                    
                                    //System.out.println("Row " + i + " Cell " + x + " - Observação: " + richTextString.getString().trim());

                                    if (!richTextString.getString().trim().equals("")) {
                                        relatorio.setObservacao(richTextString.getString().trim());
                                    }
                                }
                                break;
                            //data e hora final
                            case 5:
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                    dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                                    Date dataFinal = cell.getDateCellValue();
                                    
                                    //System.out.println("Row " + i + " Cell " + x + " - Data Final: " + dateFormat.format(dataFinal));

                                    if (dataFinal != null) {
                                        relatorio.setDataFinal(dataFinal);
                                    }
                                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                    richTextString = cell.getRichStringCellValue();
                                    
                                    if (!richTextString.getString().trim().equals("")) {
                                        try {
                                            dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                                            Date dataFinal = dateFormat.parse(richTextString.getString().trim());
                                            
                                            //System.out.println("Row " + i + " Cell " + x + " - Data Final: " + richTextString.getString().trim());
                                            
                                            if (dataFinal != null) {
                                                relatorio.setDataFinal(dataFinal);
                                            }
                                        } catch (ParseException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
                
                listImport.add(relatorio);
            }
            
            try {
                return dao.importReport(listImport);
            } catch (SQLException e) {
                System.out.println("*Erro(ManterReportControl.importReport): " + e.getMessage());
            }
        }
        
        return retorno;
    }
    
    public Relatorio recuperarReport(int id) {
        Relatorio relatorio = null;
        
        if (dao == null) {
            dao = new ManterReportDAO();
        }
        
        try {
            return dao.recuperarReport(id);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterReportControl.recuperarReport): " + e.getMessage());
        }
        
        return relatorio;
    }
    
    public List recuperarEquipamentos() {
        List list = null;
        
        if (dao == null) {
            dao = new ManterReportDAO();
        }
        
        try {
            list = dao.recuperarEquipamentos();
        } catch (SQLException e) {
            System.out.println("*Erro(ManterReportControl.recuperarEquipamentos): " + e.getMessage());
        }
        
        return list;
    }
    
    public List recuperarReportsPag(int idReport, int pageNumber, int pageLimit, String orderBy, String order) {
        List list = null;
        
        if (dao == null) {
            dao = new ManterReportDAO();
        }
        
        try {
            list = dao.recuperarReportsPag(idReport, pageNumber, pageLimit, orderBy, order);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterReportControl.recuperarReportsPag): " + e.getMessage());
        }
        
        return list;
    }
    
    public List recuperarQuantidadeReport(int tipo, int aberto, int mes, int ano) {
        List list = new ArrayList();
        ContadorMain contadorMain;
        
        String[] mesesAno = {"Jan",
                            "Fev",
                            "Mar",
                            "Abr",
                            "Mai",
                            "Jun",
                            "Jul",
                            "Ago",
                            "Set",
                            "Out",
                            "Nov",
                            "Dez"};
        
        if (dao == null) {
            dao = new ManterReportDAO();
        }
        
        try {
            if (mes == 1) {
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                mes += 11;
                --ano;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
            } else if (mes == 2) {
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                mes += 10;
                --ano;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
            } else {
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
            }
            //listdao.recuperarQuantidadeOS();
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarQuantidadeReport): " + e.getMessage());
        }
        
        return list;
    }
    
    /*public List recuperarQuantidadeReportDet(int tipo, int aberto, int mes, int ano) {
        List list = new ArrayList();
        ContadorMain contadorMain;
        
        String[] mesesAno = {"Jan",
                            "Fev",
                            "Mar",
                            "Abr",
                            "Mai",
                            "Jun",
                            "Jul",
                            "Ago",
                            "Set",
                            "Out",
                            "Nov",
                            "Dez"};
        
        if (dao == null) {
            dao = new ManterReportDAO();
        }
        
        try {
            if (mes == 1) {
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                mes += 11;
                --ano;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
            } else if (mes == 2) {
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                mes += 10;
                --ano;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
            } else {
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
                
                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes-1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeReport(tipo, aberto, mes, ano));

                list.add(contadorMain);
            }
            //listdao.recuperarQuantidadeOS();
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarQuantidadeReport): " + e.getMessage());
        }
        
        return list;
    }*/
    
}
