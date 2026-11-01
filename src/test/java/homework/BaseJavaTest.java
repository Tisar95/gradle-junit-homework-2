package homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BaseJavaTest {

    private final Random random = new Random();

    @Test
    void testIsEven() {
        System.out.println("========================Test method start");
        int n = random.nextInt(100) + 1; // 1..100
        boolean result = Main.isEven(n);
        System.out.println("isEven(" + n + ") = " + result);
        System.out.println("Test method end");
        System.out.println("========================");
    }

    @Test
    void testCheckAccess() {
        System.out.println("========================Test method start");
        for (int i = 0; i < 20; i++) {
            int age = random.nextInt(100); // 0..99
            String result = Main.checkAccess(age);
            System.out.println("checkAccess(" + age + ") = " + result);
        }
        System.out.println("Test method end");
        System.out.println("========================");
    }

    @ParameterizedTest
    @MethodSource("randomScores")
    void testGetGrade(int score) {
        System.out.println("========================Test method start");
        String result = Main.getGrade(score);
        System.out.println("getGrade(" + score + ") = " + result);
        System.out.println("Test method end");
        System.out.println("========================");
    }

    static Stream<Integer> randomScores() {
        Random r = new Random();
        return IntStream.range(0, 10).mapToObj(i -> r.nextInt(101)); // 0..100
    }
}