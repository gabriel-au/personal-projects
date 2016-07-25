package br.com.martins.vendas.dao.temp;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.util.ConfigUtils;

public class TabelaTmpMerRecDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		ConfigUtils.config();
	}
	
	@Before
	public void setUp() {
//		List<Item> listaItem = ItemDisponivelDAO.listaTodosItensDisponiveis();
//		serializaListaItem(listaItem);
//		serializaItem(listaItem.get(0));
//
//		for (Item item : listaItem) {
//			TabelaTmpGrpDeDAO.insereItem(item);
//		}
	}

	@After
	public void tearDown() {
//		TabelaTmpGrpDeDAO.limpaTabela();
	}
	
	@Test
	public void testLimpaTabela() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsere() {
		fail("Not yet implemented");
	}

	@Test
	public void testAltera() {
		fail("Not yet implemented");
	}

	@Test
	public void testExclui() {
		fail("Not yet implemented");
	}

	@Test
	public void testObtem() {
		fail("Not yet implemented");
	}

	@Test
	public void testObtemTodos() {
		fail("Not yet implemented");
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private static List<Item> desserializaListaItem() {
		List<Item> listaItem = null;
		try {
			File file = new File("/mnt/sdcard/listaItem.ser");
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			listaItem = (List<Item>) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaItem;
	}
	
}
