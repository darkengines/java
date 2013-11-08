(function($) {
	$(document).ready(function() {
		$('form .Field label').each(function() {
			var $label = $(this);
			var $field = $label.parent();
			var id = $label.attr('for');
			var $input = $('#'+id, $field);
			$input.focus(function() {
				$label.hide();
			});
			$input.blur(function() {
				if ($input.val() == '') {
					$label.show();
				}
			});		
		});
		$('form .Field .File').each(function() {
			var $container = $(this);
			var $browse = $('.Browse', $container);
			var $input = $('input[type=file]', $container);
			var $filename = $('.Filename', $container);
			$input.change(function() {
				var filename = $input.val();
				if (filename != '') {
					$filename.text(filename);
				} else {
					$filename.text('File');
				}
			});
			$browse.click(function() {
				$input.click();
			});
		});
		$('form.CreateAccount').each(function() {
			var $form = $(this);
			var $button = $('button', $form);
			var $email = $('input[name=email]', $form);
			var $emailResult = $('.Result', $email.parent());
			var $password = $('input[name=password]', $form);
			var $passwordConfirmation = $('input[name=password_confirmation]', $form);
			var $passwordResult = $('.Result', $passwordConfirmation.parent());
			$button.click(function() {
				$form.append($('<input type="hidden" name="type" value="'+$(this).val()+'"/>'));
			});
			$email.bind('keyup change', function() {
				if (!isEmail($email.val())) {
					$emailResult.removeClass('Ok').addClass('Error');
					$emailResult.text('Ce courriel n\'est pas valide');
				} else {
					$.ajax({
						url: 'email_exists',
						data: {
							email: $email.val(),
						},
						success: function(result) {
							if (result) {
								$emailResult.removeClass('Ok').addClass('Error');
								$emailResult.text('Ce courriel est déjà utilisé');
							} else {
								$emailResult.removeClass('Error').addClass('Ok');
								$emailResult.text('Ok');
							}
						},
						error: function(result) {
							$emailResult.removeClass('Ok').addClass('Error');
							$emailResult.text(result.email);
						}
					});
				}
			});
			$passwordConfirmation.bind('keyup change', function() {
				if ($passwordConfirmation.val() != $password.val()) {
					$passwordResult.removeClass('Ok').addClass('Error');
					$passwordResult.text('Les mots de passe de correspondent pas');
				} else {
					$passwordResult.removeClass('Error').addClass('Ok');
					$passwordResult.text('Ok');
				}
			});
			$form.submit(function(e) {
				$.ajax({
					url: $form.attr('action'),
					data: $form.serialize(),
					success: function() {
						window.location.href = 'edit_dev_identity';
					},
					error: function() {
						
					}
				});
				e.preventDefault();
			});
		});
		function isEmail(raw) {
			return raw.match("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
		}
	});
})(jQuery);
