package org.dmytro.consoleInterface.command;

import lombok.extern.log4j.Log4j;
import org.dmytro.entity.Salad;
import org.dmytro.entity.Vegetable;

import java.util.Scanner;

import static org.dmytro.consoleInterface.ConsoleInterface.getNextString;

@Log4j
public class RemoveVegetableCommand implements Command {

    private final Salad salad;

    public RemoveVegetableCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute(Scanner scanner) throws Exception {
        log.info("Початок виконання RemoveVegetableCommand execute");
        final String name = getNextString(scanner, "Введіть назву овоча: ");
        if (!name.isBlank()) {
            salad.removeVegetable(new Vegetable(name));
        }
        log.info("Успішне виконання RemoveVegetableCommand execute");
    }
}