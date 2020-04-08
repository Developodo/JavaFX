package com.carlosserrano.proyectojavafx.controller;
/**
 * Enum with String values: https://howtodoinjava.com/java/enum/java-enum-string-example/
 */
public enum Scenes{
    ROOT("view/root"), 
    PRIMARY("view/primary"), 
    CONN("view/connections"),
    ABOUT("view/about");

    private String url;
 
    Scenes(String fxmlFile) {
        this.url = fxmlFile;
    }
 
    public String getUrl() {
        return url;
    }
}