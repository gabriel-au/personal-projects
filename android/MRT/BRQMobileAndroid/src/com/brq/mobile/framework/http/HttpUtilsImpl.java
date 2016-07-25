package com.brq.mobile.framework.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.util.Log;

public class HttpUtilsImpl implements HttpUtils {

	private static final String TAG = HttpUtilsImpl.class.getName();
	private static DefaultHttpClient httpClient;
	private static final HttpContext httpContext = new BasicHttpContext();
	private static final CookieStore cookieStore = new BasicCookieStore();

	static {

		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

	}

	private List<NameValuePair> split(String url) {
		Log.d(TAG, "Iniciando metodo split(url = " + url + ")");

		List<NameValuePair> pairs = new ArrayList<NameValuePair>();

		try {

			String[] params = url.split("&");

			for (String str : params) {

				String[] values = str.split("=");
				String name = values[0];

				String value = null;

				if (values.length > 1) {
					value = values[1];
				}

				BasicNameValuePair pair = new BasicNameValuePair(name, value);
				pairs.add(pair);
			}

		} finally {
			Log.d(TAG, "Finalizando metodo split(url = " + url + ")");
		}

		return pairs;

	}

	public String postData(String url, String encoding) {
		Log.d(TAG, "Iniciando metodo postData(url = " + url + ")");

		HttpClient httpclient = new DefaultHttpClient();
		String service = url.split("\\?")[0];
		HttpPost httppost = new HttpPost(service);
		String result = null;

		try {

			String str = service + "?";
			url = url.replaceAll(str, "").replaceAll("\\?", "");
			List<NameValuePair> list = split(url);
			httppost.setEntity(new UrlEncodedFormEntity(list));

			HttpResponse response = httpclient.execute(httppost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();

			if (statusCode == 200) {

				result = getResponse(response, encoding);

			} else {
				Log.e(TAG, "Failed to download file");
			}

		} catch (Exception e) {
			Log.e(TAG, "Failed to parse fille", e);
		} finally {
			Log.d(TAG, "Finalizando metodo postData(url = " + url + ")");
		}
		return result;
	}

	private String getResponse(HttpResponse response, String encoding) throws IOException, UnsupportedEncodingException {
		Log.d(TAG, "Iniciando metodo getResponse(response = " + response + ")");

		StringBuilder sb = new StringBuilder();

		try {

			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(content, encoding));
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			content.close();
			reader.close();

		} finally {
			Log.d(TAG, "Finalizando metodo getResponse(response = " + response + ")");
		}
		return sb.toString();
	}

	@Override
	public String getJson(String url, String encoding) {
		Log.d(TAG, "Iniciando metodo getJson(url = " + url + ")");

		String result = null;
		httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {

			HttpResponse response = httpClient.execute(httpGet, httpContext);
			StatusLine statusLine = response.getStatusLine();

			int statusCode = statusLine.getStatusCode();

			if (statusCode == 200) {

				result = getResponse(response, encoding);

			} else {
				Log.e(TAG, "Failed to download file");
			}

		} catch (Exception e) {
			Log.e(TAG, "Failed to parse fille", e);
		} finally {
			Log.d(TAG, "Finalizando metodo getJson(url = " + url + ")");
		}

		return result;

	}

}
