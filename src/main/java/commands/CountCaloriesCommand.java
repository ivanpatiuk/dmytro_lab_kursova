package commands;

import entity.Salad;
import entity.Vegetable;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import repository.SaladRepository;
import repository.VegetableRepository;

import java.util.Scanner;

@AllArgsConstructor
@Log4j
public class CountCaloriesCommand implements Command{

    private SaladRepository saladRepository;
    private VegetableRepository vegetableRepository;

    @Override
    public void execute(){
        log.info("Виклик CountCaloriesCommand execute");
        while(true)
        {
            System.out.println("""
                    ――――――――――――――――――――
                    | Виберіть опцію меню          |
                    | (1) Показати всі салати      |
                    | (2) Підрахувати калорії      |
                    | (0) Попереднє меню           |
                    ――――――――――――――――――――""");
            int choice = new Scanner(System.in).nextInt();

            if(choice == 0) break;

            switch (choice) {
                case (1) -> saladRepository.printSalads();
                case (2) -> {
                    System.out.println("―――――――――――――――――――――――――\nУкажіть ім'я салату: ");
                    String SaladName =new Scanner(System.in).nextLine();
                    boolean Check = false;
                    double calories = 0;
                    for (Salad salad : saladRepository.getSaladList()) {
                        if (SaladName.equals(salad.getName())) {
                            for (Vegetable vegetable : salad.getVegetableList()) {
                                calories += vegetable.getWeight() * vegetable.getVegetable().getCalories();
                            }
                            Check = true;
                            break;
                        }
                    }
                    if (!Check)
                        System.out.println("Такого салату не існує, спробуйте ще раз");
                    else
                        System.out.println("\tКалорійність: " + calories + " ккал/кг\n");
                }
                default -> System.out.println("Ви ввели неправильні.");
            }
        }
    }
}
