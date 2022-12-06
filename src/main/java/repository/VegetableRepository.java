package repository;

import entity.VegetableBase;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;

@Data
public class VegetableRepository {

    private ArrayList<VegetableBase> vegetables = new ArrayList<>();

    public void printVegetables(String vegetableName) {
        for (VegetableBase vegetable : vegetables) {
            if (vegetableName.equals(vegetable.getName()))
                System.out.println("Ім'я: " + vegetable.getName() + "\nКалорійність: " + vegetable.getCalories());
        }
    }

    public void printVegetables() {
        for (VegetableBase vegetable : vegetables)
            System.out.println("Ім'я: " + vegetable.getName() + "\nКалорійність: " + vegetable.getCalories());
    }

    public void addVegetable(VegetableBase vegetable) {
        vegetables.add(vegetable);
    }

    public void removeVegetable(VegetableBase vegetable) {
        vegetables.remove(vegetable);
    }

    public void sortByName() {
        vegetables.sort(Comparator.comparing(VegetableBase::getName));
    }

    public void sortVegetablesByCalories() {
        vegetables.sort(Comparator.comparing(VegetableBase::getCalories));
    }
}
