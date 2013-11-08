package darkengines.serialization;

import com.google.gson.Gson;

public class SerializerFactory {
	private static Gson gson = null;
	public static Gson getSerializer() {
		if (gson == null) {
			gson = createGson();
		}
		return gson;
	}
	private static Gson createGson() {
		return new Gson();
	}
}
