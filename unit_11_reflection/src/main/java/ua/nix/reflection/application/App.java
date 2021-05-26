package ua.nix.reflection.application;

import ua.nix.reflection.annotation.PropertyKey;
import ua.nix.reflection.properties.AppProperties;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class App {

    AppProperties appProperties;

    public App() {
        appProperties = (AppProperties) initialize(AppProperties.class);
    }

    public Object initialize(Class<?> objectToInitialize) {
        Object object = new Object();
        try {
            object = objectToInitialize.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        Field[] fields = objectToInitialize.getFields();
        Properties properties = getProperties();
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey keyOfAnnotation = field.getAnnotation(PropertyKey.class);
                    String value = properties.getProperty(keyOfAnnotation.value());
                    if (field.getType() == Integer.class || field.getType() == int.class) {
                        field.set(object, Integer.parseInt(value));
                    } else if (field.getType() == Double.class || field.getType() == double.class) {
                        field.set(object, Double.parseDouble(value));
                    } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                        field.set(object, Boolean.parseBoolean(value));
                    } else if (field.getType() == String.class) {
                        field.set(object, value);
                    }
                }
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        return object;
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

    @Override
    public String toString() {
        return "App{" +
                "appProperties=" + appProperties +
                '}';
    }
}
