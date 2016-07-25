package br.com.martins.vendas.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;

import br.com.martins.vendas.util.ConfigUtils;
import br.com.martins.vendas.vo.Gondola;

import com.brq.mobile.framework.core.Config;
import com.brq.mobile.framework.core.ConfigAccess;

public class GondolaServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		ConfigUtils.config();
	}
	
	public void listarItensGondola() {
		try {
			Properties properties = new Properties();
			InputStream inStream = new FileInputStream(new File("C:\\Projetos\\Martins\\VendasDesenvWindows\\src\\config.properties"));
			properties.load(inStream);
			ConfigAccess.setConfig(new Config(properties));

			Integer codigoCliente = 0;
			
			List<Gondola> lista = GondolaService.listarItensGondolaCliente(codigoCliente);
			int sizeExpected = 10;
			int sizeActual = lista.size();
			Assert.assertEquals("Os tamanhos nao sao iguais.", sizeExpected, sizeActual);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}