package ua.nix.reflection.application;

import ua.nix.reflection.annotation.PropertyKey;
import ua.nix.reflection.properties.AppProperties;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class App {

    AppProperties appProperties;

    public App() {
        appProperties = new AppProperties();
    }

    @Override
    public String toString() {
        return "App{" +
                "appProperties=" + appProperties +
                '}';
    }

    public void initialize() throws IllegalAccessException {
        Field[] fields = appProperties.getClass().getFields();
        Properties properties = getProperties();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                if (field.getAnnotation(PropertyKey.class).value().equals("connectionToHost")) {
                    field.set(appProperties, properties.getProperty(field.getAnnotation(PropertyKey.class).value()));
                } else if (field.getAnnotation(PropertyKey.class).value().equals("maxConnections")) {
                    field.set(appProperties, Integer.parseInt(properties.getProperty(field.getAnnotation(PropertyKey.class).value())));
                }
            }
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = App.class.getResourceAsStream("/app.properties")) {
            properties.load(inputStream);
        } catch (IOException ioException) {
            System.err.println("File not find. Please check properties file name");
        }
        return properties;
    }
}
