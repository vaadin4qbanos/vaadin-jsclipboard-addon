window.com_vaadin_jsclipboard_ClipboardButton = function() {
    var clipboardBtn = this,
            container = this.getElement(),
            state = this.getState();
    this.onStateChange = function() {
        var markup = '<button class="btn v-button ' + state.buttonClass + '" data-clipboard-target="#' + state.targetId + '">' + state.clipboardButtonCaption + '</button>';
        container.innerHTML = markup;

        var clipboard = new Clipboard("." + state.buttonClass);

        clipboard.on('success', function(e) {
            clipboardBtn.notifyStatus(true);
            e.clearSelection();
        });

        clipboard.on('error', function(e) {
            clipboardBtn.notifyStatus(false);
        });
    };



};