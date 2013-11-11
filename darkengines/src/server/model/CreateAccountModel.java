package server.model;

public class CreateAccountModel {
	@Validators({
		@Validator(errorText = "Ne peut être vide", rule = NotNull.class),
		@Validator(errorText = "Courriel Invalide", rule = MatchesEmail.class)
	})
	public String email;
}
