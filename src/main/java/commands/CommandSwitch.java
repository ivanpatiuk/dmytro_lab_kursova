package commands;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandSwitch {
    Command changeSalad;
    Command countCalories;
    Command createSalad;
    Command createVegetable;
    Command findVegetable;
    Command sortVegetable;

    public void changeSalad() {
        changeSalad.execute();
    }

    public void countCalories() {
        countCalories.execute();
    }

    public void createSalad() {
        createSalad.execute();
    }

    public void createVegetable() {
        createVegetable.execute();
    }

    public void findVegetable() {
        findVegetable.execute();
    }

    public void sortVegetable() {
        sortVegetable.execute();
    }
}
