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
	});
})(jQuery);
