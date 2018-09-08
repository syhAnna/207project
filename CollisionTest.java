package test;

import model.Collision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {
    @Test
    void testChangeNameAddOneTag() {
        Collision collision = new Collision();

        String nameBeforeChange = "dogTest.jpeg";
        String tagToAdd = "Husky";
        String expectedName = "dogTest @Husky.jpeg";

        assertEquals(collision.changeNameAdd(nameBeforeChange, tagToAdd), expectedName);
    }

    @Test
    void testChangeNameAddTwoTags() {
        Collision collision = new Collision();

        String nameBeforeChange = "dogTest.jpeg";
        String tagToAdd = "Alaska, Samoyed";
        String expectedName = "dogTest @Alaska @Samoyed.jpeg";

        assertEquals(collision.changeNameAdd(nameBeforeChange, tagToAdd), expectedName);
    }

    @Test
    void testChangeNameDelete() {
        Collision collision = new Collision();

        String nameBeforeChange = "dogTest @Alaska @Samoyed.jpeg";
        String tagToDelete = "Alaska";
        String expectedName = "dogTest @Samoyed.jpeg";

        assertEquals(collision.changeNameDelete(nameBeforeChange, tagToDelete), expectedName);
    }

    @Test
    void testSelectName() {
        Collision collision = new Collision();

        String name = "dogTest @Husky";
        String getSuffix = "catTest.jpeg";
        String expectedName = "dogTest @Husky.jpeg";

        assertEquals(collision.selectName(name, getSuffix), expectedName);
    }

}
