package commands;


import entity.VegetableBase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import repository.VegetableRepository;

import java.util.Scanner;

@AllArgsConstructor
@Log4j
public class CreateVegetableCommand implements Command{

    private VegetableRepository vegetableRepository;

    @Override
    public void execute(){
        log.info("Виклик CreateSaladCommand execute");
        System.out.println("―――――――――――――――――――――――――");
        System.out.println("Укажіть ім'я овоча: ");
        String VegetableName = new Scanner(System.in).nextLine();
        System.out.println("Укажіть калорійність на 1 кг: ");
        double CaloriesPerKg = new Scanner(System.in).nextDouble();
        VegetableBase NewVegetable = new VegetableBase(VegetableName, CaloriesPerKg);
        vegetableRepository.addVegetable(NewVegetable);
    }
}
