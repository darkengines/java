(function($) {
	$(document).ready(function() {
		if (typeof(application)=='undefined' || application == null) {
			application = {};
		}
		$.extend(application, { 
			validators: {
				emailValidator: function(field, fields) {
					var match = field && ((''+field).match('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$') != null);
			    	return {isValid:match, text:match ? 'Ok':'Error'};
			    },
			    emailExists: function(field, fields, $field) {
			    	var exists = null;
			    	$.ajax({
			    		url: 'email_exists_test',
			    		data: {
			    			data: field
			    		},
			    		asynch: false,
			    		success: function(data) {
			    			exists = data;
			    		},
			    		beforeSend: function() {
			    			$field.text('');
			    			$field.addClass('Loading');
			    		},
			    		complete: function() {
			    			$field.removeClass('Loading');
			    		}
			    	});
			    	return {isValid:!exists, text:exists ? 'Error':'Ok'};
			    },
			    passwordValidator: function(field, fields) {
			    	var match = field && (''+field).match('^.{5,}$') != null;
			    	return {isValid:match, text:match ? 'Ok':'Error'};
			    },
			    passwordConfirmationValidator: function(field, fields) {
			    	var match = (field == fields['password']);
			    	return {isValid:match, text:match ? 'Ok':'Error'};
			    }
			}
		});
	});
})(jQuery);