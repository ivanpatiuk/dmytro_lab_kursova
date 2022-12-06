package commands;

import consoleInterface.ConsoleInterface;
import entity.Salad;
import entity.VegetableBase;
import entity.Vegetable;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import repository.SaladRepository;
import repository.VegetableRepository;

import java.util.Scanner;

@AllArgsConstructor
@Log4j
public class ChangeSaladCommand implements Command {
    private SaladRepository saladRepository;
    private VegetableRepository vegetableRepository;

    @Override
    public void execute() {
        log.info("Виклик ChangeSaladCommand execute");
        while (true) {
            System.out.println("""
                    ―――――――――――――――――――――
                    |  Виберіть опцію меню           |
                    | (1) Показати всі салати        |
                    | (2) Змінити склад салату       |
                    | (3) Видалити                   |
                    | (0) Попереднє меню             |
                    ―――――――――――――――――――――""");
            int choice = new Scanner(System.in).nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case (1) -> saladRepository.printSalads();
                case (2) -> {
                    System.out.println("――――――――――――――――――――――\nВкажіть назву салату: ");
                    String name = new Scanner(System.in).nextLine();
                    Salad saladToChange = null;
                    boolean checkPoint = false;
                    for (Salad salad : saladRepository.getSaladList()) {
                        if (name.equals(salad.getName())) {
                            saladToChange = salad;
                            checkPoint = true;
                            break;
                        }
                    }
                    if (checkPoint) {
                        while (true) {
                            choice = ConsoleInterface.changeSaladOption();
                            if (choice == 0) {
                                break;
                            }
                            String vegetableName = null;
                            switch (choice) {
                                case (1) -> {
                                    System.out.println("Укажіть назву овоча:");
                                    vegetableName = new Scanner(System.in).nextLine();
                                    checkPoint = false;
                                    for (VegetableBase bas : vegetableRepository.getVegetables()) {
                                        if (vegetableName.equals(bas.getName())) {
                                            System.out.println("Укажіть масу овоча: ");
                                            double weight = new Scanner(System.in).nextDouble();
                                            saladToChange.getVegetableList().add(new Vegetable(bas, weight));
                                            checkPoint = true;
                                            break;
                                        }
                                    }
                                    if (!checkPoint) {
                                        System.out.println("Такого овоча немає! Спробуйте ще раз");
                                    }
                                }
                                case (2) -> vegetableRepository.printVegetables();
                                case (3) -> {
                                    System.out.println("Укажіть назву овоча");
                                    vegetableName = new Scanner(System.in).nextLine();
                                    checkPoint = false;
                                    for (Vegetable vegetable : saladToChange.getVegetableList()) {
                                        if (vegetable.getVegetable().getName().equals(vegetableName)) {
                                            System.out.println("Укажіть масу овоча: ");
                                            double vegetable_weight = new Scanner(System.in).nextDouble();
                                            vegetable.setWeight(vegetable_weight);
                                            checkPoint = true;
                                            break;
                                        }
                                    }
                                    if (!checkPoint) {
                                        System.out.println("У салаті немає такого овоча, спробуйте ще раз");
                                    }
                                }
                                case (4) -> {
                                    System.out.println("Укажіть назву овоча:");
                                    vegetableName = new Scanner(System.in).nextLine();
                                    checkPoint = false;
                                    for (Vegetable veg : saladToChange.getVegetableList()) {
                                        if (veg.getVegetable().getName().equals(vegetableName)) {
                                            saladToChange.getVegetableList().remove(veg);
                                            checkPoint = true;
                                            break;
                                        }
                                    }
                                    if (!checkPoint) {
                                        System.out.println("У салаті немає такого овоча, спробуйте ще раз");
                                    }
                                }
                                case (5) -> saladToChange.printVegetables();
                                default -> System.out.println("Ви ввели неправильны дані");
                            }
                        }
                    } else System.out.println("Такого салату не існує, спробуйте ще раз");
                }
                case (3) -> {
                    System.out.println("―――――――――――――――――――――――――\nУкажіть назву салату: ");
                    String saladName = new Scanner(System.in).nextLine();
                    boolean checkPoint = false;
                    for (Salad salad : saladRepository.getSaladList()) {
                        if (saladName.equals(salad.getName())) {
                            saladRepository.getSaladList().remove(salad);
                            checkPoint = true;
                            break;
                        }
                    }
                    if (!checkPoint)
                        System.out.println("Такого салату не існує, спробуйте ще раз");
                }
                default -> System.out.println("Ви ввели неправильны дані");
            }
        }
    }
}
