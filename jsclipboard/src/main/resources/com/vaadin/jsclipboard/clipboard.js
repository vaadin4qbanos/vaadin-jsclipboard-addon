window.com_vaadin_jsclipboard_JSClipboard = function() {

    var clipboardBtn = this,
        container = this.getElement(),
        state = this.getState();

    var trigger = document.getElementsByClassName(state.buttonClass)[0];
    trigger.dataset.clipboardTarget = state.targetSelector;

    var clipboard = new Clipboard("."  + state.buttonClass);

    clipboard.on('success', function(e) {
        clipboardBtn.notifyStatus(true);
        e.clearSelection();
    });

    clipboard.on('error', function(e) {
        clipboardBtn.notifyStatus(false);
    });


    this.onStateChange = function() {
        console.log("State change......okkkkkkk");
    };



};

