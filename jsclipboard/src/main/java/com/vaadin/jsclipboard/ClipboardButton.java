/*
 * Copyright 2016 vaadin4qbanos.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vaadin.jsclipboard;

import com.vaadin.annotations.JavaScript;
import com.vaadin.jsclipboard.client.ClipboardButtonState;
import com.vaadin.server.LegacyApplication;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Geanny Hernández Rodríguez
 */
@JavaScript({"bower_components/clipboard/dist/clipboard.min.js", "clipboard_button.js"})
public class ClipboardButton extends AbstractJavaScriptComponent {

    private static final long serialVersionUID = 1L;

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

    public ClipboardButton(String targetId) {
        if (targetId == null || "".equals(targetId)) {
            throw new RuntimeException("The target element must not be empty or null");
        }

        setClipboardButtonClass();
        setTarget(targetId);

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

    public void setClipboardButtonCaption(String caption) {
        getState().clipboardButtonCaption = caption;
    }

    private void setTarget(String targetId) {
        getState().targetId = targetId;
    }

    private void setClipboardButtonClass() {
        getState().buttonClass = "btn" + UUID.randomUUID().toString().replace("-", "_");
    }

    public void setClipboardTarget(String targetId) {
        setTarget(targetId);
    }

    @Override
    protected ClipboardButtonState getState() {
        return (ClipboardButtonState) super.getState();
    }

}
