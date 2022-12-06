package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Salad {
    @NonNull
    private String Name;
    private List<Vegetable> vegetableList = new ArrayList<>();

    public Salad(String name, String vegetableName, Double calories, Double weight) {
        vegetableList.add(new Vegetable(new VegetableBase(vegetableName, calories), weight));
        this.Name = name;
    }

    public void printVegetables() {
        for (Vegetable vegetable : vegetableList)
            System.out.println("Ім'я: " + vegetable.getVegetable().getName() + "\nКалорій на 1 кг: " + vegetable.getVegetable().getCalories() +
                    "\nМаса: " + vegetable.getWeight() + " кг");
    }

    public void printVegetablesInRange(String Name, double Min, double Max) {
        for (Vegetable vegetable : vegetableList) {
            if ((vegetable.getVegetable().getCalories() >= Min) && (vegetable.getVegetable().getCalories() <= Max))
                System.out.println("Ім'я: " + vegetable.getVegetable().getName() + "\nКалорій на 1 кг: " + vegetable.getVegetable().getCalories() +
                        "\nМаса: " + vegetable.getWeight() + " кг");
        }
    }

    public void sortVegetablesByName() {

        vegetableList.sort(Comparator.comparing(vegetable -> vegetable.getVegetable().getName()));
    }

    public void sortVegetablesByCalories() {
        vegetableList.sort(Comparator.comparing(vegetable -> vegetable.getVegetable().getCalories()));
    }

    public void sortVegetablesByWeight() {
        vegetableList.sort(Comparator.comparing(vegetable -> vegetable.getVegetable().getCalories()));
    }
}
