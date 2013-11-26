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
		application.$notifier = $('.MainNotifier');
		application.$notifier.notify = function(msg) {
			application.$notifier.text(msg);
			$(application.$notifier).show( 'slide', {}, 500, function() {
				setTimeout(function() {
					$(application.$notifier).removeAttr( "style" ).fadeOut();
			    }, 1000 );
			});
		};
		$.datepicker.setDefaults( $.datepicker.regional[ "fr" ] );
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
							window.location.href = url;
						} else {
							window.location.href = 'edit_dev_profile';
						}
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
				cache: false,
				data: {
					data: application.user.userId
				},
				success: function(data) {
					if (data != null) {
						$lastName.val(data.lastName);
						$firstName.val(data.firstName);
						$address.val(data.address);
						if (data.city != null) {
							$city.val(data.city.id);
							$city_ui.val(data.city.name);
						}
						if (data.birthDate != null) {
							$birthDate_ui.datepicker('setDate', (new Date(data.birthDate)));
							$birthDate.val(data.birthDate);
						}
					}
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
				data.token = application.user.sessionToken;
				$.ajax({
					url: $form.attr('action'),
					data: {
						data: JSON.stringify(data)
					},
					success: function(token) {
						application.$notifier.notify('Identité sauvegardée');
					},
					error: function() {
						
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
				e.preventDefault();
			});
		});
		$('form.UpdateProfile').each(function() {
			var $form = $(this);
			var $programmingLanguages = $('input[name=programmingLanguageIds]');
			var $frameworks = $('input[name=frameworkIds]');
			var $languages = $('input[name=languageIds]');
			var $notifier = $('.Notification');
			var $diplomaEditor = $('.DiplomaUi');
			var $diplomaDisplay = $('.DiplomaDisplay');
			var $diploma = $('input[name=diploma]');
			var $seniorityEditor = $('.SeniorityUi');
			var $seniorityDisplay = $('.SeniorityDisplay');
			var $seniority = $('input[name=seniority]');
			var $photo = $('input[name=photo]');
			var $photoDisplay = $('.Photo');
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
				var $this = $(this);
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
				var $this = $(this);
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
			$diploma.each(function() {
				var $this = $(this);
				$diplomaEditor.slider({
					range: "min",
					value: 0,
					min: 0,
					max: 8,
					slide: function( event, ui ) {
						$this.val(ui.value);
						$diplomaDisplay.text('BAC+'+ui.value);
					}
				});
			});
			$seniority.each(function() {
				var $this = $(this);
				$seniorityEditor.slider({
					range: "min",
					value: 0,
					min: 0,
					max: 10,
					slide: function( event, ui ) {
						$this.val(ui.value);
						$seniorityDisplay.text(ui.value+(ui.value > 1 ? ' ans' : ' an'));
					}
				});
			});
			$.ajax({
				url: 'get_profile_test',
				cache: false,
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
					if (data != null) {
						$programmingLanguages.magicSuggest().addToSelection(data.programmingLanguages);
						$frameworks.magicSuggest().addToSelection(data.frameworks);
						$languages.magicSuggest().addToSelection(data.languages);
						$diploma.val(data.diploma);
						$diplomaEditor.slider('value', data.diploma);
						$diplomaDisplay.text('BAC+'+data.diploma);
						if (data.photo != null && data.photo.length > 0) {
							$photoDisplay.attr('src', data.photo);
						}
						$seniority.val(data.seniority);
						$seniorityEditor.slider('value', data.seniority);
						$seniorityDisplay.text(data.seniority+(data.seniority>0?' ans':' an'));
					}
				}
			});
			$photo.change(function() {
				var $this = $(this);
				var reader= new FileReader();
				reader.onerror = function(e) {
					alert(e);
				};
				reader.onload = function(e) {
					$photoDisplay.attr('src', e.target.result);
		        };
		        reader.readAsDataURL($this.get(0).files[0]);
			});
			$form.submit(function(e) {
				var data = $form.serializeObject();
				delete data['diploma_ui'];
				
		        var photo = $photo.get(0).files[0];
		        if (photo != null && typeof(photo)!='undefined' && photo) {
		        	var reader= new FileReader();
					reader.onerror = function(e) {
						alert(e);
					};
					reader.onload = function(e) {
			             data.photo = e.target.result;
			             data.token = application.user.sessionToken;
							$.ajax({
								url: $form.attr('action'),
								method: 'POST',
								data: {
									data: JSON.stringify(data)
								},
								success: function(token) {
									application.$notifier.notify('Profil sauvegardé');
								},
								error: function() {
									
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
			        };
			        reader.readAsDataURL($photo.get(0).files[0]);
		        } else {
		        	data.photo = null;
		             data.token = application.user.sessionToken;
						$.ajax({
							url: $form.attr('action'),
							method: 'POST',
							data: {
								data: JSON.stringify(data)
							},
							success: function(token) {
								application.$notifier.notify('Profil sauvegardé');
							},
							error: function() {
								
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
		        }
				e.preventDefault();
			});
		});
		$('form.SearchDev').each(function() {
			var $form = $(this);
			var $programmingLanguages = $('input[name=programmingLanguageIds]');
			var $frameworks = $('input[name=frameworkIds]');
			var $languages = $('input[name=languageIds]');
			var $notifier = $('.Notification');
			var $diplomaEditor = $('.DiplomaUi');
			var $diplomaDisplay = $('.DiplomaDisplay');
			var $diploma = $('input[name=diploma]');
			var $seniorityEditor = $('.SeniorityUi');
			var $seniorityDisplay = $('.SeniorityDisplay');
			var $seniority = $('input[name=seniority]');
			var $resultContainer = $('.SearchResult');
			var $result = $('.SearchResult .Collection');
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
			$diploma.each(function() {
				var $this = $(this);
				$diplomaEditor.slider({
					range: "min",
					value: 0,
					min: 0,
					max: 8,
					slide: function( event, ui ) {
						$this.val(ui.value);
						$diplomaDisplay.text('BAC+'+ui.value);
					}
				});
			});
			$seniority.each(function() {
				var $this = $(this);
				$seniorityEditor.slider({
					range: "min",
					value: 0,
					min: 0,
					max: 10,
					slide: function( event, ui ) {
						$this.val(ui.value);
						$seniorityDisplay.text(ui.value+(ui.value > 1 ? ' ans' : ' an'));
					}
				});
			});
			/*$.ajax({
				url: 'get_profile_test',
				cache: false,
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
					if (data != null) {
						$programmingLanguages.magicSuggest().addToSelection(data.programmingLanguages);
						$frameworks.magicSuggest().addToSelection(data.frameworks);
						$languages.magicSuggest().addToSelection(data.languages);
						if (data.diploma != null) {
							$diploma_ui.val(data.diploma.name);
							$diploma.val(data.diploma.id);
						}
						$seniority.val(data.seniority);
						$seniority_ui.slider('value', data.seniority);
					}
				}
			});*/
			$form.submit(function(e) {
				var data = $form.serializeObject();
				delete data['diploma_ui'];
				if (application.user != null) {
					data.token = application.user.sessionToken;
				}
				$.ajax({
					url: $form.attr('action'),
					data: {
						data: JSON.stringify(data)
					},
					success: function(data) {
						$resultContainer.show();
						$result.empty();
						if (data.length > 0) {
							$.each(data, function(index, profile) {
								var $container = $('<a href="get_profile?id='+profile.userId+'" style="background-image: url('+profile.photo+'); background-position: 50% 50%; background-repeat: none;" class="ProfileSummary"></a>');
								$result.append($container); 
							});
						} else {
							$result.text('Aucun résultat');
						}
					},
					error: function() {
						
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
				e.preventDefault();
			});
		});
		$('div.Profile').each(function() {
			var $container = $(this);
			var $photo = $('.Photo', $container);
			var $email = $('.Email', $container);
			var $programmingLanguages = $('.ProgrammingLanguages');
			var $frameworks = $('.Frameworks');
			var $languages = $('.Languages');
			var $diploma = $('.Diploma');
			var $seniority = $('.Seniority');
			
			$.ajax({
				url: 'get_profile_test',
				cache: false,
				data: {
					data: $.url().param('id')
				},
				success: function(data) {
					$email.text(data.userEmail);
					$.each(data.programmingLanguages, function(index, item) {
						$programmingLanguages.append(
							$('<div class="ms-sel-item">'+item.name+'</div>')
						);
					});
					$.each(data.frameworks, function(index, item) {
						$frameworks.append(
							$('<div class="ms-sel-item">'+item.name+'</div>')
						);
					});
					$.each(data.languages, function(index, item) {
						$languages.append(
							$('<div class="ms-sel-item">'+item.name+'</div>')
						);
					});
					$diploma.text(data.diploma.name);
					$seniority.text(data.seniority);
					if (data.photo != null && data.photo.length > 0) {
						$photo.attr('src', data.photo);
					}
				}
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
