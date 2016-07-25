package br.com.xtrategia.fiscon.application.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import sun.misc.BASE64Decoder;
import br.com.xtrategia.fiscon.application.ConexaoBanco;
import br.com.xtrategia.fiscon.application.LogApplicacao;
import br.com.xtrategia.fiscon.application.pojo.AutoInfracaoPojo;

public class AutoInfracaoDao extends ConexaoBanco<AutoInfracaoPojo> {

	@Override
	public void iniciar() throws SQLException {

	}

	@Override
	public void executar(AutoInfracaoPojo pojo) throws SQLException {
	}

	public void executar(String path) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		List<String> lista = new ArrayList<String>();
		List<String> listaArquivos = new ArrayList<String>();
		String arquivoPrincipal = path + File.separator + "exportacao"
				+ sdf.format(new Date());
		listaArquivos.add(arquivoPrincipal + ".txt");

		FileWriter file = new FileWriter(arquivoPrincipal + ".txt");
		LogApplicacao.gravar("Importando dados das Infrações");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(getSqlConsulta());
		while (rs.next()) {
			lista.add(rs.getString("id"));
			String linha = "";
			linha += rs.getString("id") + ";";
			linha += rs.getString("dataInfracao") + ";";
			linha += rs.getString("dataEnvio") + ";";
			linha += rs.getString("orgaoAutuador") + ";";
			linha += rs.getString("codigoOrgaoAutuador") + ";";
			linha += rs.getString("numeroAutoInfracao") + ";";
			linha += rs.getString("enderecoLogradouro") + ";";
			linha += rs.getString("enderecoComplemento") + ";";
			linha += rs.getString("enderecoBairro") + ";";
			linha += rs.getString("enderecoUF") + ";";
			linha += rs.getString("equipInstrumento") + ";";
			linha += rs.getString("certificado") + ";";
			linha += rs.getString("dataVerificacao") + ";";
			linha += rs.getString("medicaoRealizada") + ";";
			linha += rs.getString("limiteRegulamentado") + ";";
			linha += rs.getString("valorConsiderado") + ";";
			linha += rs.getString("codigoRenainf") + ";";
			linha += rs.getString("dataExpedicao") + ";";
			linha += rs.getString("valor") + ";";
			linha += rs.getString("latitude") + ";";
			linha += rs.getString("longitude") + ";";
			linha += rs.getString("veiculoPlaca") + ";";
			linha += rs.getString("usuarioMatricula") + ";";
			linha += rs.getString("usuarioNome") + ";";
			linha += rs.getString("municipioCodigo") + ";";
			linha += rs.getString("municipioNome") + ";";
			linha += rs.getString("infracaoCodigo") + ";";
			linha += rs.getString("infracaoNome") + ";";
			linha += rs.getString("infracaoAmparoLegal") + ";";
			linha += rs.getString("infracaoInfrator") + ";";
			linha += rs.getString("infracaoGravidade") + ";";
			linha += rs.getString("infracaoOrgao") + ";";
			linha += rs.getString("cnh") + ";";
			linha += rs.getString("dataHomologacao") + ";";
			linha += rs.getString("usuarioHomologacaoMatricula") + ";";
			linha += rs.getString("usuarioHomologacaoNome") + ";";
			file.write(linha + "\n");
			file.flush();

		}
		file.close();
		LogApplicacao.gravar("Finalizando dados das Infrações");

		LogApplicacao.gravar("Importando dados das Fotos das Infrações");
		// buscar as imagens
		PreparedStatement pstmt = conn.prepareStatement(getConsultaFoto());
		for (String id : lista) {
			pstmt.setInt(1, new Integer(id));
			ResultSet rsFoto = pstmt.executeQuery();
			int contador = 1;
			while (rsFoto.next()) {
				String arq = arquivoPrincipal + "_id_" + id + "_foto_"
						+ contador++ + ".jpg";
				listaArquivos.add(arq);

				FileOutputStream foto = new FileOutputStream(arq);
				BufferedOutputStream out = new BufferedOutputStream(foto);
				String dado = rsFoto.getString(1);
				BASE64Decoder base64 = new BASE64Decoder();
				byte[] dados = base64.decodeBuffer(dado);
				out.write(dados);
				foto.close();
			}
		}
		LogApplicacao.gravar("Finalizando dados das Fotos das Infrações");

		// Cria um buffer para ler os dados do arquivo
		byte[] buf = new byte[1024];

		try {
			// Cria o arquivo zip
			String compac = arquivoPrincipal + ".zip";
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					compac));
			LogApplicacao.gravar("Criando um arquivo compactado Infrações");

			for (String arquivo : listaArquivos) {
				// Comprime o arquivo
				FileInputStream in = new FileInputStream(arquivo);
				// Adiciona o arquivo ao fluxo de saída
				String nomeArquivo =arquivo.substring(arquivo.lastIndexOf(File.separator)+1);
				out.putNextEntry(new ZipEntry(nomeArquivo));

				// transfere dados do arquivo para o arquivo zip
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// Finaliza a entrada
				out.closeEntry();
				in.close();
				
				//remove o arquivo original
				new File(arquivo).delete();
			}

			// Completa o arquivo zip
			out.close();
			LogApplicacao.gravar("Finalizando o arquivo compactado Infrações");
		} catch (IOException e) {
			// possíveis erros aqui
		}

		LogApplicacao.gravar("Atualizando a Base de Dados");
		pstmt = conn.prepareStatement(getSqlUpdate());
		String chave = "C"+System.currentTimeMillis();
		for (String id : lista) {
			pstmt.setString(1, chave);
			pstmt.setInt(2, new Integer(id));
			pstmt.execute();
		}
		LogApplicacao.gravar("Processo de Exportação finalizado.");
		
	}

	@Override
	public void fechar() throws SQLException {

		super.fechar();
	}

	public String getConsultaFoto() {
		return "select dado from foto where id_auto_infracao = ?";
	}

	@Override
	public String getSqlConsulta() {
		return "select "
				+ "ai.id as id, "
				+ "to_char(ai.data_Infracao, 'dd/mm/YYYY') as dataInfracao, "
				+ "to_char(ai.data_Envio, 'dd/mm/YYYY') as dataEnvio, "
				+ "ai.orgao_Autuador as orgaoAutuador, "
				+ "ai.codigo_Orgao_Autuador as codigoOrgaoAutuador, "
				+ "ai.numero_Auto_Infracao as numeroAutoInfracao, "
				+ "ai.endereco_Logradouro as enderecoLogradouro, "
				+ "ai.endereco_Complemento as enderecoComplemento, "
				+ "ai.endereco_Bairro as enderecoBairro, "
				+ "ai.endereco_UF as enderecoUF, "
				+ "ai.equip_Instrumento as equipInstrumento, "
				+ "ai.certificado as certificado, "
				+ "to_char(ai.data_Verificacao,'dd/mm/YYYY') as dataVerificacao, "
				+ "ai.medicao_Realizada as medicaoRealizada, "
				+ "ai.limite_Regulamentado as limiteRegulamentado, "
				+ "ai.valor_Considerado as valorConsiderado, "
				+ "ai.codigo_Renainf as codigoRenainf, "
				+ "to_char(ai.data_Expedicao,'dd/mm/YYYY') as dataExpedicao, "
				+ "ai.valor as valor, "
				+ "ai.latitude as latitude, "
				+ "ai.longitude as longitude, "
				+ "v.veiculo_Placa as veiculoPlaca, "
				+ "u.matricula as usuarioMatricula, "
				+ "u.nome as usuarioNome, "
				+ "m.codigo as municipioCodigo, "
				+ "m.nome as municipioNome, "
				+ "i.codigo as infracaoCodigo, "
				+ "i.nome as infracaoNome, "
				+ "i.amparolegal as infracaoAmparoLegal, "
				+ "i.infrator as infracaoInfrator, "
				+ "i.Gravidade as infracaoGravidade, "
				+ "i.Orgao as infracaoOrgao, "
				+ "ai.cnh as cnh, "
				+ "to_char(ai.data_Homologacao,'dd/mm/YYYY') as dataHomologacao, "
				+ "h.matricula as usuarioHomologacaoMatricula, "
				+ "h.nome as usuarioHomologacaoNome "
				+ " from auto_Infracao ai, veiculo v, usuario u, municipio m, infracao_tipo i, usuario h "
				+ "where ai.id_veiculo = v.id " + "and ai.id_usuario=u.id "
				+ "and ai.id_municipio=m.id " + "and ai.id_infracao_tipo=i.id "
				+ "and ai.id_usuario_homologacao=h.id "
				+ "and ai.situacao='Homologada' "
				+ "and chave_extracao is null";
	}

	@Override
	public String getSqlInsert() {
		return null;
	}

	@Override
	public String getSqlInsertHistorico() {
		return null;
	}

	@Override
	public String getSqlUpdate() {
		return "update auto_infracao set chave_extracao = ? where id = ?";
	}

}
