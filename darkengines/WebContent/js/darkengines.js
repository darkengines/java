(function($) {
	$(document).ready(function() {
		application = {
			user: null
		};
		if (typeof($.cookie('token')) != 'undefined') {
			application.user = eval('('+$.cookie('token')+')');
		}
		application.disableForm = function($form, b) {
			$('input', $form).each(function() {
				$input = $(this);
				if (b) {
					$input.attr('disabled', true);
				} else {
					$input.removeAttr('disabled');
				}
			});
		};
		application.processLabels = function($form) {
			$('form.LabelInline .Field label', $form).each(function() {
				var $label = $(this);
				var $field = $label.parent();
				var id = $label.attr('for');
				var $input = $('#'+id, $field);
				if ($input.val() != '') {
					$label.hide();
				}	
			});
		};
		$.datepicker.setDefaults( $.datepicker.regional[ "fr" ] );
		$('form.LabelInline .Field label').each(function() {
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
		$('form.Login').each(function() {
			$form = $(this);
			$form.submit(function(e) {
				$.ajax({
					url: $form.attr('action'),
					data: {
						data: JSON.stringify($form.serializeObject())
					},
					success: function(token) {
						$.cookie('token', JSON.stringify(token));
						var url = '..';
						if ($.url().param('url') != null) {
							url = $.url().param('url');
						}
						window.location.href = url;
					},
					error: function() {
						
					}
				});
				e.preventDefault();
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
						$.cookie('token', JSON.stringify(token));
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
			var $lastName = $('input[name=lastName]');
			var $firstName = $('input[name=firstName]');
			var $address = $('input[name=address]');
			var $city_ui = $('input[name=city_ui]');
			var $birthDate = $('input[name=birthDate]');
			var $city = $('input[name=cityId]');
			var $birthDate_ui = $('input[name=birthDate_ui]');
			var $notifier = $('.Notification');
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
			$.ajax({
				url: 'get_identity_test',
				data: {
					data: application.user.userId
				},
				success: function(data) {
					$lastName.val(data.lastName);
					$firstName.val(data.firstName);
					$address.val(data.address);
					$city.val(data.city.id);
					$city_ui.val(data.city.name);
					$birthDate_ui.datepicker('setDate', (new Date(data.birthDate)));
					$birthDate.val(data.birthDate);
					application.processLabels($form);
				},
				beforeSend: function() {
					application.disableForm($form, true);
	            	$notifier.addClass('Loading');
	            },
				complete: function() {
					$notifier.removeClass('Loading');
					application.disableForm($form, false);
				}
			});
			$form.submit(function(e) {
				var data = $form.serializeObject();
				delete data['birthDate_ui'];
				delete data['city_ui'];
				data.token = application.user.sessionId;
				$.ajax({
					url: $form.attr('action'),
					data: {
						data: JSON.stringify(data)
					},
					success: function(token) {

					},
					error: function() {
						
					}
				});
				e.preventDefault();
			});
		});
		$('form.UpdateProfile').each(function() {
			var $form = $(this);
			var $programmingLanguages = $('input[name=programmingLanguageIds]');
			var $frameworks = $('input[name=frameworkIds]');
			var $languages = $('input[name=languageIds]');
			var $notifier = $('.Notification');
			var $diploma_ui = $('input[name=diploma_ui]');
			var $diploma = $('input[name=diplomaId]');
			var $seniority = $('input[name=seniority]');
			var $seniority_ui = $('.SeniorityUi');
			$programmingLanguages.each(function() {
				$this = $(this);
				$this.magicSuggest({
					data: function(query, reponse) {
						$.ajax({
							url: 'programming_languages_test',
							data: {
								data: JSON.stringify(query)
							},
							success: function(data) {
								reponse($.map(data, function(value, key) {
					            	 return {name:value, id:key};
					            }));
							},
						});
					},
					selectionPosition: 'bottom',
				});
			});
			$frameworks.each(function() {
				$this = $(this);
				$this.magicSuggest({
					data: function(query, reponse) {
						$.ajax({
							url: 'frameworks_test',
							data: {
								data: JSON.stringify(query)
							},
							success: function(data) {
								reponse($.map(data, function(value, key) {
					            	 return {name:value, id:key};
					            }));
							},
						});
					},
					selectionPosition: 'right',
				});
			});
			$languages.each(function() {
				$this = $(this);
				$this.magicSuggest({
					data: function(query, reponse) {
						$.ajax({
							url: 'languages_test',
							data: {
								data: JSON.stringify(query)
							},
							success: function(data) {
								reponse($.map(data, function(value, key) {
					            	 return {name:value, id:key};
					            }));
							},
						});
					},
					selectionPosition: 'right',
				});
			});
			$diploma_ui.each(function() {
				var $this = $(this);
				$this.autocomplete({
					source: function (request, response) {
				         $.ajax({
				             url: "diplomas_test",
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
		            	 $diploma.val($ui.item.id);
		             },
				});
			});
			$seniority.each(function() {
				$this = $(this);
				$seniorityDisplay = $('.SeniorityUi', $this.parent());
				$seniorityDisplay.slider({
					range: "min",
					value: 0,
					min: 0,
					max: 40,
					slide: function( event, ui ) {
						$this.val(ui.value);
					}
				});
			});
			$.ajax({
				url: 'get_profile_test',
				data: {
					data: application.user.userId
				},
				beforeSend: function() {
					application.disableForm($form, true);
	            	$notifier.addClass('Loading');
	            },
				complete: function() {
					$notifier.removeClass('Loading');
					application.disableForm($form, false);
				},
				success: function(data) {
					$programmingLanguages.magicSuggest().addToSelection(data.programmingLanguages);
					$frameworks.magicSuggest().addToSelection(data.frameworks);
					$languages.magicSuggest().addToSelection(data.languages);
					$diploma_ui.val(data.diploma.name);
					$diploma.val(data.diploma.id);
					$seniority.val(data.seniority);
					$seniority_ui.slider('value', data.seniority);
				}
			});
			$form.submit(function(e) {
				var data = $form.serializeObject();
				delete data['diploma_ui'];
				data.token = application.user.sessionId;
				$.ajax({
					url: $form.attr('action'),
					data: {
						data: JSON.stringify(data)
					},
					success: function(token) {

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
		                o[this.name] = eval([o[this.name]]);
		            }
		            o[this.name].push(eval(this.value) || '');
		        } else {
		        	try {
		        		o[this.name] = eval(this.value);
		        	} catch (e) {
		        			o[this.name] = this.value || '';
		        	}
		        }
		    });
		    return o;
		};
	});
})(jQuery);
