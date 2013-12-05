(function($) {
	$.fn.form = function(options) {
		
		options = $.extend({}, $.fn.form.defaults, options);
		
		var $form = this;
		var $fields = $('input, select, textarea', this);
		$fields.filter('input[type=text]').keyup(function() {
			var $this = $(this);
			var fieldName = $this.attr('name');
			var fields = formToJson();
			var result = validate(fieldName, fields);
			displayValidator(fieldName, result);
		});
		var displayValidator = function(fieldName, result) {
			var $field = $fields.filter('[name='+fieldName+']');
			var $result = $('.Validator', $field.parent());
			$result.text(result.text);
			if (result.isValid) {
				$field.removeClass('Invalid').addClass('Valid');
			} else {
				$field.removeClass('Valid').addClass('Invalid');
			}
		};
		var $buttons = $('button[type=submit]', this);
		$buttons.each(function() {
			var $this = $(this);
			$this.click(function() {
				var $this = $(this);
				$this.attr('clicked', 'true');
			});
		});
		var formToJson = function() {
			var json = {};
			$fields.each(function() {
				var $field = $(this);
				var name = $field.attr('name');
				if (name != null && name.length > 0) {
					var value = $field.val();
					json[name] = value;
				}
			});
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
			return json;
		};
		var validate = function(fieldName, fields) {
			var result = null;
			if ((options.validators[fieldName]) != 'undefined') {
				var validators = options.validators[fieldName]; 
				$.each(validators, function(key, validator) {
					result = validator(fields[fieldName], fields);
					return result.isValid;
				});
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
		$form.submit(function(e) {
			var url = $form.attr('action');
			var method = $form.attr('method');
			var json = formToJson();
			$.ajax({
				url: url,
				method: method,
				data: {
					data: JSON.stringify(json)
				},
				beforeSend: function(xhr, settings) {
					if (options.beforeSend != null) {
						if (options.beforeSend($form, settings.data, xhr)) {
							option.sending($form);
							return true;
						} else {
							return false;
						}
					}
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
			e.preventDefault();
			return false;
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
		validators: {}
	};
})(jQuery);