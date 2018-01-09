package com.vaadin.jsclipboard.demo.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.jsclipboard.ClipboardButton;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DeprecatedWayView extends VerticalLayout implements View {

    public static final String NAME = "deprecated";

    public DeprecatedWayView() {
       createOldWayCopyToClipboard();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private void createOldWayCopyToClipboard() {

        Label label = new Label(
                "This variant  will be removed, see <a target=\"_blank\" href=\"https://github.com/vaadin4qbanos/vaadin-jsclipboard-addon/blob/master/README.md\">README</a><br>"
                + "This variant was done using the library <a href='https://clipboardjs.com/' >clipboard.js</a> as a Component");
        label.setContentMode(ContentMode.HTML);

        final TextArea anotherArea = new TextArea();
        anotherArea.setId("clipboardTarget");
        anotherArea.setValue("Another example to copy to clipboard");

        ClipboardButton clipboardButton = new ClipboardButton("clipboardTarget");
        clipboardButton.addSuccessListener(() -> {
            Notification.show("Copy to clipboard successful");
        });
        clipboardButton.addErrorListener(() -> {
            Notification.show("Copy to clipboard unsuccessful", Notification.Type.ERROR_MESSAGE);
        });

        Button source = new Button(VaadinIcons.CODE);
        source.addClickListener((Button.ClickEvent event) -> {
            Window win = createOldWaySourceWindow();
            win.setModal(true);
            UI.getCurrent().addWindow(win);
        });

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setSpacing(true);
        wrapper.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        wrapper.setWidth("100%");
        wrapper.addComponents(new HorizontalLayout(label), anotherArea, clipboardButton, source);
        addComponent(wrapper);
    }

    private Window createOldWaySourceWindow() {
        Window sourceWindow = new Window("Example");
        VerticalLayout content = new VerticalLayout();
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label sourceCode = new Label("<pre><code>" + "        final TextArea anotherArea = new TextArea();\n"
                + "        anotherArea.setId(\"clipboardTarget\");\n"
                + "        anotherArea.setValue(\"Another example to copy to clipboard\");\n" + "\n"
                + "        ClipboardButton clipboardButton = new ClipboardButton(\"clipboardTarget\");\n"
                + "        clipboardButton.addSuccessListener(new ClipboardButton.SuccessListener() {\n" + "\n"
                + "            @Override\n" + "            public void onSuccess() {\n"
                + "                Notification.show(\"Copy to clipboard successful\");\n" + "            }\n"
                + "        });\n" + "        clipboardButton.addErrorListener(new ClipboardButton.ErrorListener() {\n"
                + "\n" + "            @Override\n" + "            public void onError() {\n"
                + "                Notification.show(\"Copy to clipboard unsuccessful\", Notification.Type.ERROR_MESSAGE);\n"
                + "            }\n" + "        });</code></pre>", ContentMode.HTML);

        content.addComponent(new HorizontalLayout(sourceCode));
        sourceWindow.setContent(content);
        sourceWindow.setHeight("400px");
        return sourceWindow;
    }

}
