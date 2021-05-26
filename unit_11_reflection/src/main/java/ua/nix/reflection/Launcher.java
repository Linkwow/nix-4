package ua.nix.reflection;

import ua.nix.reflection.application.App;

public class Launcher {

    public static void main(String[] args) throws Exception {
        App app = new App();
        System.out.println(app);
        app.initialize();
        System.out.println(app);
    }
}
