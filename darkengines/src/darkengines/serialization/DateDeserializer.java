package darkengines.serialization;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class DateDeserializer implements JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		try {
			long time = json.getAsJsonPrimitive().getAsLong();
			Date date = new Date();
			date.setTime(time);
			return date;
		} catch (NumberFormatException e) {
			throw new JsonParseException("Invalid timestamp", e);
		}
	}

}
