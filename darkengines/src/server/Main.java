package server;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import com.google.gson.JsonParseException;

import darkengines.importer.Importer;
import darkengines.model.ModelFactory;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException, IOException {
		Importer importer = new Importer();
		importer.Import("J:/java/darkengines/data/data.xlsx");
	}
}
