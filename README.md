# JSClipboard Add-on for Vaadin 7 & 8


[TOC]



JSClipboard is an JS extension for Vaadin 7 & 8 that allow to copy arbitrary text to clipboard using JavaScript only.

## Improvements
- It allows stylize the button.
- The method setEnabled works correctly.
- Adds a button that works as a wrapper for the extension (JSClipboardButton)


## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/jsclipboard

## Building and running demo

``` bash
git clone https://github.com/vaadin4qbanos/vaadin-jsclipboard-addon.git
cd vaadin-jsclipboard-addon
mvn clean install
cd jsclipboard-demo
mvn jetty:run
```

To see the demo, navigate to http://localhost:8080/jsclipboard-demo

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated.

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

JSClipboard is written by Rubén Bresler Camps and Geanny Hernández Rodríguez

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

```java
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
```

Another way is using a component wrapper `JSClipboardButton` like this:

```java
final TextArea area = new TextArea();

area.setValue("This is a sample text...");
area.setId("tocopie");
area.focus();

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

```

==*Note: It was decided to revert back to an extension so that the component could be stylized easily*==

This way to do copy to clipboard using the library javascript (This way is deprecated) [clipboard.js](https://clipboardjs.com/)
```java
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
```
For a more comprehensive example, see src/test/java/com/vaadin/jsclipboard/demo/DemoUI.java


