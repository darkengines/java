package darkengines.model;

public class MatchesEmail implements IValidationRule {

	@Override
	public boolean validate(Object raw) {
		return ((String)raw).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
	}

}
