package br.com.xtrategia.fiscon.application;

import javax.swing.JOptionPane;

import br.com.xtrategia.fiscon.application.file.AutoInfracaoFile;
import br.com.xtrategia.fiscon.application.file.AutoInfracaoRefazerFile;
import br.com.xtrategia.fiscon.application.file.CategoriaVeiculoFile;
import br.com.xtrategia.fiscon.application.file.CorFile;
import br.com.xtrategia.fiscon.application.file.EspecieFile;
import br.com.xtrategia.fiscon.application.file.MarcaModeloFile;
import br.com.xtrategia.fiscon.application.file.MunicipioFile;
import br.com.xtrategia.fiscon.application.file.RestricaoFile;
import br.com.xtrategia.fiscon.application.file.TipoVeiculoFile;
import br.com.xtrategia.fiscon.application.file.VeiculoFile;

public class NegocioCarga extends Thread {

	private static boolean emProcessamento = false;

	public static final int tipoCategoria = 1;
	public static final int tipoCores = 2;
	public static final int tipoEspecies = 3;
	public static final int tipoMarcaModelo = 4;
	public static final int tipoMunicipio = 5;
	public static final int tipoRestricoes = 8;
	public static final int tipoTipoVeiculo = 7;
	public static final int tipoVeiculo = 6;
	
	public static final int exportacao = 10;
	public static final int exportacaoRefazer = 11;

	private int tipoNegocio;
	private String arquivo;
	private String chave;
	private JButtonAbrir btn;

	public NegocioCarga(int tipoNegocio, String arquivo, JButtonAbrir btn) {
		this.tipoNegocio = tipoNegocio;
		this.arquivo = arquivo;
		this.btn = btn;
	}
	
	public NegocioCarga(int tipoNegocio, String arquivo, JButtonAbrir btn, String chave) {
		this.tipoNegocio = tipoNegocio;
		this.arquivo = arquivo;
		this.btn = btn;
		this.chave = chave;
	}

	@Override
	public void run() {
		if (emProcessamento) {
			JOptionPane.showMessageDialog(null, "Aguarde o processo anterior finalizar.");
			btn.habilitar();
			return;
		} else {
			emProcessamento=true;
			try {
				switch (tipoNegocio) {
				case NegocioCarga.tipoCategoria: {
					CategoriaVeiculoFile proc = new CategoriaVeiculoFile(
							arquivo);
					proc.realizarCarga();
					break;
				}
				case NegocioCarga.tipoCores: {
					CorFile proc = new CorFile(arquivo);
					proc.realizarCarga();
					break;
				}
				case NegocioCarga.tipoEspecies: {
					EspecieFile proc = new EspecieFile(arquivo);
					proc.realizarCarga();
					break;
				}
				case NegocioCarga.tipoMarcaModelo: {
					MarcaModeloFile proc = new MarcaModeloFile(arquivo);
					proc.realizarCarga();
					break;
				}
				case NegocioCarga.tipoMunicipio: {
					MunicipioFile proc = new MunicipioFile(arquivo);
					proc.realizarCarga();
					break;
				}
				case NegocioCarga.tipoVeiculo: {
					VeiculoFile proc = new VeiculoFile(arquivo);
					proc.realizarCarga();
					break;
				}
				case NegocioCarga.tipoTipoVeiculo: {
					TipoVeiculoFile proc = new TipoVeiculoFile(arquivo);
					proc.realizarCarga();
					break;
				}
				case NegocioCarga.tipoRestricoes: {
					RestricaoFile proc = new RestricaoFile(arquivo);
					proc.realizarCarga();
					break;
				}
				case NegocioCarga.exportacao: {
					AutoInfracaoFile proc = new AutoInfracaoFile(arquivo);
					proc.exportar();
					break;
				}
				case NegocioCarga.exportacaoRefazer: {
					AutoInfracaoRefazerFile proc = new AutoInfracaoRefazerFile(arquivo,chave);
					proc.exportar();
					break;
				}
				}
			} catch (Exception e) {
				LogApplicacao.gravar(e.getMessage());
			} finally {
				emProcessamento=false;
				btn.habilitar();
			}
		}
	}

}
