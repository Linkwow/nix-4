package ua.nix.threadexample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

public class WriteToFile implements Runnable {

    private final Application application;
    private final File file = new File("output.txt");
    private static final Logger logger = LoggerFactory.getLogger(WriteToFile.class);

    public WriteToFile(Application application) throws IOException {
        this.application = application;
    }

    @Override
    public void run() {
        while (!application.isStop()) {
            String input = application.getInput();
            try {
                sleep(1000);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
                logger.error("Error : ", interruptedException);
                throw new RuntimeException(interruptedException);
            }
            String toWrite = application.getInput();
            if (input.equals(toWrite)) {
                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write(toWrite);
                } catch (IOException ioException) {
                    logger.error("Error : ", ioException);
                    throw new UncheckedIOException(ioException);
                }
            }
        }
    }
}
