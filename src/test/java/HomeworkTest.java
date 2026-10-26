package homework;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static homework.Main.*;

@Tag("homework")
public class HomeworkTest {

    private final Random random = new Random();

    @RepeatedTest(10)
    void test1_isEven() {
        int n = random.nextInt(100) + 1;
        boolean actual = isEven(n);
        boolean expected = (n % 2 == 0);
        printResult("isEven(" + n + ")", expected, actual);
    }

    @RepeatedTest(10)
    void test2_checkAccess() {
        int age = random.nextInt(100);
        String actual = checkAccess(age);
        String expected = age > 18 ? "Allowed" : "Denied";
        printResult("checkAccess(" + age + ")", expected, actual);
    }

    @RepeatedTest(10)
    void test3_isPositive() {
        int n = random.nextInt(200) - 100;
        boolean actual = isPositive(n);
        boolean expected = n >= 0;
        printResult("isPositive(" + n + ")", expected, actual);
    }

    @RepeatedTest(10)
    void test4_getGrade() {
        int score = random.nextInt(101);
        String actual = getGrade(score);
        String expected = score <= 20 ? "E" : score <= 40 ? "D" : score <= 60 ? "C" : score <= 80 ? "B" : "A";
        printResult("getGrade(" + score + ")", expected, actual);
    }

    @RepeatedTest(10)
    void test5_blastOff() {
        int start = random.nextInt(10) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i >= 1; i--) sb.append(i).append(" ");
        sb.append("Поехали!");
        String expected = sb.toString();
        String actual = blastOff(start);
        printResult("blastOff(" + start + ")", expected, actual);
    }

    @RepeatedTest(10)
    void test6_sumToN() {
        int n = random.nextInt(100) + 1;
        int expected = n * (n + 1) / 2;
        int actual = sumToN(n);
        printResult("sumToN(" + n + ")", expected, actual);
    }

    @RepeatedTest(10)
    void test7_hasBug() {
        boolean expected = random.nextBoolean();
        String[] messages = expected ? new String[]{"ok", "BUG"} : new String[]{"ok", "info"};
        boolean actual = hasBug(messages);
        printResult("hasBug(" + java.util.Arrays.toString(messages) + ")", expected, actual);
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
        printResult("getEvenInRange(" + start + ", " + end + ")", expected, actual);
    }

    @RepeatedTest(10)
    void test9_findMax() {
        int[] arr = random.ints(5, -50, 50).toArray();
        int expected = java.util.Arrays.stream(arr).max().getAsInt();
        int actual = findMax(arr);
        printResult("findMax(" + java.util.Arrays.toString(arr) + ")", expected, actual);
    }

    @RepeatedTest(10)
    void test10_reverse() {
        int len = random.nextInt(5) + 1;
        String[] arr = new String[len];
        for (int i = 0; i < len; i++) arr[i] = "s" + random.nextInt(100);
        String[] expected = new String[len];
        for (int i = 0; i < len; i++) expected[i] = arr[len - 1 - i];
        String actual = java.util.Arrays.toString(reverse(arr));
        String exp = java.util.Arrays.toString(expected);
        printResult("reverse(" + java.util.Arrays.toString(arr) + ")", exp, actual);
    }

    @RepeatedTest(10)
    void test11_calcAverage() {
        int size = random.nextInt(5) + 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) list.add(random.nextInt(100));
        double expected = list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double actual = calcAverage(list);
        boolean ok = Math.abs(expected - actual) < 0.0001;
        System.out.println("calcAverage(" + list + ") => " + (ok ? "TEST PASSED" : "TEST FAILED"));
    }

    @RepeatedTest(10)
    void test12_removeSpecificName() {
        List<String> source = new ArrayList<>(List.of("Ann", "Bob", "Ann", "Mike"));
        String toRemove = random.nextBoolean() ? "Ann" : "Bob";
        List<String> expected = new ArrayList<>();
        for (String n : source) if (!n.equals(toRemove)) expected.add(n);
        List<String> actual = removeSpecificName(source, toRemove);
        printResult("removeSpecificName(" + source + ", " + toRemove + ")", expected, actual);
    }

    private void printResult(String caseName, Object expected, Object actual) {
        if (String.valueOf(expected).equals(String.valueOf(actual))) {
            System.out.println(caseName + " => TEST PASSED");
        } else {
            System.out.println(caseName + " => TEST FAILED: ожидалось " + expected + ", получено " + actual);
        }
    }
}