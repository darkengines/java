package caller_offerrer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import darkengines.database.IdentifiedEntity;

@Entity
public class SearchCallQuery extends IdentifiedEntity {
	@ManyToMany()
	private Set<ProgrammingLanguage> programmingLanguages;
	@ManyToMany()
	private Set<Framework> frameworks;
	@ManyToMany()
	private Set<Language> languages;
	private Integer diploma;
	private Integer seniority;
	public SearchCallQuery() {
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
}
