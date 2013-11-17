package darkengines.serialization;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerializerFactory {
	private static Gson gson = null;

	public static Gson getSerializer() {
		if (gson == null) {
			gson = createGson();
		}
		return gson;
	}

	private static Gson createGson() {
		Gson gson = new GsonBuilder()
			.serializeNulls()
			.registerTypeAdapter(Date.class, new DateSerializer())
			.registerTypeAdapter(Date.class, new DateDeserializer())
			.create();
		return gson;
	}
}
