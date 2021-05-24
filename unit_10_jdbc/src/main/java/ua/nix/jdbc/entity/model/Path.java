package ua.nix.jdbc.entity.model;

public class Path extends AbstractModel {
    private int solutionId;
    private String fromCity;
    private String toCity;

    public int getSolutionId() {
        return solutionId;
    }

    public String[] getStringPath() {
        return new String[]{fromCity, toCity};
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
}
