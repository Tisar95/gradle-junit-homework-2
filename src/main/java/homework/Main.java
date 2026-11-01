package homework;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // Задача 1
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    // Задача 2
    public static String checkAccess(int age) {
        if (age > 18) {
            return "Allowed";
        }
        return "Denied";
    }

    // Задача 3
    public static boolean isPositive(int n) {
        return n >= 0 ? true : false;
    }

    // Задача 4
    public static String getGrade(int score) {
        if (score >= 0 && score <= 20) return "E";
        if (score >= 21 && score <= 40) return "D";
        if (score >= 41 && score <= 60) return "C";
        if (score >= 61 && score <= 80) return "B";
        if (score >= 81 && score <= 100) return "A";
        return "Error";
    }

    // Задача 5
    public static String blastOff(int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i >= 1; i--) {
            sb.append(i).append(" ");
        }
        sb.append("Поехали!");
        return sb.toString();
    }

    // Задача 6
    public static int sumToN(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    // Задача 7
    public static boolean hasBug(String[] messages) {
        for (String m : messages) {
            if (m != null && m.equalsIgnoreCase("Bug")) {
                return true;
            }
        }
        return false;
    }

    // Задача 8
    public static String getEvenInRange(int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(i);
            }
        }
        return sb.toString();
    }

    // Задача 9
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // Задача 10
    public static String[] reverse(String[] arr) {
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        return result;
    }

    // Задача 11
    public static double calcAverage(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Integer num : list) {
            sum += num;
        }
        return sum / list.size();
    }

    // Задача 12
    public static List<String> removeSpecificName(List<String> list, String nameToRemove) {
        List<String> result = new ArrayList<>();
        for (String name : list) {
            if (!name.equals(nameToRemove)) {
                result.add(name);
            }
        }
        return result;
    }

    // Точка входа — просто чтобы можно было запустить и проверить вручную
    public static void main(String[] args) {
        System.out.println("isEven(4) = " + isEven(4));
        System.out.println("checkAccess(20) = " + checkAccess(20));
        System.out.println("isPositive(0) = " + isPositive(0));
        System.out.println("getGrade(85) = " + getGrade(85));
        System.out.println("blastOff(5) = " + blastOff(5));
        System.out.println("sumToN(5) = " + sumToN(5));
        System.out.println("hasBug = " + hasBug(new String[]{"ok", "BUG"}));
        System.out.println("getEvenInRange(2, 5) = " + getEvenInRange(2, 5));
        System.out.println("findMax = " + findMax(new int[]{3, 7, 2}));
        System.out.println("reverse = " + String.join(", ", reverse(new String[]{"One", "Two", "Zero"})));
        System.out.println("calcAverage = " + calcAverage(List.of(2, 4, 6)));
        System.out.println("removeSpecificName = " + removeSpecificName(List.of("Ann", "Bob", "Ann"), "Ann"));
    }
}