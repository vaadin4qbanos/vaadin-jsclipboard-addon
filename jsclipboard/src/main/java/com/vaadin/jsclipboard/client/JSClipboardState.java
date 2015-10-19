package com.vaadin.jsclipboard.client;

import com.vaadin.shared.JavaScriptExtensionState;

public class JSClipboardState extends JavaScriptExtensionState {

    private static final long serialVersionUID = 6589269702904751560L;

    public String text;
    public String selector = "#clipboard";
    public boolean success;
}