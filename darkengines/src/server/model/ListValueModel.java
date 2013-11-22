package server.model;

import darkengines.database.ListItem;

public class ListValueModel {
	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ListValueModel(ListItem item) {
		if (item != null) {
			id = item.getId();
			name = item.getName();
		}
	}
}
