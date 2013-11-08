package server;

import java.util.HashMap;
import java.util.Map;

public class QueryValidatorResult {
	protected Map<String, ValidatorResult> results;
	public QueryValidatorResult() {
		results = new HashMap<String, ValidatorResult>();
	}
	public Map<String, ValidatorResult> getResults() {
		return results;
	}
	public boolean isEmpty() {
		return results.isEmpty();
	}
	public Map<String, String> asMap() {
		Map<String, String> result = new HashMap<String, String>();
		for (String key: results.keySet()) {
			result.put(key, results.get(key).getMessage());
		}
		return result;
	}
}
