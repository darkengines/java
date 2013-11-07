package darkengines.importer;

import java.util.ArrayList;

public class FieldInfo {
	private boolean key;
	private ArrayList<String> keys;
	public FieldInfo() {
		keys = new ArrayList<String>();
	}
	public ArrayList<String> getKeys() {
		return keys;
	}
	public boolean isKey() {
		return key;
	}
	public void setKey(boolean key) {
		this.key = key;
	}
}
