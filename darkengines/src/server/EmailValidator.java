package server;

import java.util.Map;

public class EmailValidator implements IValidator {

	@Override
	public ValidatorResult validate(String[] strings, Map<String, String[]> parameters) {
		String raw = strings[0];
		if (!raw.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
			return new ValidatorResult("Courriel invalide");
		}
		return null;
	}

}
