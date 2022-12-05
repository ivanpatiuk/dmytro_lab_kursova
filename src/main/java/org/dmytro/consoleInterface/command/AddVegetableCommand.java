package org.dmytro.consoleInterface.command;

import lombok.extern.log4j.Log4j;
import org.dmytro.entity.Salad;
import org.dmytro.entity.Vegetable;
import java.util.Scanner;
import static org.dmytro.consoleInterface.ConsoleInterface.getNextDouble;
import static org.dmytro.consoleInterface.ConsoleInterface.getNextString;

@Log4j
public class AddVegetableCommand implements Command {

    private final Salad salad;

    public AddVegetableCommand(Salad salad) {
        this.salad = salad;
    }

    @Override
    public void execute(Scanner scanner) throws Exception{
        log.info("Початок виконання AddVegetableCommand execute");
        final String name = getNextString(scanner, "Введіть назву овоча: ");
        final Double weight = getNextDouble(scanner, "Введіть кількість грам овоча: ");
        final Double calories = getNextDouble(scanner, "Введіть калорійність овоча: ");
        salad.addVegetable(new Vegetable(name, weight, calories));
        log.info("Успішне виконання AddVegetableCommand execute");
    }
}
