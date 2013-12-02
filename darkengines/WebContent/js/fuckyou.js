(function($) {
	$(document).ready(function() {
		addChar = function($element, c) {
			$element.text($element.text()+c);
		};
		addString = function($element, s, t) {
			if (!t) t=250;
			if (s.length > 0) {
				addChar($element, s[0]);
				window.setTimeout(addString($element, s.substring(1), t), t);
			}
		};
		var $body = $('body');
		main = function() {
			addString($body, "fuck you ");
			window.setTimeout(main, 1);
		};
		main();
	});
})(jQuery);