package ua.nix.threadexample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteToFile implements Runnable {

    private final String threadName = "FileWriter";
    private final Application application;
    private final File file = new File("src\\main\\java\\ua\\nix\\threadexample\\output.txt");
    private static Logger logger;
    private String input;

    public WriteToFile(Application application) throws IOException {
        this.application = application;
        logger = LoggerFactory.getLogger(WriteToFile.class);
    }

    @Override
    public void run() {
        synchronized (application) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                while (true) {
                    if (application.isStop())
                        break;
                    input = application.getInput();
                    application.wait(1000);
                    logger.debug("input hashCode" + input.hashCode());
                    logger.debug("application hashcode" + application.getInput().hashCode());
                    if (input.hashCode() != application.getInput().hashCode()) {
                        fileWriter.write(application.getInput());
                        input = application.getInput();
                    }
                }
            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
