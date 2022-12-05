package org.dmytro.consoleInterface;

import lombok.extern.log4j.Log4j;
import org.dmytro.consoleInterface.command.AddVegetableCommand;
import org.dmytro.consoleInterface.command.RemoveVegetableCommand;
import org.dmytro.consoleInterface.command.SaladCommand;
import org.dmytro.consoleInterface.command.SaladNameCommand;
import org.dmytro.entity.Salad;
import org.dmytro.exceptions.SaladCreatingException;
import org.dmytro.service.SaladService;

import java.util.Scanner;

import static org.dmytro.exceptions.ExceptionHandler.throwNewException;

@Log4j
public class ConsoleInterface {

    public ConsoleInterface() {
        log.info("Створення ConsoleInterface");
    }

    public static void printMenu() {
        log.info("Виведення меню в консоль");
        System.out.println("""
                -------------------------------------------------------
                | (0) -> завершити введення, вийти з програми         |
                | (1) -> почати створення салату                      |
                | (2) -> зберегти створений салату в репозиторій      |
                | (3) -> видалити створений салат з репозиторію       |
                | (4) -> вивести салат за назвою                      |
                | (5) -> вивести список всіх салатів                  |
                | (6) -> завантажити салати з файлу                   |
                | (7) -> вивести овочі зі складу салату, що відпові-  |
                |        дають калорійності                           |
                | (8) -> довідка                                      |
                -------------------------------------------------------""");
    }

    public static void printCreatingSaladMenu() {
        log.info("Виведення меню в консоль");
        System.out.println("""
                -------------------------------------------------------
                | (0) -> завершити введення, вийти з програми,        |
                |        скасувати процес створення салату            |
                | (1) -> ввести назву для салату                      |
                | (2) -> додати овочі до салату                       |
                | (3) -> видалити овочі з салату                      |
                | (4) -> завершити створення салату                   |
                -------------------------------------------------------""");
    }

    public static void printHint() {
        log.info("Виведення довідки в консоль");
        System.out.println("""
                -------------------------------------------------------
                | Перед збереженням/видаленням салату в репозиторій,  |
                | його спочатку потрібно створити. Для отримання са-  |
                | лату з репозиторію, в ньому повинні перед цим ці    |
                | салати бути додані.                                 |
                | Для видалення салату достатньо створити салат лише. |
                | з назвою.                                           |
                -------------------------------------------------------""");
    }

    public Salad createSalad(Scanner scanner) throws Exception {
        log.info("Початок створення салату");
        Integer input;
        final Salad salad = new Salad();
        log.info("Створення команд");
        final AddVegetableCommand addVegetableCommand = new AddVegetableCommand(salad);
        final RemoveVegetableCommand removeVegetableCommand = new RemoveVegetableCommand(salad);
        final SaladNameCommand saladNameCommand = new SaladNameCommand(salad);
        final SaladCommand saladCommand = new SaladCommand(addVegetableCommand, removeVegetableCommand, saladNameCommand);

        do {
            printCreatingSaladMenu();
            input = getNextInt(scanner, "Введіть опцію: ");
            if (input == null) {
                continue;
            }
            switch (input) {
                case 1 -> saladCommand.addName(scanner);
                case 2 -> saladCommand.addVegetable(scanner);
                case 3 -> saladCommand.removeVegetable(scanner);
                case 4 -> {
                    return salad;
                }
            }
        } while (input != null && input != 0);
        return null;
    }

    public static Integer getNextInt(final Scanner scanner, final String string) throws Exception {
        log.info("Введення цілого числа для опції меню");
        System.out.print(string);
        final String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throwNewException(new SaladCreatingException("Строка не може бути приведена до Integer"));
            return null;
        }
    }

    public static Double getNextDouble(final Scanner scanner, final String string) throws Exception {
        log.info("Введення десяткового числа для опції меню");
        System.out.print(string);
        final String input = scanner.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            throwNewException(new SaladCreatingException("Строка не може бути приведена до Double"));
            return null;
        }
    }

    public static String getNextString(final Scanner scanner, final String string) {
        log.info("Введення строки");
        System.out.print(string);
        return scanner.nextLine();
    }

    public void start() throws Exception {
        log.info("Початок взаємодії з інтерфейсом");
        try {
            final Scanner scanner = new Scanner(System.in);
            Integer input;

            Salad salad = null;
            do {
                printMenu();
                input = getNextInt(scanner, "Введіть опцію: ");
                if (input == null) {
                    continue;
                }
                switch (input) {
                    case 1 -> salad = createSalad(scanner);
                    case 2 -> SaladService.saveSalad(salad);
                    case 3 -> SaladService.removeSalad(salad);
                    case 4 -> SaladService.printSalad(scanner);
                    case 5 -> SaladService.printSalads();
                    case 6 -> SaladService.loadSalads();
                    case 7 -> SaladService.printSaladInRange(scanner);
                    case 8 -> printHint();
                }
            } while (input != null && input != 0);
            log.info("Вихід з меню");
        } catch (Exception e){
            throwNewException(e);
        }
    }
}
