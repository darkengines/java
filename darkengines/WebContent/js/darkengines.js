(function($) {
	$(document).ready(function() {
		$.fn.form.defaults.success = function() {
			application.$notifier.notify('Modifications sauvegard&#233;es');
		};
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
			application.$notifier.html(msg);
			$(application.$notifier).addClass('Visible');
			setTimeout(function() {
				$(application.$notifier).removeClass('Visible');
		    }, 3000 );
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
			});
		});
		
		$('form.UpdateProfile').each(function() {
			$(this).form({
				discar: [''],
				load: {
					programmingLanguageIds: function($field, data) {
						$field.each(function() {
							var $this = $(this);
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
						$diplomaDisplay.text(formatDiploma(data.diploma));
						$diplomaEditor.slider({
							range: "min",
							value: data.diploma,
							min: 0,
							max: 10,
							slide: function(event, ui) {
								$field.val(ui.value);
								$diplomaDisplay.text(formatDiploma(ui.value));
							}
						});
						$field.val(data.diploma);
					},
					seniority: function($field, data) {
						var $seniorityEditor = $('.SeniorityEditor', $field.parent());
						var $seniorityDisplay = $('.SeniorityDisplay', $field.parent());
						$seniorityDisplay.text(formatSeniority(data.seniority));
						$seniorityEditor.slider({
							range: "min",
							value: data.seniority,
							min: 0,
							max: 10,
							slide: function(event, ui) {
								$field.val(ui.value);
								$seniorityDisplay.text(formatSeniority(ui.value));
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
				}
			});
		});
		callTypes = {
				PermanentContract: 'CDI',
				FixedTermContract: 'CDD',
				Freelance: 'Freelance'
		};
		$('.UpdateCall').each(function() {
			var $this = $(this);
			$.ajax({
				url: $this.attr('data-load-url'),
				cache: false,
				success: function(data) {
					if (data == null) data = {};
					var selected = {key: data.type, value: callTypes[data.type]};
					$('#callType').suggest({
						selectionDataSource: data == null ? {} : selected,
						suggestionDataSource: callTypes,
						max: 1,
						change: function(dataSource) {
							var $forms = $('form', $this); 
							$forms.each(function() {
								var $form = $(this);
								if ($form.is('.'+dataSource.key)) {
									$form.show();
								} else {
									$form.hide();
								}	
							});
						},
						load: function() {
							delete data['type'];
							var $forms = $('form', $this); 
							$forms.each(function() {
								var $form = $(this);
								$form.form({
									loadMethod: data,
									load: {
										programmingLanguageIds: function($field, data) {
											$field.each(function() {
												var $this = $(this);
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
											$diplomaDisplay.text(formatDiploma(data.diploma));
											$diplomaEditor.slider({
												range: "min",
												value: data.diploma,
												min: 0,
												max: 10,
												slide: function(event, ui) {
													$field.val(ui.value);
													$diplomaDisplay.text(formatDiploma(ui.value));
												}
											});
											$field.val(data.diploma);
										},
										seniority: function($field, data) {
											var $seniorityEditor = $('.SeniorityEditor', $field.parent());
											var $seniorityDisplay = $('.SeniorityDisplay', $field.parent());
											$seniorityDisplay.text(formatSeniority(data.seniority));
											$seniorityEditor.slider({
												range: "min",
												value: data.seniority,
												min: 0,
												max: 10,
												slide: function(event, ui) {
													$field.val(ui.value);
													$seniorityDisplay.text(formatSeniority(ui.value));
												}
											});
											$field.val(data.seniority);
										}
									}
								});
								if ($('#callType').val() != '') {
									if ($form.is('.'+$('#callType').val())) {
										$form.show();
									} else {
										$form.hide();
									}
								}
							});
						}
					});
				}
			});
		});
		$('.GetCalls').each(function() {
			var $this = $(this);
			var $calls = $('.Calls', $this);
			var param = {userId: application.user.userId};
			$.ajax({
				url: 'get_calls_test',
				cache: false,
				data: {
					data: JSON.stringify(param)
				},
				success: function(data) {
					$.each(data, function(key, value) {
						
						var $call = $('<div class="Call"></div>');
						var $title = $('<a href="update_call?callId='+value.id+'" class="Title">'+value.title+'</a>');
						var $type = $('<div class="Type">'+callTypes[value.type]+'</div>');
						var $controls = $('<div class="Controls"></div>');
						var param = JSON.stringify({callId:value.id, token:application.user.token});
						var $delete = $('<a class="Delete Button" href="delete_call_test">Supprimer</a>');
						$controls.append($delete);
						$delete.click(function(e) {
							var $this = $(this);
							if (window.confirm('Supprimer la proposition '+value.title+' ?')) {
								$.ajax({
									url: $this.attr('href'),
									data: {
										data: param
									},
									success: function() {
										$call.remove();
									}
								});
							}
							e.preventDefault();
							return false;
						});
						$call.append($title).append($type).append($controls);
						$calls.append($call);
					});
				}
			});
		});
		$('form.SearchDev').each(function() {
			var $form = $(this);
			var $resultContainer = $('.SearchResult');
			var $result = $('.SearchResult .Collection');
			var $diplomaEditor = $('.DiplomaUi', $form);
			var $diplomaDisplay = $('.DiplomaDisplay', $form);
			var $diploma = $('input[name=diploma]', $form);
			var $seniorityEditor = $('.SeniorityUi', $form);
			var $seniorityDisplay = $('.SeniorityDisplay', $form);
			var $seniority = $('input[name=seniority]', $form);
			$form.form({
				discar: [''],
				load: {
					programmingLanguageIds: function($field, data) {
						$field.each(function() {
							var $this = $(this);
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
						$diplomaDisplay.text(formatDiploma(data.diploma));
						$diplomaEditor.slider({
							range: "min",
							value: data.diploma,
							min: 0,
							max: 10,
							slide: function(event, ui) {
								$field.val(ui.value);
								$diplomaDisplay.text(formatDiploma(ui.value));
							}
						});
						$field.val(data.diploma);
					},
					seniority: function($field, data) {
						var $seniorityEditor = $('.SeniorityEditor', $field.parent());
						var $seniorityDisplay = $('.SeniorityDisplay', $field.parent());
						$seniorityDisplay.text(formatSeniority(data.seniority));
						$seniorityEditor.slider({
							range: "min",
							value: data.seniority,
							min: 0,
							max: 10,
							slide: function(event, ui) {
								$field.val(ui.value);
								$seniorityDisplay.text(formatSeniority(ui.value));
							}
						});
						$field.val(data.seniority);
					}
				},
				transformers: {
					
				},
				success: function($form, data) {
					if (data != null) {
						$resultContainer.show();
						$result.empty();
						if (data.length > 0) {
							$.each(data, function(index, profile) {
								var $container = $('<a href="get_profile?id='+profile.offerrerId+'" style="background-image: url(get_image?id='+profile.photoId+'); background-position: 50% 50%; background-repeat: none;" class="ProfileSummary"></a>');
								$result.append($container); 
							});
						} else {
							$result.text('Aucun résultat');
						}
					}
				}
			});
		});
		$('form.SearchCall').each(function() {
			var $form = $(this);
			var $resultContainer = $('.SearchResult');
			var $result = $('.SearchResult .Collection');
			var $diplomaEditor = $('.DiplomaUi', $form);
			var $diplomaDisplay = $('.DiplomaDisplay', $form);
			var $diploma = $('input[name=diploma]', $form);
			var $seniorityEditor = $('.SeniorityUi', $form);
			var $seniorityDisplay = $('.SeniorityDisplay', $form);
			var $seniority = $('input[name=seniority]', $form);
			$form.form({
				discar: [''],
				load: {
					programmingLanguageIds: function($field, data) {
						$field.each(function() {
							var $this = $(this);
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
						$diplomaDisplay.text(formatDiploma(data.diploma));
						$diplomaEditor.slider({
							range: "min",
							value: data.diploma,
							min: 0,
							max: 10,
							slide: function(event, ui) {
								$field.val(ui.value);
								$diplomaDisplay.text(formatDiploma(ui.value));
							}
						});
						$field.val(data.diploma);
					},
					seniority: function($field, data) {
						var $seniorityEditor = $('.SeniorityEditor', $field.parent());
						var $seniorityDisplay = $('.SeniorityDisplay', $field.parent());
						$seniorityDisplay.text(formatSeniority(data.seniority));
						$seniorityEditor.slider({
							range: "min",
							value: data.seniority,
							min: 0,
							max: 10,
							slide: function(event, ui) {
								$field.val(ui.value);
								$seniorityDisplay.text(formatSeniority(ui.value));
							}
						});
						$field.val(data.seniority);
					}
				},
				transformers: {
					
				},
				success: function($form, data) {
					if (data != null) {
						$resultContainer.show();
						$result.empty();
						if (data.length > 0) {
							$.each(data, function(index, call) {
								var $container = $('<div class="CallSummary"></div>');
								var $header = $('<div class="Header"></div>');
								var $title = $('<div class="Title"><a href="read_call?id='+call.callId+'">'+call.title+'</a></div>');
								var $description = $('<div class="Description">'+call.description+'</div>');
								var $infos = $('<div class="Infos"></div>');
								var $date = $('<div class="CreatedOn">'+new Date(call.createdOn*1).toString('le dd/MM/yyyy &#224; HH:mm')+'</div>');
								var $type = $('<div class="Type">'+callTypes[call.type]+'</div>');
								var $money = $('<div class="Money">'+formatSalary(call.remuneration)+'</div>');
								$infos.append().append($type).append($date).append($money);
								$header.append($title).append($infos);
								$container.append($header).append($description);
								$result.append($container); 
							});
						} else {
							$result.text('Aucun résultat');
						}
					}
				}
			});
		});
//		var $programmingLanguages = $('input[name=programmingLanguageIds]', $form);
//		var $frameworks = $('input[name=frameworkIds]', $form);
//		var $languages = $('input[name=languageIds]', $form);
//		var $notifier = $('.Notification', $form);
//		var $diplomaEditor = $('.DiplomaUi', $form);
//		var $diplomaDisplay = $('.DiplomaDisplay', $form);
//		var $diploma = $('input[name=diploma]', $form);
//		var $seniorityEditor = $('.SeniorityUi', $form);
//		var $seniorityDisplay = $('.SeniorityDisplay', $form);
//		var $seniority = $('input[name=seniority]', $form);
//		var $resultContainer = $('.SearchResult', $form);
//		var $result = $('.SearchResult .Collection', $form);
//		$programmingLanguages.each(function() {
//			$this = $(this);
//			$this.magicSuggest({
//				data: function(query, reponse) {
//					$.ajax({
//						url: 'programming_languages_test',
//						data: {
//							data: JSON.stringify(query)
//						},
//						success: function(data) {
//							reponse($.map(data, function(value, key) {
//				            	 return {name:value, id:key};
//				            }));
//						},
//					});
//				},
//				selectionPosition: 'bottom',
//			});
//		});
//		$frameworks.each(function() {
//			$this = $(this);
//			$this.magicSuggest({
//				data: function(query, reponse) {
//					$.ajax({
//						url: 'frameworks_test',
//						data: {
//							data: JSON.stringify(query)
//						},
//						success: function(data) {
//							reponse($.map(data, function(value, key) {
//				            	 return {name:value, id:key};
//				            }));
//						},
//					});
//				},
//				selectionPosition: 'right',
//			});
//		});
//		$languages.each(function() {
//			$this = $(this);
//			$this.magicSuggest({
//				data: function(query, reponse) {
//					$.ajax({
//						url: 'languages_test',
//						data: {
//							data: JSON.stringify(query)
//						},
//						success: function(data) {
//							reponse($.map(data, function(value, key) {
//				            	 return {name:value, id:key};
//				            }));
//						},
//					});
//				},
//				selectionPosition: 'right',
//			});
//		});
//		$diploma.each(function() {
//			var $this = $(this);
//			$diplomaEditor.slider({
//				range: "min",
//				value: 0,
//				min: 0,
//				max: 8,
//				slide: function( event, ui ) {
//					$this.val(ui.value);
//					$diplomaDisplay.text('BAC+'+ui.value);
//				}
//			});
//		});
//		$seniority.each(function() {
//			var $this = $(this);
//			$seniorityEditor.slider({
//				range: "min",
//				value: 0,
//				min: 0,
//				max: 10,
//				slide: function( event, ui ) {
//					$this.val(ui.value);
//					$seniorityDisplay.text(ui.value+(ui.value > 1 ? ' ans' : ' an'));
//				}
//			});
//		});
//		$.ajax({
//			url: 'get_profile_test',
//			cache: false,
//			data: {
//				data: application.user.userId
//			},
//			beforeSend: function() {
//				application.disableForm($form, true);
//            	$notifier.addClass('Loading');
//            },
//			complete: function() {
//				$notifier.removeClass('Loading');
//				application.disableForm($form, false);
//			},
//			success: function(data) {
//				if (data != null) {
//					$programmingLanguages.magicSuggest().addToSelection(data.programmingLanguages);
//					$frameworks.magicSuggest().addToSelection(data.frameworks);
//					$languages.magicSuggest().addToSelection(data.languages);
//					if (data.diploma != null) {
//						$diploma_ui.val(data.diploma.name);
//						$diploma.val(data.diploma.id);
//					}
//					$seniority.val(data.seniority);
//					$seniority_ui.slider('value', data.seniority);
//				}
//			}
//		});
//		$form.submit(function(e) {
//			var data = $form.serializeObject();
//			delete data['diploma_ui'];
//			if (application.user != null) {
//				data.token = application.user.token;
//			}
//			$.ajax({
//				url: $form.attr('action'),
//				data: {
//					data: JSON.stringify(data)
//				},
//				success: function(data) {
//					
//				},
//				error: function() {
//					
//				},
//				beforeSend: function() {
//					application.disableForm($form, true);
//	            	$notifier.addClass('Loading');
//	            },
//				complete: function() {
//					$notifier.removeClass('Loading');
//					application.disableForm($form, false);
//				}
//			});
//			e.preventDefault();
		var formatDiploma = function(x) {
			if (x == 0) return 'Indifférent';
			if (x == 1) return 'Baccalauréat';
			return 'BAC+'+x;
		};
		var formatSeniority = function(x) {
			if (x == 0) return 'Indifférent';
			return x+plurial('an', 'ans', x);
		};
		var plurial = function(sing, plu, x) {
			return x <= 1 ? sing : plu;
		};
		var formatSalary = function(x) {
			if (x == 0) return 'Non renseigné / Selon expérience';
			return x+'€';
		};
		$('div.Profile').each(function() {
			var $container = $(this);
			var $photo = $('.Photo');
			var $email = $('.Email');
			var $programmingLanguages = $('.ProgrammingLanguages', $container);
			var $frameworks = $('.Frameworks', $container);
			var $languages = $('.Languages', $container);
			var $diploma = $('.Diploma', $container);
			var $seniority = $('.Seniority', $container);
			var $name = $('.Name');
			var $sub = $('.Sub');
			var $phone = $('.Phone');
			
			$.ajax({
				url: 'read_offer_test',
				cache: false,
				data: {
					data: JSON.stringify({
						offerrerId: $.url().param('id'),
						token: application.user != null ? application.user.token : null
					})
				},
				success: function(data) {
					$email.text(data.email);
					$.each(data.programmingLanguages, function(index, item) {
						$programmingLanguages.append(
							$('<div class="Item">'+item+'</div>')
						);
					});
					$.each(data.frameworks, function(index, item) {
						$frameworks.append(
							$('<div class="Item">'+item+'</div>')
						);
					});
					$.each(data.languages, function(index, item) {
						$languages.append(
							$('<div class="Item">'+item+'</div>')
						);
					});
					$diploma.text(formatDiploma(data.diploma));
					$seniority.text(formatSeniority(data.seniority));
					$photo.attr('src', 'get_image?id='+data.photoId);
					$phone.text(data.phone);
				}
			});
		});
		$('div.ReadCall').each(function() {
			var $container = $(this);
			var $description = $('.Description', $container);
			var $type = $('.Type', $container);
			var $length = $('.Length', $container);
			var $remuneration = $('.Remuneration', $container);
			var $email = $('.Email');
			var $programmingLanguages = $('.ProgrammingLanguages', $container);
			var $frameworks = $('.Frameworks', $container);
			var $languages = $('.Languages', $container);
			var $diploma = $('.Diploma', $container);
			var $seniority = $('.Seniority', $container);
			var $title = $('.Title', $container);
			$.ajax({
				url: 'read_call_test',
				cache: false,
				data: {
					data: JSON.stringify({
						callId: $.url().param('id'),
						token: application.user != null ? application.user.token : null
					})
				},
				success: function(data) {
					$email.text(data.email);
					$.each(data.programmingLanguages, function(index, item) {
						$programmingLanguages.append(
							$('<div class="Item">'+item+'</div>')
						);
					});
					$.each(data.frameworks, function(index, item) {
						$frameworks.append(
							$('<div class="Item">'+item+'</div>')
						);
					});
					$.each(data.languages, function(index, item) {
						$languages.append(
							$('<div class="Item">'+item+'</div>')
						);
					});
					$title.text(data.title);
					$diploma.text(formatDiploma(data.diploma));
					$seniority.text(formatSeniority(data.seniority));
					$description.text(data.description);
					$type.text(callTypes[data.type]);
					$length.text(data.length);
					$remuneration.text(formatSalary(data.remuneration));
				}
			});
		});
	});
})(jQuery);
