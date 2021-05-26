package ua.nix.reflection.mapper;

import ua.nix.reflection.annotation.PropertyKey;
import ua.nix.reflection.application.App;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Mapper {

    public static Object initialize(Class<?> objectToInitialize, String filePath) {
        Object object = new Object();
        try {
            object = objectToInitialize.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        Field[] fields = objectToInitialize.getFields();
        Properties properties = getProperties(filePath);
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

    public static Properties getProperties(String filePath) {
        Properties properties = new Properties();
        try (InputStream inputStream = App.class.getResourceAsStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException ioException) {
            System.err.println("File not find. Please check properties file name");
        }
        return properties;
    }
}
