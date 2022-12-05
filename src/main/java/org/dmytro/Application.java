package org.dmytro;

import lombok.extern.log4j.Log4j;
import org.dmytro.consoleInterface.ConsoleInterface;

@Log4j
public class Application {
    public static void main(String[] args) throws Exception {
        log.info("Початок виконання програми");
        ConsoleInterface consoleInterface = new ConsoleInterface();
        consoleInterface.start();
    }
}