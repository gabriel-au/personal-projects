package br.com.martins.vendas.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import br.com.martins.vendas.vo.ItemMenu;
import br.com.martins.vendas.vo.Menu;
import br.com.martins.vendas.vo.NavigationConfig;

import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class MenuService {

	private static final String TAG = MenuService.class.getName();

	public static JSONObject carregaMenu(String arquivo) throws Exception {

		List<Menu> menuList;
		List<NavigationConfig> navigationConfigList;
		Menu menu = null;
		XmlPullParserFactory factory;
		XmlPullParser xml;
		File file;
		InputStream raw = null;
		int eventType;
		String strNode;
		try {
			menuList = new ArrayList<Menu>();
			navigationConfigList = new ArrayList<NavigationConfig>();
			factory = XmlPullParserFactory.newInstance();
			factory.setValidating(false);
			xml = factory.newPullParser();
			file = new File(arquivo);
			raw = new BufferedInputStream(new FileInputStream(file));
			xml.setInput(raw, null);
			eventType = xml.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					strNode = xml.getName();
					/*if (strNode.equals("MenuConfig")) {
						docBase = xml.getAttributeValue(null, "docBase");
					} else */
					if (strNode.equals("Menu")) {
						menu = processaNoMenu(xml);
						if (hasAcessoMenu(menu)) {
							menuList.add(menu);
						}
					} else if ("Item".equals(xml.getName())) {
						if (hasAcessoMenu(menu)) {
							ItemMenu itemMenu = processaNoItemMenu(xml);
							menu.addItem(itemMenu);
						}
					}else if (strNode.equals("view")) {
						navigationConfigList.add(processaNoView(xml));
					}
				}
				eventType = xml.next();
			}
			
			JSONObject jsonMenu = new JSONObject();
			jsonMenu.put("menuList", JsonUtil.toJsonArray(menuList));
			jsonMenu.put("navigationConfigList", JsonUtil.toJsonArray(navigationConfigList));
			return jsonMenu;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		} finally {
			if (raw != null) {
				raw.close();
			}
		}
	}

	private static boolean hasAcessoMenu(Menu menu) {
		if (menu.roles == null || isUserInRole(menu.roles)) {
			return true;
		}
		return false;
	}

	private static boolean isUserInRole(String... roles) {

		for(String role : roles){
			if(RepresentanteService.roles.containsKey(role)){
				return true;
			}
		}
		return false;
	}

	private static Menu processaNoMenu(XmlPullParser xml) throws JSONException {
		Menu menu = new Menu();
		menu.name = getXmlValue(xml, "name");
		menu.title = getXmlValue(xml, "title");
		menu.roles = getXmlValue(xml, "roles") != null && !"".equals(getXmlValue(xml, "roles")) ? getXmlValue(xml, "roles").split(",") : null;
		return menu;
	}

	private static ItemMenu processaNoItemMenu(XmlPullParser xml) {
		ItemMenu itemMenu = new ItemMenu();
		itemMenu.name = getXmlValue(xml, "name");
		itemMenu.title = getXmlValue(xml, "title");
		itemMenu.viewName = getXmlValue(xml, "viewName");
		return itemMenu;
	}
	
	private static NavigationConfig processaNoView(XmlPullParser xml) {
		NavigationConfig navigationConfig = new NavigationConfig();
		navigationConfig.name = getXmlValue(xml, "name");
		navigationConfig.page = getXmlValue(xml, "page");
		return navigationConfig;
	}

	private static String getXmlValue(XmlPullParser xml, String attribute) {
		return xml.getAttributeValue(null, attribute);
	}

}
