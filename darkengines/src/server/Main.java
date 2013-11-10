package server;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import darkengines.importer.Importer;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException, IOException {
		//Importer importer = new Importer();
		//importer.Import("J:/java/darkengines/data/data.xlsx");
		String s = "caca";
		String s2 = "caca";
		try {
			String test = User.hashPassword(s);
			String test2 = User.hashPassword(s2);
			boolean ok = test.equals(test2);
			System.out.println(ok);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
