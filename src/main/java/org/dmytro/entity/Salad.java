package org.dmytro.entity;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.dmytro.exceptions.SaladCreatingException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.dmytro.exceptions.ExceptionHandler.throwNewException;

@Data
@Log4j
public class Salad {
    private String name;
    private List<Vegetable> vegetableList;

    public Salad() {
        log.info("Створено об'єкт салату без інгредієнтів.");
        vegetableList = new LinkedList<>();
    }

    public Salad(String name, List<Vegetable> vegetableList) throws Exception {
        if (name == null || name.isBlank() || vegetableList == null || vegetableList.size() == 0) {
            throwNewException(new SaladCreatingException("Список овочей не може бути null"));
        } else {
            log.info("Створено салату '" + name + "' з овочами: " + vegetableList);
            this.name = name;
            this.vegetableList = vegetableList;
        }
    }


    public void addVegetable(final Vegetable vegetable) throws Exception {
        if (vegetable == null) {
            throwNewException(new SaladCreatingException("Додані овочі не повинні дорівнювати null"));
        } else {
            log.info("Додавання до салату овоча: " + vegetable);
            if (vegetableList.contains(vegetable)) {
                throwNewException(new SaladCreatingException("Цей овоч вже є в салаті"));
            } else {
                vegetableList.add(vegetable);
            }
        }
    }

    public void removeVegetable(final Vegetable vegetable) throws Exception {
        if (vegetable == null) {
            throwNewException(new SaladCreatingException("Додані овочі не повинні дорівнювати null"));
        } else {
            log.info("Видалення з салату овочів з назвою: " + vegetable.getName());
            vegetableList.remove(vegetable);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salad salad = (Salad) o;
        return Objects.equals(name, salad.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Salad{" +
                "name='" + name + '\'' +
                ", vegetableList=" + vegetableList +
                "}\n";
    }
}
