package org.dmytro.repository;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.dmytro.entity.Salad;

import java.util.LinkedList;
import java.util.List;


@Log4j
@Data
public class SaladRepository {
    private static List<Salad> saladList = new LinkedList<>();

    public static void add(final Salad salad) {
        log.info("Додавання салату: " + salad.getName());
        saladList.add(salad);
        log.info("Салат " + salad + " збережено");

    }

    public static void remove(final Salad salad) {
        log.info("Видалення салату: " + salad.getName());
        saladList.remove(salad);
        log.info("Салат " + salad + " видалений");
    }

    public static Salad get(final Salad salad) {
        log.info("Отримання салату: " + salad.getName());
        return saladList.stream().filter(e -> e.getName().equals(salad.getName())).findFirst().orElse(null);
    }

    public static Salad get(final String name) {
        log.info("Отримання салату: " + name);
        return saladList.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    public static List<Salad> getAll() {
        System.out.println("Отримати всі салати");
        return saladList;
    }
}
