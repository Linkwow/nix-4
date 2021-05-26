package ua.nix.reflection.properties;

import ua.nix.reflection.annotation.PropertyKey;

public class AppProperties {
    @PropertyKey("connectionToHost")
    public String host;

    @PropertyKey("maxConnections")
    public Integer numberOfConnections;

    @Override
    public String toString() {
        return "AppProperties{" +
                "host='" + host + '\'' +
                ", numberOfConnections=" + numberOfConnections +
                '}';
    }
}
