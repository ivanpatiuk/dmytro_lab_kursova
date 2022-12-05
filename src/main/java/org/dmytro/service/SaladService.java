package org.dmytro.service;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.dmytro.entity.Salad;
import org.dmytro.exceptions.SaladCreatingException;
import org.dmytro.repository.SaladRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.dmytro.consoleInterface.ConsoleInterface.getNextDouble;
import static org.dmytro.consoleInterface.ConsoleInterface.getNextString;
import static org.dmytro.exceptions.ExceptionHandler.throwNewException;

@Log4j
public class SaladService {
    public static void saveSalad(Salad salad) throws Exception {
        log.info("Збереження салату");
        if (salad != null && SaladRepository.get(salad) == null) {
            SaladRepository.add(salad);
        } else {
            throwNewException(new SaladCreatingException("Салат не може бути доданий до репозиторію."));
        }
    }

    public static void removeSalad(Salad salad) throws Exception {
        log.info("Видалення салату");
        if (salad != null) {
            SaladRepository.remove(salad);
        } else {
            throwNewException(new SaladCreatingException("Салат не може бути видалний з репозиторію."));
        }
    }

    public static void printSalads() {
        log.info("Виведення всіх салатів");
        System.out.println(SaladRepository.getAll());
    }

    public static void printSalad(Scanner scanner) {
        log.info("Виведення салату");
        String name = getNextString(scanner, "Введіть назву салату для виводу: ");
        System.out.println(SaladRepository.get(name));
    }

    public static void loadSalads() throws Exception {
        {
            log.info("Завантаження салатів з файлу");
            // Всі салати для ініціалізації зберігаються в файлі salads.txt в папці проекту
            File file = new File("salads.txt");
            Scanner sc = new Scanner(file);
            List<String> list = new ArrayList<>();
            // Деліметр \\Z вказує на кінець файлу
            sc.useDelimiter("\\Z");
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
            Gson gson = new Gson();
            for (String s : list) {
                saveSalad(gson.fromJson(s, Salad.class));
            }
        }
    }

    public static void printSaladInRange(Scanner scanner) throws Exception {
        log.info("Виведення овочів, які задовільняють калорійності");
        final String name = getNextString(scanner, "Введіть назву салату: ");
        if (name.isBlank()) {
            throwNewException(new SaladCreatingException("Назва салату не може бути пустою"));
        }
        final Double min = getNextDouble(scanner, "Введіть мінімальну калорійність (більше нуля): ");
        if (Objects.requireNonNull(min) < 0) {
            throwNewException(new SaladCreatingException("Мінімальна калорійність овочей повинна бути більше 0"));
        }
        final Double max = getNextDouble(scanner, "Введіть максимальну калорійність (більше нуля): ");
        if (Objects.requireNonNull(max) < 0) {
            throwNewException(new SaladCreatingException("Максимальна калорійність овочей повинна бути більше 0"));
        }
        SaladRepository
                .get(name)
                .getVegetableList()
                .forEach(vegetable -> {
                    if (vegetable.getCalories() >= min && vegetable.getCalories() <= max) {
                        System.out.println(vegetable);
                    }
                });
    }
}
