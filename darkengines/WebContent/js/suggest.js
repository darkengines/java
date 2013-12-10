(function($) {
	$.fn.suggest = function(options) {
		var $suggest = this;
		var $parent = this.parent();
		$suggest.attr('type', 'hidden');
		options = $.extend({}, $.fn.form.defaults, options);
		var $container = $('<div class="SuggestWrapper"></div>');
		var $suggestInput = $('<input type="text" class="SuggestInput" />');
		var $suggestButton = $('<div class="SuggestButton"></div>');
		var $suggestList = $('<div tabindex="-1" class="SuggestList"></div>');
		var $suggestSelected = $('<div class="SuggestSelected"></div>');
		$container = $suggest.wrap($container).parent();
		$container.append($suggestInput);
		$container.append($suggestButton);
		$container.append($suggestList);
		$parent.append($suggestSelected);
		
		$suggestInput.blur(function() {
			$(this).removeClass('Show');
		});
		
		$suggestInput.keyup(function(e) {
			var code = e.keyCode || e.which;
			switch (code) {
				case (38): {
					var index = getSuggestListFocusIndex();
					if (index >= 1) {
						suggestListSetFocus(index-1);
					}
					var $children = $suggestList.children();
					var length = $children.length;
					if (index < 1 && length > 0) {
						$suggestList.addClass('Show');
					}
					break;
				}
				case (40): {
					var $children = $suggestList.children();
					var length = $children.length;
					var index = getSuggestListFocusIndex();
					if (index < 0) {
						$suggestList.addClass('Show');
					}
					if (index < length-1) {
						suggestListSetFocus(index+1);
					}
					break;
				}
				default: {
					$suggestButton.click();
				}
			}
		});
		
		var suggestListSetFocus = function(index) {
			var $children = $suggestList.children();
			var length = $children.length;
			if (length > 0) {
				$children.removeClass('Focused');
				if (index >= 0 && index < length) {
					$($children[index]).addClass('Focused');
				}
			}
		};
		
		var getSuggestListFocusIndex = function() {
			var index = -1;
			if ($suggestList.children().length > 0 && $suggestList.children().filter('.Focused').length > 0) {
				index = $suggestList.children().filter('.Focused').index();
			}
			return index;
		};
		
		$suggestButton.click(function() {
			var data = options.databind($suggestInput.val());
			if (!$suggestList.is(':focus') && Object.keys(data).length > 0) {
				fillSuggestList(data);
				$suggestList.focus();
			} else {
				$suggestList.removeClass('Show');
			}
		});
		
		var fillSuggestList = function(data) {
			$suggestList.empty();
			$.each(data, function(key, value) {
				var $suggestElement = $('<div class="SuggestElement">'+value+'</div>');
				$suggestElement.click(function() {
					suggestSelected($suggestElement, key);
				});
				$suggestElement.keyup(function(sender, e) {
					var code = e.keyCode || e.which;
					if (code == 13 || code == 9) {
						suggestSelected($suggestElement, key);
					}
				});
				$suggestList.append($suggestElement);
				$container.addClass('Suggesting');
			});
		};
		var suggestSelected = function($suggestElement, key) {
			var val = ($suggest.val() != null && $suggest.val().length > 0) ? $suggest.val() : '{}';
			var input = eval('('+val+')');
			var selected = key;
			var text = $suggestElement.text();
			var found = false;
			$.each(input, function(key, value) {
				found = key == selected;
				return !found;
			});
			if (!found) {
				input[selected] = text;
			}
			$suggest.val(JSON.stringify(input));
			refreshSuggestSelected(input);
		};
		var refreshSuggestSelected = function(input) {
			$suggestSelected.empty();
			$.each(input, function(key, value) {
				var $selected = $('<div class="Selected">'+value+'</div>');
				var $close = $('<div class="Close"></div>');
				$selected.append($close);
				$close.click(function() {
					suggestUnselected(key);
					$selected.remove();
				});
				$suggestSelected.append($selected);
			});
		};
		var suggestUnselected = function(key) {
			var val = ($suggest.val() != null && $suggest.val().length > 0) ? $suggest.val() : '{}';
			var input = eval('('+val+')');
			var selected = key;
			var found = false;
			$.each(input, function(key, value) {
				found = key == selected;
				return !found;
			});
			if (found) {
				delete input[key];
			}
			$suggest.val(JSON.stringify(input));
		};
	};
	$.fn.suggest.defaults = {
		databind: null,
	};
})(jQuery);