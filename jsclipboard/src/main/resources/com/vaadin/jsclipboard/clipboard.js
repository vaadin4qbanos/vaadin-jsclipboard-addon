window.com_vaadin_jsclipboard_JSClipboard = function() {
    var cb = this;

    var clickHandler = function(event) {
        var copyTextArea = document.createElement('textarea');
        copyTextArea.focus();
        copyTextArea.value = cb.getState().text;

        document.body.appendChild(copyTextArea);
        copyTextArea.select();

        try {
            var successful = document.execCommand('copy');
            var msg = successful ? true : false;
            cb.notifyStatus(msg);
            console.log('Copying text command was ' + ((msg) ? 'successful' : 'unsuccessful'));
        } catch (err) {
            console.log('Oops, unable to copy');
            cb.notifyStatus(false);
        }
        document.body.removeChild(copyTextArea);
    };

    this.attachClickListener = function(selector) {
        var copyTextareaBtn = document.querySelector(selector);
        copyTextareaBtn.addEventListener('click', clickHandler);
    };
};

