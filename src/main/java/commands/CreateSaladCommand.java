package commands;

import entity.Salad;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import repository.SaladRepository;

import java.util.Scanner;

@AllArgsConstructor
@Log4j
public class CreateSaladCommand implements Command{

    private SaladRepository saladRepository;

    @Override
    public void execute() {
        log.info("Виклик CreateSaladCommand execute");
        Scanner scanner = new Scanner(System.in);
        Salad salad = new Salad();
        System.out.println("―――――――――――――――――――――――――\nУкажіть назву салату: ");
        salad.setName(scanner.nextLine());
        saladRepository.addSalad(salad);
    }
}
