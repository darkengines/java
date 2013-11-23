package server.model;

import java.util.Date;

import darkengines.model.NotNull;
import darkengines.model.Validators;
import darkengines.model.Validator;

public class UserIdentityInput {
	@Validators({
		@Validator(rule=NotNull.class, errorText="token.null")
	})
	private String token;
	private String address;
	private Date birthDate;
	private String firstName;
	private String lastName;
	private Long cityId;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
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
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
}
