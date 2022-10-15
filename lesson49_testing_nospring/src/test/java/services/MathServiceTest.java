package services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MathServiceTest {
    @Test
    public void testMath() {
        int intOne = 20;
        int intTwo = 5;
        int expectedResult = 4;
        MathService mathService = new MathService();
        int division = mathService.division(intOne, intTwo);

        Assertions.assertEquals(expectedResult,division);
    }

}
