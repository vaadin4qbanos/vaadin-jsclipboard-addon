package com.vaadin.jsclipboard.demo.views;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

public class TextFieldView extends AbstractExampleView {
    public static final String NAME = "textfield";

    public TextFieldView() {
        super();
    }


    @Override
    public Component getTargetComponent() {
        final TextField textField = new TextField();
        textField.setSizeFull();
        textField.setValue("Copy to clipboard textfield value....");
        textField.setId("tocopie-textfield");
        return textField;
    }

    @Override
    public String getExampleTitle() {
        return "Copy to clipboard example for TextField Component";
    }

    @Override
    public String createComponentSourceCode() {
        return "		final TextField textField = new TextField();\n" +
                "		textField.setSizeFull();\n" +
                "		textField.setValue(\"Copy to clipboard textfield value....\");\n" +
                "		textField.setId(\"tocopie-textfield\");";
    }


}
