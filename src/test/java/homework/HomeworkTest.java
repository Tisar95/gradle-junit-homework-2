package homework;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static homework.Main.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@Tag("homework")
public class HomeworkTest {

    private final Random random = new Random();

    @RepeatedTest(10)
    void test1_isEven() {
        int n = random.nextInt(100) + 1;
        boolean actual = isEven(n);
        boolean expected = (n % 2 == 0);
        assertThat(actual)
                .as("isEven(%d): expected %s, but was %s", n, expected, actual)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    void test2_checkAccess() {
        int age = random.nextInt(100);
        String actual = checkAccess(age);
        String expected = age > 18 ? "Allowed" : "Denied";
        assertThat(actual)
                .as("checkAccess(%d): expected \"%s\", but was \"%s\"", age, expected, actual)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    void test3_isPositive() {
        int n = random.nextInt(200) - 100;
        boolean actual = isPositive(n);
        boolean expected = n >= 0;
        assertThat(actual)
                .as("isPositive(%d): expected %s, but was %s", n, expected, actual)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    void test4_getGrade() {
        int score = random.nextInt(101);
        String actual = getGrade(score);
        String expected = score <= 20 ? "E" : score <= 40 ? "D" : score <= 60 ? "C" : score <= 80 ? "B" : "A";
        assertThat(actual)
                .as("getGrade(%d): expected \"%s\", but was \"%s\"", score, expected, actual)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    void test5_blastOff() {
        int start = random.nextInt(10) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i >= 1; i--) sb.append(i).append(" ");
        sb.append("Поехали!");
        String expected = sb.toString();
        String actual = blastOff(start);
        assertThat(actual)
                .as("blastOff(%d): expected \"%s\", but was \"%s\"", start, expected, actual)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    void test6_sumToN() {
        int n = random.nextInt(100) + 1;
        int expected = n * (n + 1) / 2;
        int actual = sumToN(n);
        assertThat(actual)
                .as("sumToN(%d): expected %d, but was %d", n, expected, actual)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    void test7_hasBug() {
        boolean expected = random.nextBoolean();
        String[] messages = expected ? new String[]{"ok", "BUG"} : new String[]{"ok", "info"};
        boolean actual = hasBug(messages);
        assertThat(actual)
                .as("hasBug(%s): expected %s, but was %s",
                        java.util.Arrays.toString(messages), expected, actual)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    void test8_getEvenInRange() {
        int start = random.nextInt(10);
        int end = start + random.nextInt(20);
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(i);
            }
        }
        String expected = sb.toString();
        String actual = getEvenInRange(start, end);
        assertThat(actual)
                .as("getEvenInRange(%d, %d): expected \"%s\", but was \"%s\"",
                        start, end, expected, actual)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    void test9_findMax() {
        int[] arr = random.ints(5, -50, 50).toArray();
        int expected = java.util.Arrays.stream(arr).max().getAsInt();
        int actual = findMax(arr);
        assertThat(actual)
                .as("findMax(%s): expected %d, but was %d",
                        java.util.Arrays.toString(arr), expected, actual)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    void test10_reverse() {
        int len = random.nextInt(5) + 1;
        String[] arr = new String[len];
        for (int i = 0; i < len; i++) arr[i] = "s" + random.nextInt(100);
        String[] expected = new String[len];
        for (int i = 0; i < len; i++) expected[i] = arr[len - 1 - i];
        String[] actual = reverse(arr);
        assertThat(actual)
                .as("reverse(%s): expected %s, but was %s",
                        java.util.Arrays.toString(arr),
                        java.util.Arrays.toString(expected),
                        java.util.Arrays.toString(actual))
                .containsExactly(expected);
    }

    @RepeatedTest(10)
    void test11_calcAverage() {
        int size = random.nextInt(5) + 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) list.add(random.nextInt(100));
        double expected = list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double actual = calcAverage(list);
        assertThat(actual)
                .as("calcAverage(%s): expected %.4f, but was %.4f", list, expected, actual)
                .isCloseTo(expected, within(0.0001));
    }

    @RepeatedTest(10)
    void test12_removeSpecificName() {
        List<String> source = new ArrayList<>(List.of("Ann", "Bob", "Ann", "Mike"));
        String toRemove = random.nextBoolean() ? "Ann" : "Bob";
        List<String> expected = new ArrayList<>();
        for (String n : source) if (!n.equals(toRemove)) expected.add(n);
        List<String> actual = removeSpecificName(source, toRemove);
        assertThat(actual)
                .as("removeSpecificName(%s, \"%s\"): expected %s, but was %s",
                        source, toRemove, expected, actual)
                .isEqualTo(expected);
    }
}