package server.model;

import java.util.Date;

public class UserIdentityOutput {
	private String address;
	private Long birthDate;
	private String firstName;
	private String lastName;
	private CityModel city;
	private String phone;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Long birthDate) {
		this.birthDate = birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public CityModel getCity() {
		return city;
	}
	public void setCity(CityModel city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
