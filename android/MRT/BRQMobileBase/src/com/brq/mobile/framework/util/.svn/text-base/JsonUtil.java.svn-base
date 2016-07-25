package com.brq.mobile.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.log.Log;

/**
 * Classe utilit�ria relacionada � JSON.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class JsonUtil {

	private final static String TAG = JsonUtil.class.getName();

	/**
	 * Método responsável por converter um objeto
	 * 
	 * <code>java.util.List<Map<String, String>></code> para um objeto do tipo <code>org.json.JSONObject</code>.
	 * 
	 * @param jsonName nome do objeto json
	 * @param list objeto do tipo <code>java.util.List<Map<String, String>></code>.
	 * @return JSONObject objeto do tipo <code>org.json.JSONObject</code>.
	 * 
	 * @throws JSONException
	 */
	public static JSONArray toSimpleJsonArray(List<Map<String, String>> list) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		JSONArray children = new JSONArray();
		try {
			for (Map<String, String> map : list) {

				jsonObject = new JSONObject();
				Set<String> keys = map.keySet();
				for (String key : keys) {
					jsonObject.put(key, map.get(key));
				}
				children.put(jsonObject);
				// json.put(jsonName, children);
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
		return children;
	}

	public static JSONObject toSimpleJsonString(List<Map<String, String>> list) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		try {
			for (Map<String, String> map : list) {

				jsonObject = new JSONObject();
				Set<String> keys = map.keySet();
				for (String key : keys) {
					jsonObject.put(key, map.get(key));
				}
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
		return jsonObject;
	}

	public static JSONObject toJsonString(String jsonName, List<Map<String, String>> list) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		JSONArray children = new JSONArray();
		JSONObject json = new JSONObject();
		try {
			for (Map<String, String> map : list) {

				jsonObject = new JSONObject();
				Set<String> keys = map.keySet();
				for (String key : keys) {
					jsonObject.put(key, map.get(key));
				}
				children.put(jsonObject);
				json.put(jsonName, children);
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
		return json;
	}

	/**
	 * Método responsável por converter um objeto
	 * 
	 * <code>java.util.List<Map<String, String>></code> para um objeto do tipo <code>org.json.JSONObject</code>.
	 * 
	 * @param jsonName nome do objeto json
	 * @param list objeto do tipo <code>java.util.List<Map<String, String>></code>.
	 * @return JSONObject objeto do tipo <code>org.json.JSONObject</code>.
	 * @throws JSONException
	 */
	public static JSONObject listMaptoJson(String jsonName, List<Map<String, Object>> list) throws JSONException {

		JSONArray children = new JSONArray();
		JSONObject json = new JSONObject();
		try {
			for (Map<String, Object> map : list) {
				children.put(mapToJson(map));
				json.put(jsonName, children);
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject mapToJson(Map<String, Object> map) throws JSONException {
		try {
			JSONObject jsonObject = new JSONObject();
			Set<String> keys = map.keySet();
			for (String key : keys) {
				Object value = map.get(key);
				if (value instanceof BaseVo) {
					jsonObject.put(key, toJson((BaseVo) value));
				} else if (value instanceof List) {
					jsonObject.put(key, toJsonArray((List<? extends BaseVo>) value));
				} else if (value instanceof Map) {
					jsonObject.put(key, mapToJson((Map<String, Object>) value));
				} else {
					if (value != null) {
						jsonObject.put(key, value);
					}
				}
			}
			return jsonObject;
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			throw new JSONException(e.getMessage());
		}
	}

	/**
	 * M�todo respons�vel por converter um objeto do tipo <code>com.brq.mobile.framework.core.BaseVo</code> para um objeto do tipo <code>org.json.JSONObject</code>.
	 * 
	 * @param jsonName nome do objeto json
	 * @param baseVo objeto do tipo <code>com.brq.mobile.framework.core.BaseVo</code>.
	 * @return JSONObject objeto do tipo <code>org.json.JSONObject</code>.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject toJson(String jsonName, BaseVo baseVo) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		JSONObject json = new JSONObject();
		Object value;
		try {
			Map<String, Object> map = baseVo.toMap();
			Set<String> keys = map.keySet();
			for (String key : keys) {
				value = map.get(key);
				if (value instanceof BaseVo) {
					jsonObject.put(key, toJson((BaseVo) value));
				} else if (value instanceof List) {

					List<?> list = (List<?>) value;
					if (!list.isEmpty() && list.get(0) instanceof BaseVo) {

						jsonObject.put(key, toJsonArray((List<? extends BaseVo>) value));

					} else if (!list.isEmpty() && list.get(0) instanceof String) {

						jsonObject.put(key, toJsonArrayString((List<String>) value));
					}

				} else {
					if (value != null) {
						jsonObject.put(key, value);
					}

				}
			}
			json.put(jsonName, jsonObject);

		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject toJson(BaseVo baseVo) throws JSONException {
		
		JSONObject jsonObject = new JSONObject();
		Object value;
		try {
			Map<String, Object> map = baseVo.toMap();
			Set<String> keys = map.keySet();
			for (String key : keys) {
				value = map.get(key);
				if (value instanceof BaseVo) {

					jsonObject.put(key, toJson((BaseVo) value));

				} else if (value instanceof List) {

					jsonObject.put(key, toJsonArray((List<? extends BaseVo>) value));

				} else {

					if (value != null) {
						jsonObject.put(key, value);
					}
				}
			}
			return jsonObject;
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

	/**
	 * M�todo respons�vel por converter um objeto do tipo <code>java.util.List<Map<String, String>></code> para um objeto do tipo <code>org.json.JSONObject</code>.
	 * 
	 * @param jsonName nome do objeto json.
	 * @param list objeto do tipo <code>java.util.List<Map<String, String>></code>.
	 * @return JSONObject objeto do tipo <code>org.json.JSONObject</code>.
	 * @throws JSONException
	 */
	public static JSONObject toJson(String jsonName, List<? extends BaseVo> list) throws JSONException {
		List<Map<String, Object>> listaMapas = new ArrayList<Map<String, Object>>();
		for (BaseVo baseVo : list) {
			listaMapas.add(baseVo.toMap());
		}
		return listMaptoJson(jsonName, listaMapas);
	}

	public static JSONArray toJsonArray(List<? extends BaseVo> list) throws JSONException {
		List<Map<String, Object>> listaMapas = new ArrayList<Map<String, Object>>();
		for (BaseVo baseVo : list) {
			listaMapas.add(baseVo.toMap());
		}
		return listMaptoJsonArray(listaMapas);
	}

	@SuppressWarnings("unchecked")
	public static JSONArray listMaptoJsonArray(List<Map<String, Object>> list) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		JSONArray children = new JSONArray();
		Object value;

		try {
			for (Map<String, Object> map : list) {
				jsonObject = new JSONObject();
				Set<String> keys = map.keySet();
				for (String key : keys) {
					value = map.get(key);
					if (value instanceof BaseVo) {

						jsonObject.put(key, toJson((BaseVo) value));

					} else if (value instanceof List) {

						jsonObject.put(key, toJsonArray((List<? extends BaseVo>) value));

					} else if (value instanceof Map) {

						jsonObject.put(key, mapToJson((Map<String, Object>) value));

					} else {

						if (value != null) {

							jsonObject.put(key, value);

						}
					}
				}
				children.put(jsonObject);
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
		return children;
	}

	/**
	 * 
	 * @param jsonReturn
	 * @param list
	 * @return
	 * @throws JSONException
	 */
	public static JSONArray toJsonArrayString(List<String> list) throws JSONException {

		JSONArray jsonArray = new JSONArray();
		if (list.isEmpty()) {
			return jsonArray;
		}
		for (int count = 0; count < list.size(); count++) {
			jsonArray.put(list.get(count));
		}
		return jsonArray;
	}

	/**
	 * 
	 * @param jsonReturn
	 * @param list
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject toJsonObject(String jsonName, List<String> list) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		if (list.isEmpty()) {
			jsonObject.accumulate(jsonName, "");
			return jsonObject;
		}
		for (int count = 0; count < list.size(); count++) {
			jsonObject.accumulate(jsonName, list.get(count));
		}
		return jsonObject;
	}

	/**
	 * Converter JsonArray em List&lt;String&gt;
	 * 
	 * @param array JsonArray
	 * @return List&lt;String&gt;
	 * @throws JSONException
	 */
	public static List<String> jsonArraytoStringList(JSONArray array) throws JSONException {
		List<String> lst = new ArrayList<String>();
		for (int count = 0; count < array.length(); count++) {
			lst.add(array.get(count).toString());
		}
		return lst;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, String> toMap(JSONObject object) throws JSONException {
		Map<String, String> map = new HashMap();
		Iterator keys = object.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			map.put(key, fromJson(object.get(key)).toString());
		}
		return map;
	}

	private static Object fromJson(Object json) throws JSONException {
		if (json instanceof JSONObject) {
			return toMap((JSONObject) json);
		} else if (json instanceof JSONArray) {
			return toList((JSONArray) json);
		} else {
			return json;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(JSONArray array) throws JSONException {
		List list = new ArrayList();
		for (int i = 0; i < array.length(); i++) {
			list.add(fromJson(array.get(i)));
		}
		return list;
	}

}