package darkengines.importer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassInfo {
	private Map<String, FieldInfo> fieldInfos;
	public ClassInfo() {
		fieldInfos = new HashMap<String, FieldInfo>();
	}
	public Map<String, FieldInfo> getFieldInfos() {
		return fieldInfos;
	}
}
