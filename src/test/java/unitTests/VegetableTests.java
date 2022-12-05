package unitTests;

import org.dmytro.entity.Vegetable;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VegetableTests {

    @Test(dataProvider = "twoVegetablesShouldBeEqualDataProvider" )
    void twoVegetablesShouldBeEqualTest(Vegetable vegetable1, Vegetable vegetable2){
        Assert.assertEquals(vegetable1,vegetable2);
    }

    @DataProvider(name = "twoVegetablesShouldBeEqualDataProvider")
    Object[][] twoVegetablesShouldBeEqualDataProvider(){
        return new Object[][]{
                {new Vegetable("Огірок", 50.0, 30.5), new Vegetable("Огірок", 100.0, 30.5)},
                {new Vegetable("Огірок"), new Vegetable("Огірок", 100.0, 30.5)}
        };
    }
}
