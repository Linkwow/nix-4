package ua.nix.hometasks.hippodrome;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class Hippodrome {

    private static final CopyOnWriteArrayList<Horse> finished = new CopyOnWriteArrayList<>();
    private final CountDownLatch countDownLatch;
    private final List<Horse> stable;

    public Hippodrome(List<Horse> stable){
        this.stable = stable;
        countDownLatch = new CountDownLatch(stable.size());
    }

    public List<Horse> getStable() {
        return stable;
    }

    public void chooseHorse(int id) {
        stable.get(id).setChosen(true);
    }

    public void startRace() {
        for (Horse horse : stable) {
            horse.setWaiter(countDownLatch);
            new Thread(horse, horse.getName()).start();
        }
    }

    public static void setFinished(Horse horse){
        finished.add(horse);
    }

    public CopyOnWriteArrayList<Horse> getWinner(){
        return finished;
    }

    public void waitAll(){
        try {
            countDownLatch.await();
        } catch (InterruptedException interruptedException){
            Thread.currentThread().interrupt();
            throw new RuntimeException(interruptedException);
        }
    }
}
