package server.model;

public class MatchesEmail implements IValidationRule {

	@Override
	public boolean validate(String raw) {
		return raw.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
	}

}
