package br.com.xtrategia.fiscon.command.mobile;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.BairroPojo;
import br.com.xtrategia.fiscon.web.pojo.EnderecoPojo;
import br.com.xtrategia.fiscon.web.pojo.GlobalPositionPojo;
import br.com.xtrategia.fiscon.web.pojo.MunicipioPojo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Lista Genérica
 * 
 * @author Gustavo Luis Costa
 * 
 */

public class RecuperarEnderecoCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		GlobalPositionPojo globalPositionPojo = (GlobalPositionPojo) transferObject;
		EnderecoPojo enderecoPojo = new EnderecoPojo();
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(getXML(globalPositionPojo.getLatitude(),
					globalPositionPojo.getLongitude()));
			doc.getDocumentElement().normalize();
			System.out.println("Root element "
					+ doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("Placemark");
			System.out.println("Information of all employees");
			Node fstNode = nodeLst.item(0);
			Element fstElmnt = (Element) fstNode;

			// recuperar endereço
			NodeList fstNmElmntLst = fstElmnt
					.getElementsByTagName("ThoroughfareName");
			Element fstNmElmnt;
			NodeList fstNm;
			if (fstNmElmntLst != null) {
				fstNmElmnt = (Element) fstNmElmntLst.item(0);
				fstNm = fstNmElmnt.getChildNodes();
				System.out.println("ThoroughfareName : "
						+ ((Node) fstNm.item(0)).getNodeValue());
				enderecoPojo.setEndereco(converte(((Node) fstNm.item(0)).getNodeValue()));
			}

			// Recupera Bairro
			fstNmElmntLst = fstElmnt
					.getElementsByTagName("DependentLocalityName");
			if (fstNmElmntLst != null) {
				fstNmElmnt = (Element) fstNmElmntLst.item(0);
				fstNm = fstNmElmnt.getChildNodes();
				System.out.println("DependentLocalityName : "
						+ ((Node) fstNm.item(0)).getNodeValue());
				enderecoPojo.setBairroPojo(new BairroPojo(
						converte(((Node) fstNm.item(0)).getNodeValue())));
			}

			// Recuperar Cidade/Municipio

			fstNmElmntLst = fstElmnt.getElementsByTagName("LocalityName");
			if (fstNmElmntLst != null) {
				fstNmElmnt = (Element) fstNmElmntLst.item(0);
				fstNm = fstNmElmnt.getChildNodes();
				System.out.println("LocalityName : "
						+ ((Node) fstNm.item(0)).getNodeValue());
				enderecoPojo.setMunicipioPojo(new MunicipioPojo(converte(((Node) fstNm
						.item(0)).getNodeValue())));
			}
			// Recuperar UF
			fstNmElmntLst = fstElmnt
					.getElementsByTagName("AdministrativeAreaName");
			if (fstNmElmntLst != null) {
				fstNmElmnt = (Element) fstNmElmntLst.item(0);
				fstNm = fstNmElmnt.getChildNodes();
				System.out.println("AdministrativeAreaName : "
						+ ((Node) fstNm.item(0)).getNodeValue());
				enderecoPojo.getMunicipioPojo().setUf(
						converte(((Node) fstNm.item(0)).getNodeValue()));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}

		return enderecoPojo;
	}

	private InputStream getXML(double latitude, double longitude) {
		String site = "http://maps.google.com/maps/geo?ll="
				+ latitude
				+ ","
				+ longitude
				+ "&output=xml&sensor=true_or_false&key=ABQIAAAAFnCArmTyWSW8wqBRWkg7QhTagTsArHU_La9eEjO8OEF9eLywJhS9z-OAcQRa3VUfGhLT8eeR-ifwiQ";
		HttpURLConnection conexao = null;
		try {
			URL url = new URL(site);
			conexao = (HttpURLConnection) url.openConnection();
			conexao.setRequestProperty("Request-Method", "GET");

			conexao.setDoInput(true);
			conexao.setDoOutput(false);

			conexao.connect();

			return conexao.getInputStream();

		} catch (Exception e) {
			return null;
		}
	}

	public String converte(String text) {
		text = text.toUpperCase();
		return text.replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e")
				.replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o")
				.replaceAll("[ûúùü]", "u").replaceAll("[ÃÂÀçÄ]", "A")
				.replaceAll("[ÊêÉË]", "E").replaceAll("[ÎíêÏ]", "I")
				.replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[óÙÚÜ]", "U").replace(
						'ç', 'c').replace('Ç', 'C').replace('ñ', 'n').replace(
						'Ñ', 'N').replaceAll("!", "").replaceAll(
						"\\[\\´\\`\\?!\\@\\#\\$\\%\\¨\\*", " ").replaceAll(
						"\\(\\)\\=\\{\\}\\[\\]\\~\\^\\]", " ").replaceAll(
						"[\\.\\;\\-\\_\\+\\'\\ª\\º\\:\\;\\/]", " ");

	}
}