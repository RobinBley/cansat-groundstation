/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.data;

import de.gt.api.relay.Receiver;
import java.util.Map;

/**
 *
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
