import commands.*;
import repository.SaladRepository;
import repository.VegetableRepository;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final SaladRepository saladRepository = new SaladRepository();
        final VegetableRepository vegetableRepository = new VegetableRepository();
        final Scanner scanner = new Scanner(System.in);

        final CommandSwitch commandSwitch = new CommandSwitch(
                new ChangeSaladCommand(saladRepository, vegetableRepository),
                new CountCaloriesCommand(saladRepository, vegetableRepository),
                new CreateSaladCommand(saladRepository),
                new CreateVegetableCommand(vegetableRepository),
                new FindVegetableCommand(saladRepository),
                new SortVegetableCommand(saladRepository, vegetableRepository));


        while (true) {
            System.out.println("""
                ―――――――――――――――――――――――――
                | Оберіть опцію меню:                   |
                | (1) Добавити овочі                    |
                | (2) Знайти овочі                      |
                | (3) Сортувати овочі                   |
                | (4) Створити салат                    |
                | (5) Підрахувати калорійність          |
                | (6) Змінити склад салату              |
                | (7) Завершити роботу програми         |
                ――――――――――――――――――――――――――""");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1 -> commandSwitch.createVegetable();
                case 2 -> commandSwitch.findVegetable();
                case 3 -> commandSwitch.sortVegetable();
                case 4 -> commandSwitch.createSalad();
                case 5 -> commandSwitch.countCalories();
                case 6 -> commandSwitch.changeSalad();
                case 0 -> {
                    System.out.println("Завершення роботи програми");
                    return;
                }
            }
        }
    }
}