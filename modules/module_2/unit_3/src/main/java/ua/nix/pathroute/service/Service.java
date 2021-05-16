package ua.nix.pathroute.service;

import ua.nix.pathroute.entity.Node;
import ua.nix.pathroute.service.fileinteractions.Reader;
import ua.nix.pathroute.dao.impl.NodeDaoImpl;
import ua.nix.pathroute.routecalculator.Calculate;
import ua.nix.pathroute.service.fileinteractions.Writer;

import java.util.List;

public class Service {
    private static Service instance;

    public void start() {
        Reader.getInstance().readInput();
        String[] dataFromFile = Reader.getInstance().readFile();
        String findTown = dataFromFile[dataFromFile.length - 1];
        List<Node> list = NodeDaoImpl.getInstance().generate(dataFromFile);
        Calculate.getInstance().pathRouteCreate(list);
        String[] towns = findTown.split("/");
        Node firstNode = new Node();
        Node lastNode = new Node();
        String s = "";
        for (int index = 0; index < towns.length; index++) {
            String[] name = towns[index].split(" ");
            for (Node node : list) {
                if (node.getTownName().equals(name[0])) {
                    firstNode = node;
                } else if (node.getTownName().equals(name[1])) {
                    lastNode = node;
                }
            }
            s += Calculate.getInstance().pathCount(firstNode, lastNode) + " ";
        }
        for (String write : s.split(" ")) {
            Writer.getInstance().writeFile(write);
        }
        Reader.getInstance().readOutput();
    }

    public static Service getInstance() {
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }
}
