package org.dmytro.consoleInterface.command;

import lombok.extern.log4j.Log4j;
import org.dmytro.entity.Salad;
import org.dmytro.entity.Vegetable;

import java.util.Scanner;

import static org.dmytro.consoleInterface.ConsoleInterface.getNextString;

@Log4j
public class SaladNameCommand implements Command {

    private final Salad salad;

    public SaladNameCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute(Scanner scanner)  {
        log.info("Початок виконання SaladNameCommand execute");
        final String name = getNextString(scanner, "Введіть назву салату: ");
        if (!name.isBlank()) {
            salad.setName(name);
        }
        log.info("Успішне виконання SaladNameCommand execute");
    }
}
