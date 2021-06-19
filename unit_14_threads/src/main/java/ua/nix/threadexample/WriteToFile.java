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
        while (!application.isStop()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                String input = application.getInput();
                application.wait(1000);
                logger.debug("input hashCode" + input.hashCode());
                logger.debug("application hashcode" + application.getInput().hashCode());
                if (input.hashCode() != application.getInput().hashCode()) {
                    fileWriter.write(application.getInput());
                }
            } catch(InterruptedException interruptedException){
                Thread.currentThread().interrupt();
                throw new RuntimeException(interruptedException);
            } catch(IOException ioException){
                throw new UncheckedIOException(ioException);
            }
        }
    }
}
