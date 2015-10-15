package com.vaadin.jsclipboard;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.annotations.JavaScript;
import com.vaadin.jsclipboard.client.JSClipboardState;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.server.ClientConnector;
import com.vaadin.ui.Button;

@JavaScript("clipboard.js")
public class JSClipboard extends AbstractJavaScriptExtension {

    private static final long serialVersionUID = 5382186808485208584L;

    public JSClipboard() {

    }

    @Override
    protected Class<? extends ClientConnector> getSupportedParentType() {
        return Button.class;
    }

    @Override
    public JSClipboardState getState() {
        return (JSClipboardState) super.getState();
    }

    public void apply(Button target) {
        extend(target);
        parseSelector(target);
        attachEvent();
    }

    private void parseSelector(Button target) {
        if (StringUtils.isNotEmpty(target.getId())) {
            String value;
            if (target.getId().startsWith("#")) {
                value = target.getId();
            } else {
                value = "#" + target.getId();
            }
            getState().selector = StringUtils.replace(value, ".", "\\.");
        } else {
            target.setId(getState().selector.replaceFirst("#", ""));
        }
    }

    private void attachEvent() {
        callFunction("attachClickListener", this.getState().selector);
    }

    public void setText(String text) {
        getState().text = text;
    }
}
