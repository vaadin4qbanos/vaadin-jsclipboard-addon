package com.vaadin.jsclipboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.annotations.JavaScript;
import com.vaadin.jsclipboard.client.JSClipboardState;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.server.ClientConnector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;
import java.util.UUID;


@JavaScript({"bower_components/clipboard/dist/clipboard.min.js", "clipboard.js"})
public class JSClipboard extends AbstractJavaScriptExtension {

    private static final long serialVersionUID = 5382186808485208584L;

    public interface SuccessListener extends Serializable {

        void onSuccess();
    }

    public interface ErrorListener extends Serializable {

        void onError();
    }

    List<SuccessListener> successListeners = new ArrayList<SuccessListener>();

    List<ErrorListener> errorListeners = new ArrayList<ErrorListener>();

    public void addSuccessListener(SuccessListener listener) {
        if (listener != null) {
            successListeners.add(listener);
        }
    }

    public void addErrorListener(ErrorListener listener) {
        if (listener != null) {
            errorListeners.add(listener);
        }
    }

    public JSClipboard() {
        setClipboardButtonClass();
        addFunction("notifyStatus", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                boolean status = arguments.getBoolean(0);
                if (status) {
                    for (SuccessListener successListener : successListeners) {
                        successListener.onSuccess();
                    }
                } else {
                    for (ErrorListener errorListener : errorListeners) {
                        errorListener.onError();
                    }
                }
            }
        });
    }

    private void setClipboardButtonClass() {
        getState().buttonClass = "btn" + UUID.randomUUID().toString().replace("-", "_");
        getState().targetSelector = "#target" + UUID.randomUUID().toString().replace("-", "_");
    }

    @Override
    protected Class<? extends ClientConnector> getSupportedParentType() {
        return Button.class;
    }

    @Override
    public JSClipboardState getState() {
        return (JSClipboardState) super.getState();
    }

    public void apply(Button trigger, Component target) {
        extend(trigger);      
        trigger.addStyleName(getState().selector);
        trigger.addStyleName(getState().buttonClass);
        parseSelector(target);
    }
    
    
    private void parseSelector(Component target) {
		if (StringUtils.isNotEmpty(target.getId())) {
			String value;
			if (target.getId().startsWith("#")) {
				value = target.getId();
			} else {
				value = "#" + target.getId();
			}
			getState().targetSelector = StringUtils.replace(value, ".", "\\.");
		} else {
			target.setId(getState().targetSelector.replaceFirst("#", ""));
		}
	}
    
    public void setText(String text) {
        getState().text = text;
    }
}
