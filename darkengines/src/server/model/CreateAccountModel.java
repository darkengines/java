package server.model;

public class CreateAccountModel {
	@Validators({
		@Validator(errorText = "Ne peut �tre vide", rule = NotNull.class),
		@Validator(errorText = "Courriel Invalide", rule = MatchesEmail.class)
	})
	private String email;
	@Validators({
		@Validator(errorText = "Ne peut �tre vide", rule = NotNull.class)
	})
	private String password;
}
