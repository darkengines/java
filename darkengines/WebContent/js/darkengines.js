(function($) {
	$(document).ready(function() {
		$.datepicker.setDefaults( $.datepicker.regional[ "fr" ] );
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
						url: 'email_exists_test',
						data: {
							data: $email.val(),
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
					data: {
						data: JSON.stringify($form.serializeObject())
					},
					success: function(token) {
						$.cookie('token', token);
						window.location.href = 'edit_dev_identity';
					},
					error: function() {
						
					}
				});
				e.preventDefault();
			});
		});
		$('form.UpdateIdentity').each(function() {
			var $form = $(this);
			var $city_ui = $('input[name=city_ui]');
			var $birthDate = $('input[name=birthDate]');
			var $city = $('input[name=cityId]');
			var $birthDate_ui = $('input[name=birthDate_ui]');
			$birthDate_ui.each(function() {
				var $this = $(this);
				$this.datepicker({
					dateFormat: "dd/mm/yy",
					onSelect: function() {
						$birthDate.val($(this).datepicker('getDate').getTime());
					}
				});
			});
			$city_ui.each(function() {
				var $this = $(this);
				$this.autocomplete({
					source: function (request, response) {
				         $.ajax({
				             url: "cities_test",
				             data: { data: request.term },
				             dataType: "json",
				             success: function(data) {
				            	 response($.map(data, function(value, key) {
				            		 return {label:value, id:key};
				            	 }));
				             },
				             error: function () {
				                 response([]);
				             }
				         });
				     },
				     change: function(event, $ui) {
		            	 $city.val($ui.item.id);
		             },
				});
			});
			$form.submit(function(e) {
				var data = $form.serializeObject();
				delete data['birthDate_ui'];
				delete data['city_ui'];
				data.token = $.cookie('token');
				$.ajax({
					url: $form.attr('action'),
					data: {
						data: JSON.stringify(data)
					},
					success: function(token) {
						$.cookie('token', token);
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
		$.fn.serializeObject = function()
		{
		    var o = {};
		    var a = this.serializeArray();
		    $.each(a, function() {
		        if (o[this.name] !== undefined) {
		            if (!o[this.name].push) {
		                o[this.name] = [o[this.name]];
		            }
		            o[this.name].push(this.value || '');
		        } else {
		            o[this.name] = this.value || '';
		        }
		    });
		    return o;
		};
	});
})(jQuery);
