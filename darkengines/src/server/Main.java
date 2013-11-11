package server;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import server.model.ModelFactory;
import server.model.TestClass;
import darkengines.importer.Importer;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException, IOException {
		String test = "{email: rivarol89@hotmail.com, date: date}";
		TestClass c = new ModelFactory<TestClass>(TestClass.class).buildModel(test);
		System.out.println(c);
	}
}
