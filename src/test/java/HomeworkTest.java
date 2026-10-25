import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HomeworkTest {

    private final Random random = new Random();

    boolean isEven(int n) { return n % 2 == 0; }
    String checkAccess(int age) { return age >= 18 ? "Allowed" : "Denied"; }
    String getGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        return "F";
    }

    // 1. @Test
    @Test
    void testIsEvenRandom() {
        int n = random.nextInt(100) + 1;
        boolean result = isEven(n);
        boolean expected = (n % 2 == 0);
        if (result == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Test
    void testCheckAccessRandom() {
        int age = random.nextInt(100);
        String result = checkAccess(age);
        String expected = age >= 18 ? "Allowed" : "Denied";
        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Test
    void testGetGradeRandom() {
        int score = random.nextInt(101);
        String result = getGrade(score);
        String expected = score >= 90 ? "A" : score >= 80 ? "B" : score >= 70 ? "C" : "F";
        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    // 2. @RepeatedTest
    @RepeatedTest(5)
    void repeatedTestIsEven() {
        int n = random.nextInt(100) + 1;
        boolean result = isEven(n);
        boolean expected = (n % 2 == 0);
        if (result == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(3)
    void repeatedTestCheckAccess() {
        int age = random.nextInt(100);
        String result = checkAccess(age);
        String expected = age >= 18 ? "Allowed" : "Denied";
        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    // 3. @ParameterizedTest
    @ParameterizedTest
    @MethodSource("randomAges")
    void paramTestCheckAccess(int age) {
        String result = checkAccess(age);
        String expected = age >= 18 ? "Allowed" : "Denied";
        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @ParameterizedTest
    @MethodSource("randomScoresForGrade")
    void paramTestGetGrade(int score) {
        String result = getGrade(score);
        String expected = score >= 90 ? "A" : score >= 80 ? "B" : score >= 70 ? "C" : "F";
        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    // Генераторы данных
    static Stream<Integer> randomAges() {
        Random r = new Random();
        return IntStream.range(0, 10).mapToObj(i -> r.nextInt(100));
    }

    static Stream<Integer> randomScoresForGrade() {
        Random r = new Random();
        return IntStream.range(0, 10).mapToObj(i -> r.nextInt(101));
    }
}