window.com_vaadin_jsclipboard_JSClipboard = function() {
	var cb = this;
	
	var clickHandler = function(event) {
		var copyTextArea = document.createElement('textarea');
		copyTextArea.value = cb.getState().text;

		document.body.appendChild(copyTextArea);
		copyTextArea.select();

		try {
			var successful = document.execCommand('copy');
			var msg = successful ? 'successful' : 'unsuccessful';
			console.log('Copying text command was ' + msg);
		} catch (err) {
			console.log('Oops, unable to copy');
		}
		document.body.removeChild(copyTextArea);
	};

	this.attachClickListener = function(selector) {
		var copyTextareaBtn = document.querySelector(selector);
		copyTextareaBtn.addEventListener('click', clickHandler);
	};
};

