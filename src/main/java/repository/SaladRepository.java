package repository;

import entity.Salad;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SaladRepository {
    private List<Salad> saladList = new ArrayList<>();

    public void printSalads(){
        for (Salad salad: saladList)
        {
            System.out.println("Ім'я: " + salad.getName());
        }
    }
    public void addSalad(Salad salad){
        saladList.add(salad);
    }
    public void removeSalad(Salad salad){
        saladList.remove(salad);
    }
}
