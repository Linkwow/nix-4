package ua.nix.pathroute.service;

import ua.nix.pathroute.entity.Graph;
import ua.nix.pathroute.entity.Node;
import ua.nix.pathroute.service.fileinteractions.Reader;
import ua.nix.pathroute.dao.impl.NodeDaoImpl;
import ua.nix.pathroute.routecalculator.Calculate;
import ua.nix.pathroute.service.fileinteractions.Writer;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private static Service instance;

    public void start() {
        Reader.getInstance().readInput();
        String[] dataFromFile = Reader.getInstance().readFile();
        List<Node> list;
        String findTown = dataFromFile[dataFromFile.length - 1];
        String[] towns = findTown.split("/");
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j < towns.length; j++) {
            list = NodeDaoImpl.getInstance().generate(dataFromFile);
            String[] name = towns[j].split(" ");
            Graph graph = new Graph(list);
            sb.append(graph.start(name));
            sb.append(" ");
        }
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
