(function($) {
	$(document).ready(function() {
		$.extend(application, {
			user: null
		});
		if (typeof($.cookie('userInfo')) != 'undefined') {
			application.user = eval('('+$.cookie('userInfo')+')');
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
		$('.Disconnect').click(function() {
			$this = $(this);
			$.removeCookie('userInfo');
			window.location='/';
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
			$form.form({
				success: function($form, data) {
					$.cookie('userInfo', JSON.stringify(data));
					var url = $.url().param('url');
					if (url == null) {
						url = '/';
					}
					window.location = url;
				}
			});
		});
		$('form.CreateAccount').each(function() {
			$(this).form({
				validators: {
					email: [application.validators.emailValidator, application.validators.emailExists],
					password: [application.validators.passwordValidator],
					password_confirmation: [application.validators.passwordConfirmationValidator]
				},
				discar: ['password_confirmation'],
				success: function($form, data) {
					$.cookie('userInfo', JSON.stringify(data));
					window.location = '/';
				}
			});
		});
		
		$('form.UpdateContact').each(function() {
			$(this).form({
				validators: {
					email: [application.validators.emailValidator, application.validators.emailExists]
				},
				success: function() {
					application.$notifier.notify('Contact sauvegardé');
				}
			});
		});
		
		$('form.UpdateProfile').each(function() {
			$(this).form({
				discar: [''],
				load: {
					programmingLanguageIds: function($field, data) {
						$field.each(function() {
							$this = $(this);
							$this.suggest({
								selectionDataSource: data.programmingLanguageIds,
								suggestionDataSource: function(query) {
									var result = {};
									$.ajax({
										url: 'programming_languages_test',
										async: false,
										data: {
											data: JSON.stringify(query)
										},
										success: function(data) {
											result = data;
										},
									});
									return result;
								}
							});
						});
					},
					frameworkIds: function($field, data) {
						$field.each(function() {
							var $this = $(this);
							$this.suggest({
								selectionDataSource: data.frameworkIds,
								suggestionDataSource: function(query) {
									var result = {};
									$.ajax({
										url: 'frameworks_test',
										async: false,
										data: {
											data: JSON.stringify(query)
										},
										success: function(data) {
											result = data;
										},
									});
									return result;
								}
							});
						});
					},
					languageIds: function($field, data) {
						$field.each(function() {
							var $this = $(this);
							$this.suggest({
								selectionDataSource: data.languageIds,
								suggestionDataSource: function(query) {
									var result = {};
									$.ajax({
										url: 'languages_test',
										async: false,
										data: {
											data: JSON.stringify(query)
										},
										success: function(data) {
											result = data;
										},
									});
									return result;
								}
							});
						});
					},
					diploma: function($field, data) {
						var $diplomaEditor = $('.DiplomaEditor', $field.parent());
						var $diplomaDisplay = $('.DiplomaDisplay', $field.parent());
						if (data.diploma != null) $diplomaDisplay.text(data.diploma+(data.diploma > 1 ? ' ans' : ' an'));
						$diplomaEditor.slider({
							range: "min",
							value: data.diploma,
							min: 0,
							max: 10,
							slide: function(event, ui) {
								$field.val(ui.value);
								$diplomaDisplay.text(ui.value+(ui.value > 1 ? ' ans' : ' an'));
							}
						});
						$field.val(data.diploma);
					},
					seniority: function($field, data) {
						var $seniorityEditor = $('.SeniorityEditor', $field.parent());
						var $seniorityDisplay = $('.SeniorityDisplay', $field.parent());
						if (data.diploma != null) $seniorityDisplay.text(data.seniority+(data.seniority > 1 ? ' ans' : ' an'));
						$seniorityEditor.slider({
							range: "min",
							value: data.seniority,
							min: 0,
							max: 10,
							slide: function(event, ui) {
								$field.val(ui.value);
								$seniorityDisplay.text(ui.value+(ui.value > 1 ? ' ans' : ' an'));
							}
						});
						$field.val(data.seniority);
					},
					photo: function($field, data) {
						var $photoDisplay = $('.PhotoDisplay', $field.parent());
						$photoDisplay.attr('src', data.photoUrl);
						$field.change(function() {
							var $this = $(this);
							var $photoDisplay = $('.PhotoDisplay', $this.parent());
							var reader= new FileReader();
							reader.onerror = function(e) {
								alert(e);
							};
							reader.onload = function(e) {
								$photoDisplay.attr('src', e.target.result);
					        };
					        reader.readAsDataURL($this.get(0).files[0]);
						});
						if (data.hasOwnProperty('photoId')) {
							$photoDisplay.attr('src', 'get_image?id='+data.photoId);
						}
					}
				},
				transformers: {
					photo: function($field, callback) {
						var photo = $field.get(0).files[0];
				        if (photo != null && typeof(photo)!='undefined' && photo) {
				        	var reader= new FileReader();
							reader.onerror = function(e) {
								alert(e);
							};
							reader.onload = function(e) {
								callback(e.target.result);
					        };
					        reader.readAsDataURL(photo);
				        } else {
				        	callback(null);
				        }
					}
				},
				success: function() {
					application.$notifier.notify('Profil sauvegardé');
				}
			});
		});
		callTypes = {
				0: 'CDI',
				1: 'CDD',
				2: 'Freealnce'
		};
		$('form.UpdateCall').each(function() {
			$(this).form({
				discar: [''],
				load: {
					callType: function($field, data) {
						$field.each(function() {
							var $this = $(this);
							$this.suggest({
								selectionDataSource: data == null ? {} : data.callType,
								suggestionDataSource: callTypes,
								max: 1
							});
						});
					},
				},
				transformers: {
					
				},
				success: function() {
					application.$notifier.notify('Profil sauvegardé');
				}
			});
		});
		$('form.SearchDev').each(function() {
			var $form = $(this);
			var $programmingLanguages = $('input[name=programmingLanguageIds]', $form);
			var $frameworks = $('input[name=frameworkIds]', $form);
			var $languages = $('input[name=languageIds]', $form);
			var $notifier = $('.Notification', $form);
			var $diplomaEditor = $('.DiplomaUi', $form);
			var $diplomaDisplay = $('.DiplomaDisplay', $form);
			var $diploma = $('input[name=diploma]', $form);
			var $seniorityEditor = $('.SeniorityUi', $form);
			var $seniorityDisplay = $('.SeniorityDisplay', $form);
			var $seniority = $('input[name=seniority]', $form);
			var $resultContainer = $('.SearchResult', $form);
			var $result = $('.SearchResult .Collection', $form);
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
					data.token = application.user.token;
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
//		$('div.Profile').each(function() {
//			var $container = $(this);
//			var $photo = $('.Photo');
//			var $email = $('.Email');
//			var $programmingLanguages = $('.ProgrammingLanguages', $container);
//			var $frameworks = $('.Frameworks', $container);
//			var $languages = $('.Languages', $container);
//			var $diploma = $('.Diploma', $container);
//			var $seniority = $('.Seniority', $container);
//			var $name = $('.Name');
//			var $sub = $('.Sub');
//			var $phone = $('.Phone');
//			
//			$.ajax({
//				url: 'get_profile_test',
//				cache: false,
//				data: {
//					data: $.url().param('id')
//				},
//				success: function(data) {
//					$email.text(data.userEmail);
//					$.each(data.programmingLanguages, function(index, item) {
//						$programmingLanguages.append(
//							$('<div class="ms-sel-item">'+item.name+'</div>')
//						);
//					});
//					$.each(data.frameworks, function(index, item) {
//						$frameworks.append(
//							$('<div class="ms-sel-item">'+item.name+'</div>')
//						);
//					});
//					$.each(data.languages, function(index, item) {
//						$languages.append(
//							$('<div class="ms-sel-item">'+item.name+'</div>')
//						);
//					});
//					$diploma.text(data.diploma);
//					$seniority.text(data.seniority);
//					if (data.photo != null && data.photo.length > 0) {
//						$photo.attr('src', data.photo);
//					}
//					$name.text(data.firstName+' '+data.lastName);
//					var birthDate = new Date(data.birthDate);
//					$sub.text(data.city+', '+birthDate.toString())
//					$phone.text(data.phone);
//					
//				}
//			});
//		});
	});
})(jQuery);
