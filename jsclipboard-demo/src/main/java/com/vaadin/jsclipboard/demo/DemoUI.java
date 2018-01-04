package com.vaadin.jsclipboard.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.jsclipboard.ClipboardButton;
import com.vaadin.jsclipboard.JSClipboard;
import com.vaadin.jsclipboard.JSClipboardButton;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("JSClipboard Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "com.vaadin.jsclipboard.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final GridLayout layout = new GridLayout(2,2);
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        createNewWayCopyToClipboardUsingExtension(layout);
        createNewWayCopyToClipboardUsingWrapperButton(layout);
        
        createOldWayCopyToClipboard(layout);

        setContent(layout);
    }

    private void createNewWayCopyToClipboardUsingExtension(GridLayout layout) {
        Label label = new Label(
                "This variant was done using the library <a href='https://clipboardjs.com/' >clipboard.js</a> as a Extension");
        label.setContentMode(ContentMode.HTML);

        final JSClipboard clipboard = new JSClipboard();

        final TextArea area = new TextArea();

        area.setValue("This is a sample text...");
        area.setId("tocopie");       

        Button b = new Button("Copy to clipboard");
        clipboard.apply(b, area);
        clipboard.addSuccessListener(new JSClipboard.SuccessListener() {

            @Override
            public void onSuccess() {
                Notification.show("Copy to clipboard successful");
            }
        });
        clipboard.addErrorListener(new JSClipboard.ErrorListener() {

            @Override
            public void onError() {
                Notification.show("Copy to clipboard unsuccessful", Notification.Type.ERROR_MESSAGE);
            }
        });

        Button source = new Button(VaadinIcons.CODE);
        source.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window win = createNewWaySourceWindow();
                win.setModal(true);
                UI.getCurrent().addWindow(win);
            }
        });

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setSpacing(true);
        wrapper.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        wrapper.addComponents(new HorizontalLayout(label), area, b, source);
        wrapper.setWidth("100%");

        layout.addComponent(wrapper,0,0);
    }

    private void createNewWayCopyToClipboardUsingWrapperButton(GridLayout layout) {
        Label label = new Label(
                "This variant was done using the library <a href='https://clipboardjs.com/' >clipboard.js</a> as a Extension using a wrapper");
        label.setContentMode(ContentMode.HTML);

        final TextArea area = new TextArea();

        area.setValue("This is a sample text...");
        area.setId("tocopie");
      
        JSClipboardButton b = new JSClipboardButton(area, "Copy to clipboard");
        b.addSuccessListener(new JSClipboard.SuccessListener() {

            @Override
            public void onSuccess() {
                Notification.show("Copy to clipboard successful");
            }
        });
        b.addErrorListener(new JSClipboard.ErrorListener() {

            @Override
            public void onError() {
                Notification.show("Copy to clipboard unsuccessful", Notification.Type.ERROR_MESSAGE);
            }
        });

        Button source = new Button(VaadinIcons.CODE);
        source.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window win = createNewWayWrapperSourceWindow();
                win.setModal(true);
                UI.getCurrent().addWindow(win);
            }
        });

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setSpacing(true);
        wrapper.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        wrapper.addComponents(new HorizontalLayout(label), area, b, source);
        wrapper.setWidth("100%");

        layout.addComponent(wrapper,1,0);
    }

    private void createOldWayCopyToClipboard(GridLayout layout) {

        Label label = new Label(
                "This variant  will be removed, see <a target=\"_blank\" href=\"https://github.com/vaadin4qbanos/vaadin-jsclipboard-addon/blob/master/README.md\">README</a><br>"
                        + "This variant was done using the library <a href='https://clipboardjs.com/' >clipboard.js</a> as a Component");
        label.setContentMode(ContentMode.HTML);

        final TextArea anotherArea = new TextArea();
        anotherArea.setId("clipboardTarget");
        anotherArea.setValue("Another example to copy to clipboard");

        ClipboardButton clipboardButton = new ClipboardButton("clipboardTarget");
        clipboardButton.addSuccessListener(new ClipboardButton.SuccessListener() {

            @Override
            public void onSuccess() {
                Notification.show("Copy to clipboard successful");
            }
        });
        clipboardButton.addErrorListener(new ClipboardButton.ErrorListener() {

            @Override
            public void onError() {
                Notification.show("Copy to clipboard unsuccessful", Notification.Type.ERROR_MESSAGE);
            }
        });

        Button source = new Button(VaadinIcons.CODE);
        source.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window win = createOldWaySourceWindow();
                win.setModal(true);
                UI.getCurrent().addWindow(win);
            }
        });

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setSpacing(true);
        wrapper.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        wrapper.setWidth("100%");
        wrapper.addComponents(new HorizontalLayout(label), anotherArea, clipboardButton, source);
        layout.addComponent(wrapper,0,1);
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

    private Window createNewWaySourceWindow() {
        Window sourceWindow = new Window("Example");
        VerticalLayout content = new VerticalLayout();
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label sourceCode = new Label("<pre><code>" + "		final JSClipboard clipboard = new JSClipboard();\n"
                + "\n"
                + "		final TextArea area = new TextArea();\n"
                + "\n"
                + "		area.setValue(\"This is a sample text...\");\n"
                + "		area.setId(\"tocopie\");		\n"            
                + "\n"
                + "		Button b = new Button(\"Copy to clipboard\");\n"
                + "		clipboard.apply(b,area);\n"
                + "		clipboard.addSuccessListener(new JSClipboard.SuccessListener() {\n"
                + "\n"
                + "			@Override\n"
                + "			public void onSuccess() {\n"
                + "				Notification.show(\"Copy to clipboard successful\");\n"
                + "			}\n"
                + "		});\n"
                + "		clipboard.addErrorListener(new JSClipboard.ErrorListener() {\n"
                + "\n"
                + "			@Override\n"
                + "			public void onError() {\n"
                + "				Notification.show(\"Copy to clipboard unsuccessful\", Notification.Type.ERROR_MESSAGE);\n"
                + "			}\n"
                + "		});  </code></pre>", ContentMode.HTML);

        content.addComponent(new HorizontalLayout(sourceCode));
        sourceWindow.setContent(content);
        sourceWindow.setHeight("400px");
        return sourceWindow;
    }

    private Window createNewWayWrapperSourceWindow() {
        Window sourceWindow = new Window("Example");
        VerticalLayout content = new VerticalLayout();
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label sourceCode = new Label("<pre><code>" + "        final TextArea area = new TextArea();\n"
                + "\n"
                + "        area.setValue(\"This is a sample text...\");\n"
                + "        area.setId(\"tocopie\");\n"            
                + "\n"
                + "        JSClipboardButton b = new JSClipboardButton(area,\"Copy to clipboard\");\n"
                + "        b.addSuccessListener(new JSClipboard.SuccessListener() {\n"
                + "\n"
                + "            @Override\n"
                + "            public void onSuccess() {\n"
                + "                Notification.show(\"Copy to clipboard successful\");\n"
                + "            }\n"
                + "        });\n"
                + "        b.addErrorListener(new JSClipboard.ErrorListener() {\n"
                + "\n"
                + "            @Override\n"
                + "            public void onError() {\n"
                + "                Notification.show(\"Copy to clipboard unsuccessful\", Notification.Type.ERROR_MESSAGE);\n"
                + "            }\n"
                + "        });</code></pre>", ContentMode.HTML);

        content.addComponent(new HorizontalLayout(sourceCode));
        sourceWindow.setContent(content);
        sourceWindow.setHeight("400px");
        return sourceWindow;
    }

}
