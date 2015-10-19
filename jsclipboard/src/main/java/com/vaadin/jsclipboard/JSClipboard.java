package com.vaadin.jsclipboard;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.annotations.JavaScript;
import com.vaadin.jsclipboard.client.JSClipboardState;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.server.ClientConnector;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import java.io.Serializable;
import java.util.ArrayList;

@JavaScript("clipboard.js")
public class JSClipboard extends AbstractJavaScriptExtension {
    
    private static final long serialVersionUID = 5382186808485208584L;
    
    public interface onSuccessListener extends Serializable {
        
        void onSuccess();
    }
    
    public interface onErrorListener extends Serializable {
        
        void onError();
    }
    
    ArrayList<onSuccessListener> successListeners
            = new ArrayList<onSuccessListener>();
    
    ArrayList<onErrorListener> errorListeners
            = new ArrayList<onErrorListener>();
    
    public void addOnSuccessListener(
            onSuccessListener listener) {
        successListeners.add(listener);
    }
    
    public void addOnErrorListener(
            onErrorListener listener) {
        errorListeners.add(listener);
    }
    
    public JSClipboard() {
        addFunction("notifyStatus", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                boolean status = arguments.getBoolean(0);
                if (status) {
                    for (onSuccessListener successListener : successListeners) {
                        successListener.onSuccess();
                    }
                } else {
                    for (onErrorListener errorListener : errorListeners) {
                        errorListener.onError();
                    }
                }
            }
        });
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
    
    public void apply(Button target, onSuccessListener successListener, onErrorListener errorListener) {
        
        if (errorListener != null) {
            addOnErrorListener(errorListener);
        }
        if (successListener != null) {
            addOnSuccessListener(successListener);
        }
        this.apply(target);
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
