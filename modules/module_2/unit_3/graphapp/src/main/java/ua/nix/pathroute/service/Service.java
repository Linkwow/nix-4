package ua.nix.pathroute.service;

import ua.nix.pathroute.dao.NodeDaoImpl;
import ua.nix.pathroute.service.fileinteractions.Reader;
import ua.nix.pathroute.service.fileinteractions.Writer;

public class Service {
    private static Service instance;

    public void start() {
        Reader.getInstance().readInput();
        String[] dataFromFile = Reader.getInstance().readFile();
        StringBuilder sb = NodeDaoImpl.getInstance().generate(dataFromFile);
        Writer.getInstance().writeFile(sb.toString());
        Reader.getInstance().readOutput();
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
}
