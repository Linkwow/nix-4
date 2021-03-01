package src.task.car;

import src.task.car.engine.Engine;
import org.apache.commons.lang3.*;

public class Car {

    Engine myEngine = new Engine();

    public void useStart(){
        myEngine.start();
    }
}
