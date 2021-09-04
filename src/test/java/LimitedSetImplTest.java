
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LimitedSetImplTest {
    private LimitedSet<String> stringSet;

    @BeforeEach
    void setUp() {
        stringSet = new LimitedSetImpl<>();
    }

    @Test
    void testAddingMoreThenTenUniqueElements_Ok() {
        for (int i = 1; i <= 20; i++) {
            stringSet.add("element " + i);
        }

        int expectedSize = 10;

        Assertions.assertEquals(expectedSize, stringSet.size());
    }

    @Test
    void testSizeMethod_Ok() {
        Assertions.assertEquals(0, stringSet.size());

        stringSet.add("element 1");

        Assertions.assertEquals(1, stringSet.size());

        stringSet.add("element 2");
        stringSet.add("element 3");
        stringSet.add("element 1");

        Assertions.assertEquals(3, stringSet.size());

        Assertions.assertTrue(stringSet.remove("element 1"));

        Assertions.assertEquals(2, stringSet.size());

        for (int i = 0; i < 100; i++) {
            stringSet.add("element " + i);
        }
        stringSet.remove("element 99");

        Assertions.assertEquals(9, stringSet.size());
    }

    @Test
    void testRemovingNonExistingValue_Ok() {
        stringSet.add("element 1");

        Assertions.assertFalse(stringSet.remove("element 2"));
    }

    @Test
    void testReplaceLeastViewedValue_Ok() {
        for (int i = 1; i <= 10; i++) {
            stringSet.add("element " + i);
        }

        for (int i = 1; i < 10; i++) {
            stringSet.contains("element " + i);
        }

        String replaceValue = "element 10 is replaced";
        stringSet.add(replaceValue);

        Assertions.assertFalse(stringSet.contains("10"));

        stringSet.contains(replaceValue);
        stringSet.contains(replaceValue);
        stringSet.add("another value that should not replace replaceValue");

        Assertions.assertTrue(stringSet.contains(replaceValue));
    }

    @Test
    void testAdding_Ok() {
        stringSet.add("test");
        stringSet.add("test1");
        stringSet.add("test2");

        LimitedSet<String> stringSetSecond = new LimitedSetImpl<>();

        Assertions.assertFalse(stringSetSecond.contains("test"));
        Assertions.assertFalse(stringSetSecond.contains("test1"));
        Assertions.assertFalse(stringSetSecond.contains("test2"));

        stringSetSecond.add("test");

        Assertions.assertTrue(stringSetSecond.contains("test"));
    }

    @Test
    void testAddingNull_Ok() {
        stringSet.add("element 1");
        stringSet.add(null);

        Assertions.assertTrue(stringSet.contains(null));
        Assertions.assertTrue(stringSet.remove(null));
    }

    @Test
    void testAddingDifferentParameters_Ok() {
        LimitedSet<Integer> integerSet = new LimitedSetImpl<>();

        integerSet.add(1);
        integerSet.add(Integer.MAX_VALUE);
        integerSet.add(Integer.MIN_VALUE);

        Assertions.assertTrue(integerSet.contains(1));

        LimitedSet<Boolean> booleanSet = new LimitedSetImpl<>();

        booleanSet.add(true);
        booleanSet.add(false);
        booleanSet.add(true);
        booleanSet.add(false);

        Assertions.assertTrue(booleanSet.contains(true));

        LimitedSet<Double> doubleSet = new LimitedSetImpl<>();

        doubleSet.add(2.21321);
        doubleSet.add(Double.MAX_VALUE);
        doubleSet.add(Double.MIN_VALUE);

        Assertions.assertTrue(doubleSet.contains(2.21321));

        LimitedSet<Character> characterSet = new LimitedSetImpl<>();

        characterSet.add('a');
        characterSet.add('b');
        characterSet.add('c');
        characterSet.add('d');

        Assertions.assertTrue(characterSet.contains('a'));
    }
}