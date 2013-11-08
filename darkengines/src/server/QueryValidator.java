package server;

import java.util.HashMap;
import java.util.Map;

public abstract class QueryValidator {
	protected Map<String, IValidator> validators;
	public QueryValidator() {
		validators = new HashMap<String, IValidator>();
		setValidators();
	}
	protected abstract void setValidators();
	
	public QueryValidatorResult validate(Map<String, String[]> parameters) {
		QueryValidatorResult result = new QueryValidatorResult();
		for (String key: validators.keySet()) {
			ValidatorResult singleResult = validators.get(key).validate(parameters.get(key), parameters);
			if (singleResult != null) {
				result.getResults().put(key, singleResult);
			}
		}
		return result;
	}
}
