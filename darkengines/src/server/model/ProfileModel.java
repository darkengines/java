package server.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;

import darkengines.database.ListItem;

import server.Profile;
import server.User;

public class ProfileModel {
	private String userEmail;
	private Long userId;
	private Set<ListValueModel> programmingLanguages;
	private Set<ListValueModel> frameworks;
	private Set<ListValueModel> languages;
	private ListValueModel diploma;
	private Integer seniority;
	private String photo;
	
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
		if (profile != null) {
			programmingLanguages = toListValueModel(profile.getProgrammingLanguages());
			frameworks = toListValueModel(profile.getFrameworks());
			languages = toListValueModel(profile.getLanguages());
			diploma = new ListValueModel(profile.getDiploma());
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
	public ListValueModel getDiploma() {
		return diploma;
	}
	public void setDiploma(ListValueModel diploma) {
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
