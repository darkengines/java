package server;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import darkengines.database.IdentifiedEntity;

@Entity
public class Profile extends IdentifiedEntity {
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ProgrammingLanguage> programmingLanguages;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Framework> frameworks;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Language> languages;
	@ManyToOne
	private Diploma diploma;
	private Integer seniority;
	@Lob
	private byte[] photo;
	public Profile() {
		programmingLanguages = new HashSet<ProgrammingLanguage>();
		frameworks = new HashSet<Framework>();
		languages = new HashSet<Language>();
	}
	public Set<ProgrammingLanguage> getProgrammingLanguages() {
		return programmingLanguages;
	}
	public void setProgrammingLanguages(
			Set<ProgrammingLanguage> programmingLanguages) {
		this.programmingLanguages = programmingLanguages;
	}
	public Set<Framework> getFrameworks() {
		return frameworks;
	}
	public void setFrameworks(Set<Framework> frameworks) {
		this.frameworks = frameworks;
	}
	public Set<Language> getLanguages() {
		return languages;
	}
	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}
	public Diploma getDiploma() {
		return diploma;
	}
	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}
	public Integer getSeniority() {
		return seniority;
	}
	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
