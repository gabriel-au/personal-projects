package com.brq.mobile.framework.win;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class PluginRead {

	public PluginRead() {}

	/**
	 * 
	 * @param xmlPath
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static Map<String, String> readXML() throws IOException, XmlPullParserException {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setValidating(false);

		XmlPullParser xml = factory.newPullParser();

		InputStream inStream = ClassLoader.getSystemResourceAsStream("plugins.xml");

		InputStream raw = new BufferedInputStream(inStream);
		xml.setInput(raw, null);

		Map<String, String> map = new HashMap<String, String>();

		int eventType = xml.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {

			if (eventType == XmlPullParser.START_TAG) {

				String strNode = xml.getName();

				if (strNode.equals("plugin")) {

					map.put(xml.getAttributeValue(null, "name"), xml.getAttributeValue(null, "value"));

				}
			}
			eventType = xml.next();
		}

		return map;
	}

}
