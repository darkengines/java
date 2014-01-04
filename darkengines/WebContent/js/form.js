(function($) {
	(function( $ ) {
    $.fn.pop = function() {
        var top = this.get(-1);
        this.splice(this.length-1,1);
        return top;
    };

    $.fn.shift = function() {
        var bottom = this.get(0);
        this.splice(0,1);
        return bottom;
    };
	})( jQuery );
	$.fn.form = function(options) {
		
		options = $.extend({}, $.fn.form.defaults, options);
		var allValid = false;
		var $form = this;
		var getFields = function() {
			return $('input, select, textarea, hidden, file', $form);
		};
		var isSet = function(x) {
			return typeof(x) != 'undefined';
		};
		getFields().filter('input[type=text], input[type=password]').keyup(function() {
			var $this = $(this);
			var fieldName = $this.attr('name');
			if (isSet(options.validators[fieldName])) {
				formToJson(function(fields) {
					var result = validate(fieldName, fields);
					checkForm(fields);
					displayValidator(fieldName, result);
				});
			}
		});
		var displayValidator = function(fieldName, result) {
			var $field = getFields().filter('[name='+fieldName+']');
			var $result = $('.Validator', $field.parent());
			$result.text(result.text);
			if (result.isValid) {
				$result.removeClass('Invalid').addClass('Valid');
			} else {
				$result.removeClass('Valid').addClass('Invalid');
			}
		};
		var $buttons = $('button[type=submit]', this);
		$buttons.each(function() {
			var $this = $(this);
			$this.click(function() {
				var $this = $(this);
				$buttons.removeAttr('clicked');
				$this.attr('clicked', 'true');
			});
		});
		var rec = function($fields, json, callback) {
			if ($fields.length > 0) {
				var $field = $($fields.pop());
				var name = $field.attr('name');
				if (options.transformers.hasOwnProperty(name)) {
						options.transformers[name]($field, function(value) {
							json[name] = value;
							rec($fields, json, callback);
						});
				} else {
					var value = $field.val();
					try {
						json[name] = eval(value);
					} catch(exception) {
						json[name] = value;
					}
					rec($fields, json, callback);
				}
			} else {
				callback(json);
			}
		};
		var formToJson = function(callback) {
			var json = {};
			var $clickedButton = $('button[clicked=true]', $form);
			if ($clickedButton.length > 0) {
				$clickedButton.each(function() {
					var $this = $(this);
					var name = $this.attr('name');
					if (name != null && name.length > 0) {
						var value = $this.attr('value');
						if (value != null && value.length > 0) {
							json[name] = value;
						}
					}
				});
			}
			var $fields = getFields();
			rec($fields, json, function(transformed) {
				callback(transformed);
			});
		};
		var validate = function(fieldName, fields) {
			var result = null;
			if ((options.validators[fieldName]) != 'undefined') {
				var validators = options.validators[fieldName];
				if ((validators) != 'undefined' && validators != null) {
					$.each(validators, function(key, validator) {
						var $field = getFields().filter('[name='+fieldName+']');
						var $validator = $('.Validator', $field.parent());
						result = validator(fields[fieldName], fields, $validator);
						return result.isValid;
					});
				}
				return result;
			}
			return true;
		};
		var validateAll = function(fields) {
			var result = {};
			$.each(options.validators, function(key, validator) {
				result[key] = validate(key, fields);
			});
			return result;
		};
		var checkForm = function(fields) {
			allValid = true;
			var results = validateAll(fields);
			$.each(results, function(key, result) {
				allValid &= result.isValid;
			});
			$submitters = $buttons.add(getFields().filter('[type=submit]'));
			if (allValid) {
				$submitters.removeAttr('disabled');
			} else {
				$submitters.attr('disabled', 'true');
			}
		};
		$form.submit(function(e) {
			var url = $form.attr('action');
			var method = $form.attr('method');
			formToJson(function(json) {
				$.each(options.discar, function(key, value) {
					delete json[value];
				});
				$.ajax({
					url: url,
					method: method,
					data: {
						data: JSON.stringify(json)
					},
					beforeSend: function(xhr, settings) {
						if (options.beforeSubmit != null) {
							if (options.beforeSend($form, settings.data, xhr)) {
								options.sending($form);
								return true;
							} else {
								return false;
							}
						}
						options.sending($form);
						return true;
					},
					success: function(data) {
						if (options.success != null) {
							options.success($form, data);
						}
					},
					complete: function() {
						if (options.complete != null) {
							options.complete($form);
						}
					}
				});
			});
			e.preventDefault();
			return false;
		});
		var load = function() {
			switch (typeof(options.loadMethod)) {
				case 'string': {
					var url = $form.attr(options.loadMethod);
					if (typeof(url) != 'undefined' && url != null) {
						$.ajax({
							url: url,
							method: 'get',
							beforeSend: function() {
								options.sending($form);
								return true;
							},
							complete: function() {
								options.complete($form);
							},
							success: function(data) {
								dataBind(data);
							}
						});
					}
					break;
				}
				case 'object': {
					dataBind(options.loadMethod);
					break;
				}
			}
		};
		var dataBind = function(data) {
			$.each(getFields(), function(key, $field) {
				$field = $($field);
				var name = $field.attr('name');
				if (options.load.hasOwnProperty(name)) {
					options.load[name]($field, data);
				} else {
					if (data != null && data.hasOwnProperty(name)) {
						$field.val(data[name]);
					}
				}
			});
		};
		load();
		formToJson(function(json) {
			checkForm(json);
		});
	};
	$.fn.form.defaults = {
		beforeSubmit: null,
		afterSubmit: null,
		sending: function($form) {
			$form.addClass('Loading');
		},
		complete: function($form) {
			$form.removeClass('Loading');
		},
		success: function($form, data) {
			
		},
		validators: {},
		load: {},
		discar: {},
		transformers: {},
		dataSource: {},
		loadMethod: 'data-load-url',
	};
})(jQuery);