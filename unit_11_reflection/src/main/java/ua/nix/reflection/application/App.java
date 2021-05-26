package ua.nix.reflection.application;

import ua.nix.reflection.properties.AppProperties;
import ua.nix.reflection.mapper.Mapper;


public class App {

    AppProperties appProperties;

    public App() {
        appProperties = (AppProperties) Mapper.initialize(AppProperties.class, "/app.properties");
    }

    @Override
    public String toString() {
        return "App{" +
                "appProperties=" + appProperties +
                '}';
    }
}
