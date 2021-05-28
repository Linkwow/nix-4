package ua.nix.app.demonstration.objects;

public enum Citizen {

    UKR("ukraine"),
    RUS("russian"),
    POL("poland");

    public String value;

    Citizen(String value) {
        this.value = value;
    }
}
