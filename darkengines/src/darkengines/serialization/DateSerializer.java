package darkengines.serialization;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateSerializer implements JsonSerializer<Date> {

	@Override
	public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
		String value = "null";
		if (date != null) {
			value = Long.toString(date.getTime());
		}
		return new JsonPrimitive(value);
	}

}
