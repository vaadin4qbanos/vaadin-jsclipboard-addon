package com.vaadin.jsclipboard.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.jsclipboard.ClipboardButton;
import com.vaadin.jsclipboard.JSClipboard;
import com.vaadin.jsclipboard.JSClipboardButton;
import com.vaadin.jsclipboard.demo.views.DeprecatedWayView;
import com.vaadin.jsclipboard.demo.views.GridView;
import com.vaadin.jsclipboard.demo.views.LabelView;
import com.vaadin.jsclipboard.demo.views.TextAreaView;
import com.vaadin.jsclipboard.demo.views.TextFieldView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

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

        VerticalLayout content = new VerticalLayout();
        content.setStyleName("demoContentLayout");
        content.setSizeFull();

        HorizontalLayout info = new HorizontalLayout();
        info.setWidth("100%");
        info.setHeightUndefined();
        setInfo(info);

        VerticalLayout viewContainer = new VerticalLayout();
        viewContainer.setMargin(false);
        viewContainer.setSizeFull();

        content.addComponents(info, viewContainer);
        content.setExpandRatio(viewContainer, 1);

        TabSheet examples = new TabSheet();
        examples.addTab(new TextFieldView(), "TextField");
        examples.addTab(new TextAreaView(), "TextArea");
        examples.addTab(new GridView(), "Grid");
        examples.addTab(new LabelView(), "Label");
        examples.addTab(new DeprecatedWayView(), "DeprecatedWay");

        viewContainer.addComponent(examples);

        setContent(content);
    }

    private void setInfo(HorizontalLayout info) {
        info.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Label logo = new Label(
                "<b>JSClipboard Demo</b>");
        logo.addStyleName(ValoTheme.LABEL_H2);
        logo.setContentMode(ContentMode.HTML);
        info.addComponent(logo);

        Label label = new Label(
                "This variant was done using the library <a href='https://clipboardjs.com/' >clipboard.js</a> as a Extension");
        label.setContentMode(ContentMode.HTML);
        info.addComponent(label);

        Label git = new Label(
                "<a href='https://github.com/vaadin4qbanos/vaadin-jsclipboard-addon'><b>GITHUB</b></a>");
        git.setContentMode(ContentMode.HTML);
        info.addComponent(git);
        info.setComponentAlignment(git, Alignment.MIDDLE_RIGHT);
        info.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);

    }

}
