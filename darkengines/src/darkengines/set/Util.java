package darkengines.set;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import darkengines.database.IdentifiedEntity;
import darkengines.database.ListItem;

public class Util {
	public static Map<Long, String> toMap(Set<? extends ListItem> set) {
		Map<Long, String> result = new HashMap();
		for (ListItem item: set) {
			result.put(item.getId(), item.getName());
		}
		return result;
	}
}
