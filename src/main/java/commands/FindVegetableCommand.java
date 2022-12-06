package commands;

import entity.Salad;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import repository.SaladRepository;

import java.util.Scanner;

@AllArgsConstructor
@Log4j
public class FindVegetableCommand implements Command{

    private SaladRepository saladRepository;

    @Override
    public void execute(){
        log.info("Виклик FindVegetableCommand execute");
        System.out.println("―――――――――――――――――――――――――\nУкажіть назву салату: ");
        String name = new Scanner(System.in).nextLine();
        Salad salad  = null;
        boolean Check = false;
        for (Salad s: saladRepository.getSaladList()) {
            if(name.equals(s.getName())) {
                salad = s;
                Check = true;
                break;
            }
        }
        if(Check) {
            System.out.println("―――――――――――――――――――――――――");
            System.out.println("Вкажіть калорійність");
            System.out.println("Від:");
            double Min = new Scanner(System.in).nextDouble();
            System.out.println("До:");
            double Max = new Scanner(System.in).nextDouble();
            salad.printVegetablesInRange(salad.getName(), Min, Max);
        }
        else System.out.println("Такого салату не існує, спробуйте ще раз");
    }
}
