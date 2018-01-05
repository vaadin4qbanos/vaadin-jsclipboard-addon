/*
 * Copyright 2018 vaadin4qbanos.
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

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 *
 * @author geanny
 */
public class JSClipboardButton extends Button {

    private JSClipboard clipboard;

    public JSClipboardButton(Component target) {
        this.initClipboard(target);
    }

    public JSClipboardButton(Component target, String caption) {
        super(caption);
        this.initClipboard(target);
    }

    public JSClipboardButton(Component target, Resource icon) {
        super(icon);
        this.initClipboard(target);
    }

    public JSClipboardButton(Component target, String caption, Resource icon) {
        super(caption, icon);
        this.initClipboard(target);
    }

    public JSClipboardButton(Component target, String caption, ClickListener listener) {
        super(caption, listener);
        this.initClipboard(target);
    }

    private void initClipboard(Component target) {
        this.clipboard = new JSClipboard();
        this.clipboard.apply(this, target);
    }

    public void addSuccessListener(JSClipboard.SuccessListener listener) {
        this.clipboard.addSuccessListener(listener);
    }

    public void addErrorListener(JSClipboard.ErrorListener listener) {
        this.clipboard.addErrorListener(listener);
    }

    public void setClipboardText(String text) {
        this.clipboard.setText(text);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled); 
        this.clipboard.setEnabled(enabled);
    }
    
    

}
