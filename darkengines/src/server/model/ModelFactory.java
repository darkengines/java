package server.model;

import com.google.gson.Gson;

import darkengines.serialization.SerializerFactory;

public class ModelFactory <T> {
	private Class<?> type;
	public ModelFactory(Class<?> type) {
		this.type = type;
	}
	@SuppressWarnings("unchecked")
	public T buildModel(String json) {
		Gson gson = SerializerFactory.getSerializer();
		return (T)gson.fromJson(json, type);
	}
}
