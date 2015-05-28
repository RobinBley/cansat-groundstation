package de.gt.core.data;

import de.gt.api.relay.Receiver;
import java.util.Map;

/**
 * Printet die empfangenen Daten in die Konsole
 * @author Robin
 */
public class ConsoleOutput implements Receiver {

    @Override
    public void receive(Map<String, Double> datum) {
        datum.entrySet().stream()
                .map(e -> String.format("%s: %s", e.getKey(), e.getValue()))
                .forEach(System.out::println);
    }
}
