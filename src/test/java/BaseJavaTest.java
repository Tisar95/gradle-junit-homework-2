import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BaseJavaTest {

    private final Random random = new Random();

    boolean isEven(int n) { return n % 2 == 0; }
    String checkAccess(int age) { return age >= 18 ? "Allowed" : "Denied"; }
    String getGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        return "F";
    }

    @Test
    void testIsEven() {
        System.out.println("========================Test method start");
        int n = random.nextInt(100) + 1; // 1 to 100
        boolean result = isEven(n);
        System.out.println("isEven(" + n + ") = " + result);
        System.out.println("Test method end");
        System.out.println("========================");
    }

    @Test
    void testCheckAccess() {
        System.out.println("========================Test method start");
        for (int i = 0; i < 20; i++) {
            int age = random.nextInt(100); // 0 to 99
            String result = checkAccess(age);
            System.out.println("checkAccess(" + age + ") = " + result);
        }
        System.out.println("Test method end");
        System.out.println("========================");
    }

    // Параметризованный тест с массивом случайных чисел
    @ParameterizedTest
    @MethodSource("randomScores")
    void testGetGrade(int score) {
        System.out.println("========================Test method start");
        String result = getGrade(score);
        System.out.println("getGrade(" + score + ") = " + result);
        System.out.println("Test method end");
        System.out.println("========================");
    }

    static Stream<Integer> randomScores() {
        Random r = new Random();
        return IntStream.range(0, 10).mapToObj(i -> r.nextInt(101)); // 0 to 100
    }
}