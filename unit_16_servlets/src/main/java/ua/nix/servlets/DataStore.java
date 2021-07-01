package ua.nix.servlets;

import java.util.concurrent.ConcurrentHashMap;

public class DataStore {

    private static DataStore instance;
    private ConcurrentHashMap<String, String> visitorsData = new ConcurrentHashMap<>();

    private DataStore(){

    }

    public ConcurrentHashMap<String, String> getVisitorsData() {
        return visitorsData;
    }

    public void setVisitorsData(String key, String value) {
        this.visitorsData.put(key, value);
    }

    public static DataStore getInstance(){
        if(instance == null){
            instance = new DataStore();
        }
        return instance;
    }

}
