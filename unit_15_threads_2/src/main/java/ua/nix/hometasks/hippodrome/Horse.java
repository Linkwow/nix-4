package ua.nix.hometasks.hippodrome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Horse implements Runnable {

    private final Random random = new Random(System.nanoTime());
    private final Logger logger = LoggerFactory.getLogger(Horse.class);
    private final String name;
    private static int count;
    private final int id = count++;
    private int distance;
    private boolean chosen;
    private CountDownLatch waiter;

    public Horse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance += distance;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public void setWaiter(CountDownLatch waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        while (this.getDistance() < 1000) {
            logger.debug(this.getName() + " start running");
            this.setDistance(random.nextInt(101) + 100);
            logger.debug("Horse " + this.getName() + " overcame distance " + this.getDistance() + " meters");
            if (this.getDistance() < 1000) {
                try {
                    logger.debug("Horse " + this.getName() + " is sleeping now");
                    Thread.sleep(random.nextInt(101) + 400);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                    logger.error("Error when sleep method invokes", interruptedException);
                    throw new RuntimeException(interruptedException);
                }
            } else {
                try {
                    Hippodrome.setFinished(this);
                } finally {
                    waiter.countDown();
                }
                long finish = (System.nanoTime() - start) / 1000000;
                logger.debug("Horse " + this.getName() + " came to the finish line after " + finish + " ms");
            }
        }
    }
}
