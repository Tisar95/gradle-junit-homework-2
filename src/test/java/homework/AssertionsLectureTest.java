package homework;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("lecture")
public class AssertionsLectureTest {

    private final HomeWorkMethods methods = new HomeWorkMethods();

    @Test
    void testIsPositive_returnsBoolean() {
        int n = 5;
        boolean actual = methods.isPositive(n);
        assertThat(actual)
                .as("isPositive(%d): expected true, but was %s", n, actual)
                .isTrue();
    }

    @Test
    void testGetEvenNumbersUpTo_returnsList() {
        int limit = 6;
        List<String> actual = methods.getEvenNumbersUpTo(limit);
        assertThat(actual)
                .as("getEvenNumbersUpTo(%d): expected [2, 4, 6], but was %s", limit, actual)
                .containsExactly("2", "4", "6");
    }

    @Test
    void testBrokenSum_shouldFailWithInfo() {
        int a = 2, b = 3;
        int actual = methods.brokenSum(a, b);
        assertThat(actual)
                .as("brokenSum(%d, %d): expected 5, but was %s", a, b, actual)
                .isEqualTo(5);
    }

    @Test
    void testGetGreeting_returnsString() {
        String name = "World";
        String actual = methods.getGreeting(name);
        assertThat(actual)
                .as("getGreeting(\"%s\"): expected \"Hello, World!\", but was \"%s\"", name, actual)
                .isEqualTo("Hello, World!");
    }
}