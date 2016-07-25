package br.com.martins.vendas.dao.temp;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.util.ConfigUtils;

public class TabelaTmpPcaBcdDAOTest {

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

	@SuppressWarnings("unused")
	private static void serializaListaItem(List<Item> listaItem) {
		ObjectOutput objectOutput;
		try {
			objectOutput = new ObjectOutputStream(new FileOutputStream("/mnt/sdcard/listaItem.ser"));
			objectOutput.writeObject(listaItem);
			objectOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	@SuppressWarnings("unused")
	private static void serializaItem(Item item) {
		ObjectOutput objectOutput;
		try {
			objectOutput = new ObjectOutputStream(new FileOutputStream("/mnt/sdcard/item.ser"));
			objectOutput.writeObject(item);
			objectOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static Item desserializaItem() {
		Item item = null;
		try {
			File file = new File("/mnt/sdcard/item.ser");
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			item = (Item) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return item;
	}
	
}
