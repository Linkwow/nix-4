package ua.nix.jdbc.util;

public enum Constants {

    PROPERTY_FILE_NAME ("/jdbc.properties"),
    CITY_POLTAVA("poltava/3/6 14/3 9/2 7"),
    CITY_KIEV("kiev/3/1 7/4 15/3 10"),
    CITY_KHARKOV("kharkov/4/1 9/2 10/4 11/6 2"),
    CITY_LVOV("lvov/3/2 15/3 11/5 6"),
    CITY_ODESSA("odessa/2/4 6/6 9"),
    CITY_LUTSK("lutsk/3/1 14/5 9/3 2"),
    ROUTES("2/poltava lutsk/lvov lutsk");

    private final String value;

    Constants(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
