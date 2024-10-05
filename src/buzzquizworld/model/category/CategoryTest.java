package buzzquizworld.model.category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testToString() {
        assertEquals("Geography",Category.GEOGRAPHY.toString());
    }

    @Test
    void valueOf() {
        Category c = Category.GEOGRAPHY;
        assertEquals(c, Category.valueOf("GEOGRAPHY"));
    }
}