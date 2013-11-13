package darkengines.model;

public class NotNull implements IValidationRule {

	@Override
	public boolean validate(Object raw) {
		return raw != null;
	}

}
