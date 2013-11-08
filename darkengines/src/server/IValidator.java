package server;

import java.util.Map;

public interface IValidator {

	public ValidatorResult validate(String[] strings, Map<String, String[]> parameters);

}
