package server.model;

import server.City;

public class CityModel {
	private String name;
	private Long id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CityModel(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public CityModel(City city) {
		id = city.getId();
		name = city.getName();
	}
}
