package server.model;

import java.util.List;

import darkengines.model.NotNull;
import darkengines.model.Validator;
import darkengines.model.Validators;
import server.Diploma;

public class ProfileInput {
	@Validators({
		@Validator(rule=NotNull.class, errorText="token.null")
	})
	private Long token;
	private List<Long> programmingLanguageIds;
	private List<Long> frameworkIds;
	private List<Long> languageIds;
	private Long diplomaId;
	private Integer seniority;
	public Long getToken() {
		return token;
	}
	public void setToken(Long token) {
		this.token = token;
	}
	public List<Long> getProgrammingLanguageIds() {
		return programmingLanguageIds;
	}
	public void setProgrammingLanguageIds(List<Long> programmingLanguageIds) {
		this.programmingLanguageIds = programmingLanguageIds;
	}
	public List<Long> getFrameworkIds() {
		return frameworkIds;
	}
	public void setFrameworkIds(List<Long> frameworkIds) {
		this.frameworkIds = frameworkIds;
	}
	public List<Long> getLanguageIds() {
		return languageIds;
	}
	public void setLanguageIds(List<Long> languageIds) {
		this.languageIds = languageIds;
	}
	public Long getDiplomaId() {
		return diplomaId;
	}
	public void setDiplomaId(Long diplomaId) {
		this.diplomaId = diplomaId;
	}
	public Integer getSeniority() {
		return seniority;
	}
	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}
}
