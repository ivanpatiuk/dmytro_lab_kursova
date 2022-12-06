package commands;

import entity.Salad;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import repository.SaladRepository;
import repository.VegetableRepository;

import java.util.Scanner;

@AllArgsConstructor
@Log4j
public class SortVegetableCommand implements Command {

    private SaladRepository saladRepository;
    private VegetableRepository vegetableRepository;

    @Override
    public void execute() {
        log.info("Виклик SortVegetableCommand execute");
        while (true) {
            System.out.println("""
                    ―――――――――――――――――――――――――
                    |  Виберіть опцію меню                 |
                    | (1) Показати всі салати              |
                    | (2) Показати всі овочі               |
                    | (3) Показати вміст салату            |
                    | (4) Відсортувати всі овочі           |
                    | (5) Відсортувати овочі в салаті      |
                    | (0) Попереднє меню                   |
                    ―――――――――――――――――――――――――""");
            int choice = new Scanner(System.in).nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case (1) -> saladRepository.printSalads();
                case (2) -> vegetableRepository.printVegetables();
                case (3) -> {
                    System.out.println("―――――――――――――――――――――――――");
                    System.out.println("Укажіть ім'я салату: ");
                    String saladName = new Scanner(System.in).nextLine();
                    boolean Check = false;
                    for (Salad salad : saladRepository.getSaladList()) {
                        if (saladName.equals(salad.getName())) {
                            salad.printVegetables();
                            Check = true;
                            break;
                        }
                    }
                    if (!Check) System.out.println("Такого салату не існує! Спробуйте ще раз");
                }
                case (4) -> {
                    System.out.println("""
                            ―――――――――――――――――――――
                            | Оберіть опцію сортування      |
                            | (1) За назвою                 |
                            | (2) За калорійністю           |
                            ―――――――――――――――――――――""");
                    int sort = new Scanner(System.in).nextInt();
                    switch (sort) {
                        case (1) -> {
                            vegetableRepository.sortByName();
                            for (Salad salad : saladRepository.getSaladList()) {
                                salad.sortVegetablesByName();
                            }
                        }
                        case (2) -> {
                            vegetableRepository.sortVegetablesByCalories();
                            for (Salad salad : saladRepository.getSaladList()) {
                                salad.sortVegetablesByCalories();
                            }
                        }
                        default -> System.out.println("Ви ввели некоректні дані!");
                    }
                }
                case (5) -> {
                    System.out.println("―――――――――――――――――――――――――");
                    System.out.println("Укажіть назву салату: ");
                    String name = new Scanner(System.in).nextLine();
                    Salad sortedSalad = null;
                    boolean checkpoint = false;
                    for (Salad salad : saladRepository.getSaladList()) {
                        if (name.equals(salad.getName())) {
                            sortedSalad = salad;
                            checkpoint = true;
                            break;
                        }
                    }
                    if (checkpoint) {
                        System.out.println("""
                                ――――――――――――――――――――
                                | Виберіть опцію сортування    |
                                | (1) За назвою                |
                                | (2) За калорійністю          |
                                | (3) За масою                 |
                                ――――――――――――――――――――""");
                        int sortChoice = new Scanner(System.in).nextInt();
                        switch (sortChoice) {
                            case (1) -> sortedSalad.sortVegetablesByName();
                            case (2) -> sortedSalad.sortVegetablesByCalories();
                            case (3) -> sortedSalad.sortVegetablesByWeight();
                            default -> System.out.println("Ви ввели некоректні дані!");
                        }
                    } else System.out.println("Такого салату не існує! Спробуйте ще раз");
                }
                default -> System.out.println("Ви ввели некоректні дані!");

            }
        }
    }
}
