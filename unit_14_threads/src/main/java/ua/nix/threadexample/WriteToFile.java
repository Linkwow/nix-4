package ua.nix.threadexample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteToFile implements Runnable {

    private final Application application;
    private final File file = new File("output.txt");
    private static final Logger logger = LoggerFactory.getLogger(WriteToFile.class);

    public WriteToFile(Application application) throws IOException {
        this.application = application;
    }

    @Override
    public void run() {
        synchronized (application) {
            while (!application.isStop()) {
                String input = application.getInput();
                try {
                    application.wait(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                logger.debug("input hashCode" + input.hashCode());
                logger.debug("application hashcode" + application.getInput().hashCode());
                if (input.hashCode() != application.getInput().hashCode()) {
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write(application.getInput());
                    } catch (IOException ioException) {
                        throw new UncheckedIOException(ioException);
                    }
                }
            }
        }
    }
}
