package ua.nix.reflection.properties;

import ua.nix.reflection.annotation.PropertyKey;

public class AppProperties {
    @PropertyKey("connectionToHost")
    public String host;

    @PropertyKey("maxConnections")
    public Integer numberOfConnections;

    @PropertyKey("version")
    public Double versionOfSoftware;

    @PropertyKey("security")
    public Boolean isSecurityChecked;


    @Override
    public String toString() {
        return "AppProperties{" +
                "host='" + host + '\'' +
                ", numberOfConnections=" + numberOfConnections +
                ", versionOfSoftware=" + versionOfSoftware +
                ", isSecurityChecked=" + isSecurityChecked +
                '}';
    }
}
