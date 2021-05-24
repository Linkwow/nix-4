package ua.nix.jdbc.entity;

import ua.nix.jdbc.entity.model.*;
import ua.nix.jdbc.util.Constants;
import ua.nix.libs.entity.Node;
import ua.nix.libs.routecost.RouteCost;

import java.util.ArrayList;
import java.util.List;

public class ModelGenerator {
    private final List<Node> nodes;
    private static final String[] inputData = new String[]{
            Constants.CITY_POLTAVA.getValue(),
            Constants.CITY_KIEV.getValue(),
            Constants.CITY_KHARKOV.getValue(),
            Constants.CITY_LVOV.getValue(),
            Constants.CITY_ODESSA.getValue(),
            Constants.CITY_LUTSK.getValue(),
            Constants.ROUTES.getValue()
    };

    public ModelGenerator(){
       nodes = RouteCost.getInstance().generate(inputData);
    }

    public List<Location> generateLocations(){
        List<Location> locations = new ArrayList<>();
        Location location;
        for (Node node : nodes){
            location = new Location();
            location.setId(node.getVertex());
            location.setName(node.getTownName());
            locations.add(location);
        }
        return locations;
    }

    public List<Route> generateRoutes(){
        List<Route> routes = new ArrayList<>();
        Route route;
        int id = 1;
        for (Node node : nodes){
            for(int key : node.getRoute().keySet()){
                route = new Route();
                route.setId(id++);
                route.setFromId(node.getVertex());
                route.setToId(key);
                route.setCost(node.getRoute().get(key));
                routes.add(route);
            }
        }
        return routes;
    }

    public Problem generateProblem(int id, int fromId, int toId){
        Problem problem = new Problem();
        problem.setId(id);
        problem.setFromId(fromId);
        problem.setToId(toId);
        return problem;
    }

    public Solution generateSolution(int id, int problemId){
        Solution solution = new Solution();
        solution.setId(id);
        solution.setProblemId(problemId);
        return solution;
    }

    public static String[] getInputData() {
        return inputData;
    }
}
