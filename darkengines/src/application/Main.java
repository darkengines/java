package application;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import darkengines.importer.Importer;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException, IOException {
		Importer importer = new Importer();
		importer.Import("/home/quick/java_pro/darkengines/data/data.xlsx");
	}
}
