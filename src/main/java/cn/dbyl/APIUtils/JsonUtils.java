package main.java.cn.dbyl.APIUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by young on 5/11/16.
 */
public class JsonUtils {
	public static JsonUtils jsonUtils;

	private JsonUtils() {

	}

	public static JsonUtils getInstance() {
		if (jsonUtils == null) {
			synchronized (JsonUtils.class) {

				if (jsonUtils == null) {
					jsonUtils = new JsonUtils();
				}

			}
		}
		return jsonUtils;
	}

	/**
	 * @author young
	 * @param str
	 * @param clazz
	 * @return
	 */
	public Object parseStringToBean(String str, Class<?> clazz) {
		Object obj = null;
		Gson gson = new Gson();
		try {
			obj = gson.fromJson(str, clazz);
		} catch (Exception e) {

		}

		return obj;
	}

	public <T> List<T> parseJsonToList(String json, Class<T[]> clazz) {
		Gson gson = new Gson();
		T[] array = gson.fromJson(json, clazz);
		return Arrays.asList(array);
	}

	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public <T> ArrayList<T> parseJsonToArrayList(String json, Class<T> clazz) {
		Type type = new TypeToken<ArrayList<JsonObject>>() {
		}.getType();
		ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
		ArrayList<T> arrayList = new ArrayList<>();
		for (JsonObject jsonObject : jsonObjects) {
			arrayList.add(new Gson().fromJson(jsonObject, clazz));
		}
		return arrayList;
	}
}
