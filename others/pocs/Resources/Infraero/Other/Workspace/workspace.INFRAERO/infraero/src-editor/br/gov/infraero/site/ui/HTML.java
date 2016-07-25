package br.gov.infraero.site.ui;

import java.util.ArrayList;
import java.util.List;

import br.gov.infraero.site.Componente;

public class HTML extends Componente {

	private List<String> javaImport = new ArrayList<String>();

	public String getCode() {
		//String texto = "<%@ page contentType=\"text/html; charset=UTF-8\" %>";
		
		String texto ="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" ";
		texto += getCodeImport();
		texto += "\n";
		texto += "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
		texto += "\n";
		texto += "\n";
		texto += "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
		texto += getCodeChild();
		texto += "\n";
		texto += "</html>";
		return texto;
	}

	public String getCodeImport() {
		String texto = "";
		for (String clazz : javaImport) {
			texto += "<%@page import=\"";
			texto += clazz;
			texto += "\"%>";
			texto += "\n";
		}
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensOpcionais.add(Head.class);
		itensOpcionais.add(Body.class);
	}

	public void addJavaImport(String clazz) {
		javaImport.add(clazz);
	}

}
