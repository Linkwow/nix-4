package ua.nix.pathroute.routecalculator;

import ua.nix.pathroute.entity.Node;
import java.util.List;

public class Calculate {
    private static Calculate instance;
    Integer[][] pathMatrix;


    public void pathRouteCreate(List<Node> nodes) {
        pathMatrix = new Integer[nodes.size() + 1][nodes.size() + 1];
        for (Node node : nodes) {
            for (int i = 0; i < node.getNeighbors().size(); i++) {
                pathMatrix[node.getIndex()][node.getNeighbors().get(i)] = node.getRouteCost().get(i);
            }
        }
        for (int i = 0; i < pathMatrix.length; i++) {
            for (int j = 0; j < pathMatrix[i].length; j++) {
                if (pathMatrix[i][j] == null) {
                    pathMatrix[i][j] = 0;
                }
            }
        }
    }

    public String pathCount(Node start, Node finish) {
        Integer sum = 0, temp;
        for (int startIndex = start.getIndex(); startIndex < finish.getIndex() - 1; startIndex++) {
            for (int innerIndex = 1; innerIndex < pathMatrix.length; innerIndex++) {
                if (pathMatrix[startIndex][innerIndex] != 0) {
                    temp = pathMatrix[startIndex][innerIndex];
                    if(sum < temp){
                        sum += temp;
                        break;
                    }
                }
            }
        }
        return String.valueOf(sum);
    }

        public static Calculate getInstance () {
            if (instance == null) {
                instance = new Calculate();
            }
            return instance;
        }
    }
