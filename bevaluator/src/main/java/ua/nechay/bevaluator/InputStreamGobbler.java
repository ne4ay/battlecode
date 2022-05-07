package ua.nechay.bevaluator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class InputStreamGobbler implements Runnable {

    private InputStream inputStream;
    private Consumer<String> consumer;

    public InputStreamGobbler(InputStream inputStream, Consumer<String> consumer) {
        this.inputStream = inputStream;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        new BufferedReader(new InputStreamReader(inputStream)).lines()
            .forEach(consumer);
    }
}
