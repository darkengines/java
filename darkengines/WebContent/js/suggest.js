(function($) {
	$.fn.suggest = function(options) {
		var $suggest = this;
		var $parent = this.parent();
		$suggest.attr('type', 'hidden');
		options = $.extend({}, $.fn.form.defaults, options);
		var $container = $('<div class="SuggestWrapper"></div>');
		var $suggestInput = $('<input type="text" class="SuggestInput" />');
		var $suggestButton = $('<div class="SuggestButton"></div>');
		var $suggestList = $('<div tabindex="1" class="SuggestList"></div>');
		var $suggestSelected = $('<div class="SuggestSelected"></div>');
		$container = $suggest.wrap($container).parent();
		$container.append($suggestInput);
		$container.append($suggestButton);
		$container.append($suggestList);
		$parent.append($suggestSelected);
		
		$(document).click(function(e) {
			if (!$.contains($container[0], e.target)) {
				$suggestList.removeClass('Visible');
				$container.removeClass('Suggesting');
			}
		});
		
		var showSuggestList = function() {
			$suggestList.addClass('Visible');
			$container.addClass('Suggesting');
			$suggestList.scrollTop(0);
			suggestListSetFocus(-1);
			
		};
		var hideSuggestList = function() {
			$suggestList.removeClass('Visible');
			$container.removeClass('Suggesting');
		};
		$suggestInput.keyup(function(e) {
			var code = e.keyCode || e.which;
			switch (code) {
				case (38): {
					e.preventDefault();
					return false;
					break;
				}
				case (40): {
					e.preventDefault();
					return false;
					break;
				}
				case (13): {
					$($suggestList.children()[getSuggestListFocusIndex()]).click();
					e.preventDefault();
					return false;
					break;
				}
				default: {
					var data = options.databind($suggestInput.val());
					if (Object.keys(data).length > 0) {
						fillSuggestList(data);
						if (!$suggestList.is('.Visible')) {
							showSuggestList();
						}
					} else {
						hideSuggestList();
					}
				}
			}
		});
		$suggestInput.keydown(function(e) {
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
						showSuggestList();
					}
					e.preventDefault();
					return false;
					break;
				}
				case (40): {
					var $children = $suggestList.children();
					var length = $children.length;
					var index = getSuggestListFocusIndex();
					if (index < 0) {
						showSuggestList();
					}
					if (index < length-1) {
						suggestListSetFocus(index+1);
					}
					e.preventDefault();
					return false;
					break;
				}
				case (13): {
					e.preventDefault();
					return false;
					break;
				}
				default: {

				}
			}
		});
		var heightTo = function(index, $parent) {
			var i = 0;
			var result = 0;
			while (i < index) {
				result+=$($parent[i]).outerHeight();
				i++;
			}
			return result;
		};
		var suggestListSetFocus = function(index) {
			var $children = $suggestList.children();
			var length = $children.length;
			if (length > 0) {
				$children.removeClass('Focused');
				if (index >= 0 && index < length) {
						var $element = $($children[index]);
						var h = heightTo(index, $children);
						$element.addClass('Focused');
						var offset = $suggestList.height() % $element.outerHeight();
						if ($suggestList.scrollTop()+$suggestList.height() <= h+offset) {
							$suggestList.scrollTop(Math.abs($suggestList.height() - h - $element.outerHeight()));
						}
						if ($suggestList.scrollTop() >= h+offset) {
							$suggestList.scrollTop(Math.abs(h));
						}
						$suggestInput.text($element.text());
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
		
		$suggestButton.click(function(e) {
			var data = options.databind($suggestInput.val());
			if (!$suggestList.is('.Visible') && Object.keys(data).length > 0) {
				fillSuggestList(data);
				showSuggestList();
				
			} else {
				hideSuggestList();
			}
		});
		
		var fillSuggestList = function(data) {
			$suggestList.empty();
			$.each(data, function(key, value) {
				var $suggestElement = $('<div class="SuggestElement" tabindex="'+key+'">'+value+'</div>');
				$suggestElement.key = key;
				$suggestElement.click(function(e) {
					suggestSelected($suggestElement, key);
				});
				$suggestElement.mouseover(function() {
					suggestListSetFocus($suggestElement.index());
				});
				$suggestList.append($suggestElement);
			});
		};
		var suggestSelected = function($suggestElement) {
			var val = ($suggest.val() != null && $suggest.val().length > 0) ? $suggest.val() : '{}';
			var input = eval('('+val+')');
			var selected = $suggestElement.key;
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