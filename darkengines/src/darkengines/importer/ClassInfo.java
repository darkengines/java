package darkengines.importer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassInfo {
	private ArrayList<String> keyFields;
	private Map<String, FieldInfo> fieldInfos;
	public ClassInfo() {
		keyFields = new ArrayList<String>();
		fieldInfos = new HashMap<String, FieldInfo>();
	}
	public ArrayList<String> getKeyFields() {
		return keyFields;
	}
	public Map<String, FieldInfo> getFieldInfos() {
		return fieldInfos;
	}
}
