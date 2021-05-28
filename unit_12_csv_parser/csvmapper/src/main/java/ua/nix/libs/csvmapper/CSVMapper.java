package ua.nix.libs.csvmapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import ua.nix.libs.csvdatastore.CSVDataStore;

public class CSVMapper<T> {

    private final Class<T> type;
    private final CSVDataStore csvDataStore;
    private T object;
    private final Class<Annotation> annotation;
    private final Field[] fields;
    private final List<T> objectList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public CSVMapper(Class<T> type, CSVDataStore csvDataStore, Class<? extends Annotation> annotationClass) {
        this.type = type;
        this.fields = type.getDeclaredFields();
        this.csvDataStore = csvDataStore;
        this.annotation = (Class<Annotation>) annotationClass;
        try {
            mapping();
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void mapping() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        for (int key : csvDataStore.getAllData().keySet()) {
            object = type.getDeclaredConstructor().newInstance();
            for (Field field : fields) {
                if (field.isAnnotationPresent(annotation)) {
                    Annotation declaredAnnotation = field.getAnnotation(annotation);
                    String columnName = (String) declaredAnnotation.getClass().getMethod("value").invoke(declaredAnnotation);
                    if (csvDataStore.getHeader().contains(columnName)) {
                        field.setAccessible(true);
                        try {
                            setField(field, key, columnName);
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                            throw new IllegalAccessException(illegalAccessException.getMessage());
                        }
                    }
                }
            }
            objectList.add(object);
        }
    }

    private void setField(Field field, int row, String columnName) throws IllegalAccessException {
        String result = csvDataStore.getData(row, columnName);
        try {
            if (field.getType() == Integer.class || field.getType() == int.class) {
                field.set(object, Integer.parseInt(result));
            } else if (field.getType() == Short.class || field.getType() == short.class) {
                field.set(object, Short.parseShort(result));
            } else if (field.getType() == Long.class || field.getType() == long.class) {
                field.set(object, Long.parseLong(result));
            } else if (field.getType() == Float.class || field.getType() == float.class) {
                field.set(object, Float.parseFloat(result));
            } else if (field.getType() == Double.class || field.getType() == double.class) {
                field.set(object, Double.parseDouble(result));
            } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                field.set(object, Boolean.parseBoolean(result));
            } else if (field.getType() == Byte.class || field.getType() == byte.class) {
                field.set(object, Byte.parseByte(result));
            } else if (field.getType() == String.class) {
                field.set(object, result);
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
            throw new IllegalAccessException(illegalAccessException.getMessage());
        }
    }

    public List<T> getObjectList() {
        return objectList;
    }
}