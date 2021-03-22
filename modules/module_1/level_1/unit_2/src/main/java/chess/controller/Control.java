package chess.controller;

import chess.data.Horse;
import chess.services.AppLogic;
import chess.userinterface.ConsoleInteraction;

public class Control {

    public static void run() {
        boolean resume = true;
        while (resume) {
            try {
                Horse.getInstance().setPosition(AppLogic.startPosition(ConsoleInteraction.enterStartPosition()));
                resume = false;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
            }
        }
        do {
            resume = true;
            while (resume) {
                try {
                    Horse.getInstance().setPosition(AppLogic.nextPosition(ConsoleInteraction.enterNextPosition(), Horse.getInstance().getPosition()));
                    resume = false;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println("Конь на позиции : " + Horse.getInstance());
            }
        } while (AppLogic.endCalculate(ConsoleInteraction.enough()));
    }
}
