package server;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import darkengines.database.IdentifiedEntity;

@Entity
public class Identity extends IdentifiedEntity {
	private String lastName;
	private String firstName;
	private Date birthDate;
	@ManyToOne
	private City city;
	private String address;
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
