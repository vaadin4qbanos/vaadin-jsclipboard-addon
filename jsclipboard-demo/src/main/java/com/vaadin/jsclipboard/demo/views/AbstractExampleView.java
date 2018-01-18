package com.vaadin.jsclipboard.demo.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.jsclipboard.ClipboardButton;
import com.vaadin.jsclipboard.JSClipboard;
import com.vaadin.jsclipboard.JSClipboardButton;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;
import java.util.Objects;

public abstract class AbstractExampleView extends VerticalLayout implements View{


    private Component targetComponent;

    public AbstractExampleView() {
        this.init();
    }

  
    private void init(){
        
        if(!Objects.isNull(getTargetComponent())){
            targetComponent = getTargetComponent();
        }else{
            Notification.show("EL componente target no puede ser nulo.");
            return;
        }


        Label label = new Label(this.getExampleTitle());
        label.setContentMode(ContentMode.HTML);
        addComponent(new VerticalLayout(label,targetComponent));
       
        TabSheet exmaplesTab = new TabSheet();

        exmaplesTab.addTab(createNewWayCopyToClipboardUsingExtension(),"JSClipboard Extension");
        exmaplesTab.addTab(createNewWayCopyToClipboardUsingWrapperButton(),"JSClipboardButton Extension");
        exmaplesTab.addTab(createNewWayCopyToClipboardUsingWrapperButtonDisabled(),"JSClipboardButton Extension Disabled");
        
        addComponent(new VerticalLayout(exmaplesTab));

    }





    private VerticalLayout createNewWayCopyToClipboardUsingExtension() {

        final JSClipboard clipboard = new JSClipboard();

        Button b = new Button("Copy to clipboard");
        clipboard.apply(b, targetComponent);
        clipboard.addSuccessListener(() -> {
            Notification.show("Copy to clipboard successful");
        });
        clipboard.addErrorListener(() -> {
            Notification.show("Copy to clipboard unsuccessful", Notification.Type.ERROR_MESSAGE);
        });

        Button source = new Button(VaadinIcons.CODE);
        source.addClickListener((Button.ClickEvent event) -> {
            Window win = createSourceWindowTab1();
            win.setModal(true);
            UI.getCurrent().addWindow(win);
        });

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setSpacing(true);
        wrapper.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        wrapper.addComponents(b, source);
        wrapper.setWidth("100%");

       return wrapper;
    }

    private VerticalLayout createNewWayCopyToClipboardUsingWrapperButton() {

        JSClipboardButton b = new JSClipboardButton(targetComponent, "Copy to clipboard");
        b.addSuccessListener(() -> {
            Notification.show("Copy to clipboard successful");
        });
        b.addErrorListener(() -> {
            Notification.show("Copy to clipboard unsuccessful", Notification.Type.ERROR_MESSAGE);
        });

        Button source = new Button(VaadinIcons.CODE);
        source.addClickListener(event -> {
            Window win = createSourceWindowTab2();
            win.setModal(true);
            UI.getCurrent().addWindow(win);
        });

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setSpacing(true);
        wrapper.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        wrapper.addComponents( b, source);
        wrapper.setWidth("100%");

       return wrapper;
    }

    private VerticalLayout createNewWayCopyToClipboardUsingWrapperButtonDisabled() {


        JSClipboardButton b = new JSClipboardButton(targetComponent, "Copy to clipboard");
        b.setEnabled(false);
        b.addSuccessListener(() -> {
            Notification.show("Copy to clipboard successful");
        });
        b.addErrorListener(() -> {
            Notification.show("Copy to clipboard unsuccessful", Notification.Type.ERROR_MESSAGE);
        });

        Button source = new Button(VaadinIcons.CODE);
        source.addClickListener((Button.ClickEvent event) -> {
            Window win = createSourceWindowTab2();
            win.setModal(true);
            UI.getCurrent().addWindow(win);
        });

        Button toggleEnable = new Button("Toggle Enabled");
        toggleEnable.addClickListener(event -> {
            b.setEnabled(!b.isEnabled());
        });

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setSpacing(true);
        wrapper.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        wrapper.addComponents(b, toggleEnable, source);
        wrapper.setWidth("100%");

        return wrapper;
    }




    public abstract Component getTargetComponent();

    public abstract String getExampleTitle();

    public abstract String createComponentSourceCode();

    private Window createSourceWindowTab1() {
        Window sourceWindow = new Window("Example");
        VerticalLayout content = new VerticalLayout();
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label sourceCode = new Label("<pre><code>" + "		final JSClipboard clipboard = new JSClipboard();\n\n"
                +
              this.createComponentSourceCode()
                + "\n\n"
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

    private Window createSourceWindowTab2() {
        Window sourceWindow = new Window("Example");
        VerticalLayout content = new VerticalLayout();
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label sourceCode = new Label("<pre><code>" +
                this.createComponentSourceCode()+"\n\n"
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




    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
