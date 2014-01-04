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
		
		this.selectionDataSource = options.selectionDataSource;
		this.suggestionDataSource = options.suggestionDataSource;
		this.rawValue = '';
		
		$suggestInput.click(function() {
			$suggestButton.click();
		});
		
		this.suggestionDataBind = function() {
			$suggestList.empty();
			switch (typeof($suggest.suggestionDataSource)) {
				case 'function': {
					data = $suggest.suggestionDataSource($suggestInput.val());
					break;
				}
				case 'object': {
					data = $suggest.suggestionDataSource;
					break;
				}
				default: {
					
				}
			}
			$.each(data, function(key, value) {
				var $suggestElement = $('<div class="SuggestElement">'+value+'</div>');
				$suggestElement.mouseup(function(e) {
					if (options.max == 1 && $suggest.selectionDataSource.key != key) {
						$suggest.selectionDataSource = {key:key, value:value};
						$container.removeClass('Suggesting');
						hideSuggestList();
						options.change($suggest.selectionDataSource);
					} else {
						if (!options.max || Object.keys($suggest.selectionDataSource).length < options.max) {
							if (!$suggest.selectionDataSource.hasOwnProperty(key)) {
								$suggest.selectionDataSource[key] = value;
								$container.removeClass('Suggesting');
								hideSuggestList();
								options.change($suggest.selectionDataSource);
							}
						} else {
							//Too much values
						}
					}
					$suggest.selectedDataBind();
				});
				$suggestElement.mousedown(function(e) {
					e.preventDefault();
					return false;
				});
				$suggestElement.mouseover(function() {
					suggestListSetFocus($suggestElement.index());
				});
				$suggestElement.mouseout(function() {
					unsuggestInput();
				});
				$suggestList.append($suggestElement);
			});
		};
		this.selectedDataBind = function() {
			if (options.max != 1) {
				$suggestSelected.empty();
				var input = new Array();
				$.each($suggest.selectionDataSource, function(key, value) {
					var $wrapper = $('<div class="SelectedWrapper"></div>');
					var $selected = $('<div class="Selected">'+value+'</div>');
					var $close = $('<div class="Close"></div>');
					$selected.append($close);
					$wrapper.append($selected);
					$close.click(function() {
						if ($suggest.selectionDataSource.hasOwnProperty(key)) {
							delete $suggest.selectionDataSource[key];
							$suggest.selectedDataBind();
							options.change($suggest.selectionDataSource);
						}
					});
					$suggestSelected.append($wrapper);
					input.push(key);
				});
				$suggest.val(JSON.stringify(input));
			} else {
				if ($suggest.suggestionDataSource == null) {
					$suggest.val('');
				} else {
					$suggestInput.val($suggest.selectionDataSource.value);
					$suggest.rawValue = $suggest.selectionDataSource.value;
					$suggest.val($suggest.selectionDataSource.key);
				}
			}
		};

		$(document).click(function(e) {
			if (!$.contains($container[0], e.target)) {
				$suggestList.removeClass('Visible');
				$container.removeClass('Suggesting');
				$suggestInput.removeClass('Suggested');
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
					$($suggestList.children()[getSuggestListFocusIndex()]).mouseup();
					e.preventDefault();
					return false;
					break;
				}
				default: {
					$suggest.suggestionDataBind();
					if ($suggestList.children().length > 0) {
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
					if (length > 0 && index < 0) {
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
						suggestInput($($children[index]).text());
				}
			}
		};
		
		var suggestInput = function(text) {
			$suggest.rawValue = $suggestInput.val();
			$suggestInput.val(text);
			$suggestInput.addClass('Suggested');
		};
		
		var unsuggestInput = function() {
			$suggestInput.val($suggest.rawValue);
			$suggestInput.removeClass('Suggested');
		};
		
		var getSuggestListFocusIndex = function() {
			var index = -1;
			if ($suggestList.children().length > 0 && $suggestList.children().filter('.Focused').length > 0) {
				index = $suggestList.children().filter('.Focused').index();
			}
			return index;
		};
		
		$suggestButton.click(function(e) {
			$suggest.suggestionDataBind();
			if (!$suggestList.is('.Visible') && $suggestList.children().length > 0) {
				showSuggestList();
				
			} else {
				hideSuggestList();
			}
		});
		this.selectedDataBind();
		options.load();
	};
	$.fn.suggest.defaults = {
		selectionDataSource: {},
		suggestionDataSource: {},
		change: function() {},
		load: function() {},
		max: 0,
	};
})(jQuery);