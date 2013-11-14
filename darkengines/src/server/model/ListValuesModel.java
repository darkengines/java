package server.model;

import java.util.HashSet;
import java.util.Set;

import darkengines.database.ListItem;

public class ListValuesModel {
	private Set<ListValueModel> items;
	public ListValuesModel(Set<? extends ListItem> items) {
		this.items = new HashSet<ListValueModel>();
		for (ListItem item: items) {
			this.items.add(new ListValueModel(item));
		}
	}
	public Set<ListValueModel> getItems() {
		return items;
	}
}
