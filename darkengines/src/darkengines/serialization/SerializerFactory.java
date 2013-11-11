package darkengines.serialization;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;

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
			.registerTypeAdapter(Date.class, new DateSerializer())
			.registerTypeAdapter(Date.class, new DateDeserializer())
			.create();
		return gson;
	}
}
