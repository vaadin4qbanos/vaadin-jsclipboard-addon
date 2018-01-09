package com.vaadin.jsclipboard.demo.views;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextArea;


public class TextAreaView extends AbstractExampleView {
    public static final String NAME = "textarea";

    public TextAreaView() {
        super();
    }


    @Override
    public Component getTargetComponent() {
        final TextArea textArea = new TextArea();
        textArea.setSizeFull();
        textArea.setValue("Copy to clipboard textarea value....");
        textArea.setId("tocopie-textarea");
        return textArea;
    }

    @Override
    public String getExampleTitle() {
        return "Copy to clipboard example for TextArea Component";
    }

    @Override
    public String createComponentSourceCode() {
        return "		final TextArea textArea = new TextArea();\n" +
"		textArea.setSizeFull();\n" +
"		textArea.setValue(\"Copy to clipboard textarea value....\");\n" +
"		textArea.setId(\"tocopie-textarea\");";
    }


}
