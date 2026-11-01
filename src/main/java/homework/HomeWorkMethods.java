package homework;

import java.util.List;
import java.util.stream.IntStream;

public class HomeWorkMethods {

    // Метод, возвращающий boolean (критерий 1.2)
    public boolean isPositive(int n) {
        return n > 0;
    }

    // Метод, возвращающий список (критерий 1.3)
    public List<String> getEvenNumbersUpTo(int limit) {
        return IntStream.rangeClosed(1, limit)
                .filter(n -> n % 2 == 0)
                .mapToObj(String::valueOf)
                .toList();
    }

    // Намеренно ошибочный метод — для падающего ассерта (критерий 1.4)
    public int brokenSum(int a, int b) {
        return a + b + 1;
    }

    // Четвёртый метод для строкового ассерта
    public String getGreeting(String name) {
        return "Hello, " + name + "!";
    }
}