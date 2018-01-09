package com.vaadin.jsclipboard.demo.views;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.io.Serializable;

public class LabelView extends AbstractExampleView {

    public static final String NAME = "label";

    public LabelView() {
        super();
    }

    @Override
    public Component getTargetComponent() {
        final Label label= new Label("Copy to clipboard Label value....");
        label.setSizeFull();      
        label.setId("tocopie-label");
        return label;
    }

    @Override
    public String getExampleTitle() {
        return "Copy to clipboard example for Label Component";
    }

    @Override
    public String createComponentSourceCode() {
        return "		final Label label= new Label(\"Copy to clipboard Label value....\");\n" +
"		label.setSizeFull();      \n" +
"		label.setId(\"tocopie-label\");";
    }

    public class Person implements Serializable {

        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

    }

}
