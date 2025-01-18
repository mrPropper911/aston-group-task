package by.aston;

import by.aston.view.Simulation;

public class Application {
    public static void main(String[] args) {
        // var ui = new UserInterfaceApplication(new Simulation(new Scanner(System.in)));
        // ui.run();
        var ui = new Simulation();
        ui.run();

    }
}
