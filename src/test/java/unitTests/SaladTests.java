package unitTests;

import com.google.gson.Gson;
import org.dmytro.entity.Salad;
import org.dmytro.entity.Vegetable;
import org.dmytro.repository.SaladRepository;
import org.dmytro.service.SaladService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SaladTests {

    @Test(dataProvider = "shouldThrowExceptionDataProvider")
    void shouldThrowExceptionTest(String name, List<Vegetable> vegetableList) {
        Assert.assertThrows(() -> new Salad(name, vegetableList));
    }

    @Test
    void shouldProcessWhenAddVegetable() throws Exception {
        Salad salad = new Salad("Український", new ArrayList<>(List.of(new Vegetable("Огірок", 30.0, 20.5))));
        Vegetable vegetable = new Vegetable("Капуста", 150.0, 30.0);
        salad.addVegetable(vegetable);
        Assert.assertTrue(salad.getVegetableList().contains(vegetable));
    }

    @Test(dataProvider = "shouldThrowExceptionWhenAddVegetableDataProvider")
    void shouldThrowExceptionWhenAddVegetable(Vegetable vegetable) throws Exception {
        Salad salad = new Salad("Український", new ArrayList<>(List.of(new Vegetable("Огірок", 30.0, 20.5))));
        Assert.assertThrows(() -> salad.addVegetable(vegetable));
    }

    @Test
    void shouldProcessWhenRemoveVegetable() throws Exception {
        Salad salad = new Salad("Український", new ArrayList<>(List.of(new Vegetable("Огірок", 30.0, 20.5))));
        Vegetable vegetable1 = new Vegetable("Огірок");
        Assert.assertTrue(salad.getVegetableList().contains(vegetable1));
        salad.removeVegetable(new Vegetable("Огірок"));
        Assert.assertFalse(salad.getVegetableList().contains(vegetable1));
    }

    @Test
    void shouldThrowExceptionWhenRemoveVegetable() throws Exception {
        Salad salad = new Salad("Український", new ArrayList<>(List.of(new Vegetable("Огірок", 30.0, 20.5))));
        Assert.assertThrows(() -> salad.removeVegetable(null));
    }

    @Test
    void shouldProcessWhenRemoveSalad() throws Exception {
        Salad salad = new Salad("Український", new ArrayList<>(List.of(new Vegetable("Огірок", 30.0, 20.5))));
        SaladRepository.add(salad);
        Assert.assertNotNull(SaladRepository.get(salad));
        SaladRepository.remove(salad);
        Assert.assertNull(SaladRepository.get(salad));
    }

    @Test
    void shouldProcessWhenPrintSalads() throws Exception {
        Salad salad = new Salad("Український", new ArrayList<>(List.of(new Vegetable("Огірок", 30.0, 20.5))));
        SaladRepository.add(salad);
        SaladService.printSalads();
        // Якщо метод вище не викинув помилку, то метод нижче спрацює
        Assert.assertTrue(true);
    }

    @Test
    void shouldThrowExceptionWhenRemoveSalad() throws Exception {
        Salad salad = new Salad("Український", new ArrayList<>(List.of(new Vegetable("Огірок", 30.0, 20.5))));
        SaladRepository.add(salad);
        Assert.assertThrows(() -> SaladService.removeSalad(null));
    }


    @DataProvider(name = "shouldThrowExceptionDataProvider")
    Object[][] shouldThrowExceptionDataProvider() {
        return new Object[][]{
                {null, null},
                {null, List.of(new Vegetable("Огірок", 30.0, 20.5))},
                {null, Collections.emptyList()},
                {"Український", null},
                {"", null},
                {"Український", Collections.emptyList()},
        };
    }

    @DataProvider(name = "shouldThrowExceptionWhenAddVegetableDataProvider")
    Object[][] shouldThrowExceptionWhenAddVegetableDataProvider() {
        return new Object[][]{
                {new Vegetable("Огірок", 150.0, 20.5)},
                {null}
        };
    }
}
