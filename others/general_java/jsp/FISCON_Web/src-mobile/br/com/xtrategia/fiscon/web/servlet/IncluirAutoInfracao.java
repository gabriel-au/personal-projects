package br.com.xtrategia.fiscon.web.servlet;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.MobileAction;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;
import br.com.xtrategia.fiscon.web.pojo.CnhPojo;
import br.com.xtrategia.fiscon.web.pojo.FotoPojo;
import br.com.xtrategia.fiscon.web.pojo.InfracaoTipoPojo;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;
import br.com.xtrategia.fiscon.web.pojo.VeiculoPojo;

/**
 * Classe para atender as consultas de veiculos do mobile
 * 
 * @author Gustavo Luis
 * 
 */
public class IncluirAutoInfracao extends MobileAction {

	public String incluir(HttpServletRequest request) {

		AutoInfracaoPojo autoInfracaoPojo = new AutoInfracaoPojo();
		Set<FotoPojo> fotos = new HashSet<FotoPojo>();

		// Recuperação das fotos
		if (request.getParameter("foto1") != null
				&& !request.getParameter("foto1").equals("")) {
			FotoPojo foto1 = new FotoPojo();
			System.out.println("dsd: " + request.getParameter("foto1"));
			foto1.setDado(request.getParameter("foto1").replaceAll(" ", "+"));
			// saveImage(foto1.getDado());
			// System.out.println("Tamanho foto enviada"+request.getParameter("tamanhofoto1")+"= Tamanho Foto recebida"+foto1.getDado().length);
			foto1.setAutoInfracaoPojo(autoInfracaoPojo);
			fotos.add(foto1);
		}
		if (request.getParameter("foto2") != null
				&& !request.getParameter("foto2").equals("")) {
			FotoPojo foto2 = new FotoPojo();
			foto2.setDado(request.getParameter("foto2").replaceAll(" ", "+"));
			foto2.setAutoInfracaoPojo(autoInfracaoPojo);
			fotos.add(foto2);
		}
		if ((request.getParameter("foto2") != null && !request.getParameter(
				"foto2").equals(""))
				|| (request.getParameter("foto1") != null && !request
						.getParameter("foto1").equals(""))) {
			// Inclusão das fotos
			autoInfracaoPojo.setFotoPojos(fotos);
		}
		// Inclusão da data de infração
		autoInfracaoPojo.setDataInfracao(new Date(Long.parseLong(request
				.getParameter("datainfracao"))));

		// recuperação do Tipo de Infração
		InfracaoTipoPojo infracaoTipoPojo = new InfracaoTipoPojo();
		infracaoTipoPojo.setCodigo(request.getParameter("codigoInfracao"));
		infracaoTipoPojo.setNome(request.getParameter("nomeinfracao"));

		// Inclusão do tipo de infração
		autoInfracaoPojo.setInfracaoTipoPojo(infracaoTipoPojo);

		// Recupera Veiculo
		VeiculoPojo veiculoPojo = new VeiculoPojo();
		veiculoPojo.setVeiculoPlaca(request.getParameter("placa"));

		// Inclusão do Veiculo
		autoInfracaoPojo.setVeiculoPojo(veiculoPojo);

		request.getParameter("observacao");
		if (request.getParameter("registrocnh") != null) {
			// Recupera CNH
			CnhPojo cnhPojo = new CnhPojo();
			cnhPojo.setCnh(request.getParameter("registrocnh"));
			cnhPojo.setNome(request.getParameter("nomecnh"));
			// Inclusao da CNH
			autoInfracaoPojo.setCnhPojo(cnhPojo);
		} else
			autoInfracaoPojo.setCnhPojo(null);

		autoInfracaoPojo.setEnderecoBairro(request.getParameter("bairro"));
		autoInfracaoPojo.setEnderecoComplemento(request
				.getParameter("complemento"));
		autoInfracaoPojo.setEnderecoLogradouro(request
				.getParameter("logradouro"));
		autoInfracaoPojo.setLatitude(request.getParameter("latitude"));
		autoInfracaoPojo.setLongitude(request.getParameter("longitude"));
		UsuarioPojo usuario = new UsuarioPojo();
		usuario.setMatricula(request.getParameter("matriculausuario"));
		autoInfracaoPojo.setUsuarioPojo(usuario);
		try {
			autoInfracaoPojo = (AutoInfracaoPojo) getFachada().execute(
					autoInfracaoPojo, "InserirInfracao");
		} catch (FISCONException e) {
			e.printStackTrace();
			return "Falha na Inclusao";
		}
		return "numeroautoinfracao=" + autoInfracaoPojo.getNumeroAutoInfracao();

	}

	/*
	 * private byte [] getImgBytes(Image image) { ByteArrayOutputStream baos =
	 * new ByteArrayOutputStream(); try { ImageIO.write(getBufferedImage(image),
	 * "JPEG", baos); } catch (IOException ex) { //handle it here.... not
	 * implemented yet... } return baos.toByteArray(); }
	 * 
	 * private BufferedImage getBufferedImage(Image image) { int width =
	 * image.getWidth(null); int height = image.getHeight(null); BufferedImage
	 * bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	 * //Graphics2D g2d = bi.createGraphics(); //g2d.drawImage(image, 0, 0,
	 * null); return bi; }
	 */

}
