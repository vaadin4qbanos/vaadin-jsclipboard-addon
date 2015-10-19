package com.vaadin.jsclipboard.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.jsclipboard.JSClipboard;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        final JSClipboard clipboard = new JSClipboard();

        final TextArea area = new TextArea();
        area.setValue("This is a sample text...");
        area.addBlurListener(new BlurListener() {

            @Override
            public void blur(BlurEvent event) {
                clipboard.setText(area.getValue());
            }
        });

        Button b = new Button("Copy to clipboard");
        clipboard.apply(b, new JSClipboard.onSuccessListener() {

            @Override
            public void onSuccess() {
                Notification.show("Cool this thing realy work, jajajaj!");
            }
        }, new JSClipboard.onErrorListener() {

            @Override
            public void onError() {
                Notification.show("Ups!, this lock like a breakeamiento, jajajaja",Notification.Type.ERROR_MESSAGE);
            }
        });      

        layout.addComponents(area, b);

        setContent(layout);
    }

}
