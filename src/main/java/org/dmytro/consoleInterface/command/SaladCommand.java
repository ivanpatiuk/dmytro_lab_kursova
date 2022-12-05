package org.dmytro.consoleInterface.command;

import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class SaladCommand {
    private AddVegetableCommand addVegetableCommand;
    private RemoveVegetableCommand removeVegetableCommand;
    private SaladNameCommand saladNameCommand;

    public void addVegetable(Scanner scanner) throws Exception{
        addVegetableCommand.execute(scanner);
    }

    public void removeVegetable(Scanner scanner) throws Exception {
        removeVegetableCommand.execute(scanner);
    }

    public void addName(Scanner scanner) throws Exception {
        saladNameCommand.execute(scanner);
    }
}
