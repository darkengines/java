package server.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;

import darkengines.database.ListItem;
import server.Identity;
import server.Profile;
import server.User;

public class ProfileModel {
	private String userEmail;
	private Long userId;
	private Set<ListValueModel> programmingLanguages;
	private Set<ListValueModel> frameworks;
	private Set<ListValueModel> languages;
	private Integer diploma;
	private Integer seniority;
	private String photo;
	private String firstName;
	private String lastName;
	private String city;
	private Date birthDate;
	private String phone;
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<ListValueModel> getProgrammingLanguages() {
		return programmingLanguages;
	}

	public void setProgrammingLanguages(Set<ListValueModel> programmingLanguages) {
		this.programmingLanguages = programmingLanguages;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private Set<ListValueModel> toListValueModel(Set<? extends ListItem> items) {
		Set<ListValueModel> result = new HashSet<ListValueModel>();
		if (items != null) {
			for (ListItem item: items) {
				result.add(new ListValueModel(item));
			}
		}
		return result;
	}
	
	public ProfileModel(User user) {
		userEmail = user.getEmail();
		userId = user.getId();
		Profile profile = user.getProfile();
		Identity identity = user.getIdentity();
		if (identity != null) {
			if (identity.getCity() != null) {
				city = identity.getCity().getName();
			}
			firstName = identity.getFirstName();
			lastName = identity.getLastName();
			birthDate = identity.getBirthDate();
			phone = identity.getPhone();			
		}
		if (profile != null) {
			programmingLanguages = toListValueModel(profile.getProgrammingLanguages());
			frameworks = toListValueModel(profile.getFrameworks());
			languages = toListValueModel(profile.getLanguages());
			diploma = profile.getDiploma();
			seniority = profile.getSeniority();
			byte[] photoBytes = profile.getPhoto();
			if (photoBytes != null && photoBytes.length > 0) {
				Base64 codec = new Base64();
				photo = String.format("data:image/png;base64,%s",codec.encodeBase64String(profile.getPhoto()));
			}
		}
	}
	
	public Set<ListValueModel> getProgrammingLanguageIds() {
		return programmingLanguages;
	}
	public void setProgrammingLanguage(Set<ListValueModel> programmingLanguageIds) {
		this.programmingLanguages = programmingLanguageIds;
	}
	public Set<ListValueModel> getFrameworks() {
		return frameworks;
	}
	public void setFrameworks(Set<ListValueModel> frameworkIds) {
		this.frameworks = frameworkIds;
	}
	public Set<ListValueModel> getLanguages() {
		return languages;
	}
	public void setLanguages(Set<ListValueModel> languageIds) {
		this.languages = languageIds;
	}
	public Integer getDiploma() {
		return diploma;
	}
	public void setDiploma(Integer diploma) {
		this.diploma = diploma;
	}
	public Integer getSeniority() {
		return seniority;
	}
	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
