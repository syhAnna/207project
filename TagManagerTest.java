package test;

import model.TagManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TagManagerTest {

    @Test
    @SuppressWarnings("unchecked")
    void add() throws IOException, ClassNotFoundException {
        TagManager testTagManager = new TagManager("./testTagManager.ser");
        testTagManager.add("pop", "./testTagManager.ser");
        assertTrue(testTagManager.getSerializedList().size() == 1);
        assertTrue(testTagManager.getSerializedList().contains("pop"));
        testTagManager.add("apple,banana", "./testTagManager.ser");
        assertTrue(testTagManager.getSerializedList().size() == 3);
        assertTrue(testTagManager.getSerializedList().contains("apple"));
        assertTrue(testTagManager.getSerializedList().contains("banana"));
        assertTrue(testTagManager.getSerializedList().contains("pop"));
        ArrayList<String> tagList = (ArrayList<String>) testTagManager.getSerializedList().clone();
        for (String tag : tagList) {
            testTagManager.delete(tag, "./testTagManager.ser");
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void getSerializedList() throws IOException, ClassNotFoundException {
        TagManager testTagManager = new TagManager("./testTagManager2.ser");
        testTagManager.add("dog", "./testTagManager2.ser");
        testTagManager.delete("dog", "./testTagManager2.ser");
        assertTrue(testTagManager.getSerializedList().size() == 0);
        testTagManager.add("cat,pig", "./testTagManager2.ser");
        assertTrue(testTagManager.getSerializedList().size() == 2);
        assertTrue(testTagManager.getSerializedList().contains("cat"));
        assertTrue(testTagManager.getSerializedList().contains("pig"));
        ArrayList<String> tagList = (ArrayList<String>) testTagManager.getSerializedList().clone();
        for (String tag : tagList) {
            testTagManager.delete(tag, "./testTagManager2.ser");
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete() throws IOException, ClassNotFoundException {
        TagManager testTagManager = new TagManager("./testTagManager3.ser");
        testTagManager.add("book", "./testTagManager3.ser");
        testTagManager.delete("book", "./testTagManager3.ser");
        assertTrue(testTagManager.getSerializedList().size() == 0);
        testTagManager.add("pencil,bag,eraser", "./testTagManager3.ser");
        testTagManager.delete("pencil", "./testTagManager3.ser");
        assertTrue(testTagManager.getSerializedList().size() == 2);
        assertTrue(testTagManager.getSerializedList().contains("bag"));
        assertTrue(testTagManager.getSerializedList().contains("eraser"));
        testTagManager.delete("bag", "./testTagManager3.ser");
        assertTrue(testTagManager.getSerializedList().size() == 1);
        assertTrue(testTagManager.getSerializedList().contains("eraser"));
        testTagManager.delete("eraser", "./testTagManager3.ser");
        assertTrue(testTagManager.getSerializedList().size() == 0);
        ArrayList<String> tagList = (ArrayList<String>) testTagManager.getSerializedList().clone();
        for (String tag : tagList) {
            testTagManager.delete(tag, "./testTagManager3.ser");
        }
    }

}