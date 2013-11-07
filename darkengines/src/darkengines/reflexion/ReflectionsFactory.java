package darkengines.reflexion;

import org.reflections.Reflections;

public class ReflectionsFactory {
	private static Reflections reflections = null;
	public static Reflections getReflections() {
		if (reflections == null) {
			reflections = createReflections();
		}
		return reflections;
	}
	private static Reflections createReflections() {
		return new Reflections();
	}
}
