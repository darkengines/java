package server.model;

public class NotNull implements IValidationRule {

	@Override
	public boolean validate(String raw) {
		return raw != null;
	}

}
