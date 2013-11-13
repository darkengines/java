package server.model;

import java.util.List;

import darkengines.model.NotNull;
import darkengines.model.Validator;
import darkengines.model.Validators;
import server.Diploma;

public class ProfileOutput {
	@Validators({
		@Validator(rule=NotNull.class, errorText="token.null")
	})
	private Long token;
	private List<Long> programmingLanguageIds;
	private List<Long> frameworkIds;
	private List<Long> languageIds;
	private Long diplomaId;
	private Integer seniority;
}
