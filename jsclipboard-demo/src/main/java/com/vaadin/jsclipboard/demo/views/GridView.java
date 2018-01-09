package com.vaadin.jsclipboard.demo.views;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import java.io.Serializable;
import java.util.Arrays;

public class GridView extends AbstractExampleView {

    public static final String NAME = "grid";

    public GridView() {
        super();
    }

    @Override
    public Component getTargetComponent() {
        final Grid<Person> grid = new Grid(Person.class);
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeightByRows(3);
        grid.setItems(Arrays.asList(new Person("Arthur", 12),new Person("Ruben", 30),new Person("Ulises", 109)));
        grid.setId("tocopie-grid");
        return grid;
    }

    @Override
    public String getExampleTitle() {
        return "Copy to clipboard example for Grid Component";
    }

    @Override
    public String createComponentSourceCode() {
        return "		final Grid<Person> grid = new Grid(Person.class);\n" +
"		grid.setSizeFull();\n" +
"		grid.setHeightMode(HeightMode.ROW);\n" +
"		grid.setHeightByRows(3);\n" +
"		grid.setItems(Arrays.asList(new Person(\"Arthur\", 12),new Person(\"Esther\", 44),new Person(\"Ulises\", 109)));\n" +
"		grid.setId(\"tocopie-grid\");";
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
