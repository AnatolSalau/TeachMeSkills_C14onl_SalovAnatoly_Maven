package services;

import org.junit.Before;
import org.junit.jupiter.api.*;

public class MathServiceTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll()");
    }
    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach()");
    }
    @AfterEach
    public void afterEach() {
        System.out.println("afterEach()");
    }
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
