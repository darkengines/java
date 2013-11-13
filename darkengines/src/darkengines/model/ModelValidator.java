package darkengines.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import darkengines.reflexion.ReflectionsFactory;

public class ModelValidator<T> {
	private Class<?> type;
	public ModelValidator(Class<?> inputType) {
		type = inputType;
	}

	public void validate(T input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InvalidFormatException {
		Field[] fields = type.getDeclaredFields();
		for (Field field: fields) {
			if (field.isAnnotationPresent(Validators.class)) {
				Validators validators = field.getAnnotation(Validators.class);
				for (Validator validator: validators.value()) {
					IValidationRule rule = validator.rule().getConstructor().newInstance();
					field.setAccessible(true);
					if (!rule.validate(field.get(input))) {
						throw new InvalidFormatException(validator.errorText(), null);
					}
				}
			}
		}
	}
}
