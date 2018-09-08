package test;

import model.ImageFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ImageFileTest {
    @Test
    void testImageFileAddTag() throws IOException {
        File outFile = new File("/dogTEST.jpeg");
        ImageFile file = new ImageFile(outFile);

        file.addTag("Husky");
        assertTrue(file.getExistTag().size() == 1);
        assertTrue(file.getExistTag().contains("Husky"));

        file.addTag("Alaska,Samoyed");
        assertTrue(file.getExistTag().size() == 3);
        assertTrue(file.getExistTag().contains("Husky"));
        assertTrue(file.getExistTag().contains("Alaska"));
        assertTrue(file.getExistTag().contains("Samoyed"));
    }

    @Test
    void testImageFileDeleteTag() throws IOException {
        File outFile = new File("/dogTEST.jpeg");
        ImageFile file = new ImageFile(outFile);

        file.addTag("Husky");
        file.addTag("Alaska");
        file.addTag("Samoyed");

        file.deleteTag("Alaska");
        assertTrue(file.getExistTag().size() == 2);
        assertTrue(file.getExistTag().contains("Husky"));
        assertTrue(file.getExistTag().contains("Samoyed"));
    }

    @Test
    void testImageFileChangeDirectory() throws IOException {
        File outFile = new File("/dogTEST.jpeg");
        ImageFile file = new ImageFile(outFile);

        assertEquals(file.getFile().getParent(), "/");
        file.changeDirectory("/model");
        assertEquals(file.getFile().getParent(), "/model");
        assertNotEquals(file.getFile().getParent(), "/");
    }

    @Test
    void testImageFileChangeImageName() throws IOException {
        File outFile = new File("/dogTEST.jpeg");
        ImageFile file = new ImageFile(outFile);

        file.changeImageName("dogTESTChangeName");
        assertEquals(file.getFile().getName(), "dogTESTChangeName.jpeg");
    }

    @Test
    void testImageFileChangeTagHistory() throws IOException {
        File outFile = new File("/dogTEST.jpeg");
        ImageFile file = new ImageFile(outFile);

        ArrayList<String> tagsToRename = new ArrayList<>();
        tagsToRename.add("Husky");
        tagsToRename.add("Samoyed");
        tagsToRename.add("Alaska");

        file.changeTagHistory(tagsToRename);
        assertEquals(file.getExistTag(), tagsToRename);
    }

    @Test
    void testImageFileGetLog() throws IOException {
        File outFile = new File("/dogTEST.jpeg");
        ImageFile file = new ImageFile(outFile);

        file.addTag("Husky");
        file.addTag("Alaska");

        assertTrue(file.getLog().size() == 2);
        assertTrue(file.getLog().get(0).contains("dogTEST.jpeg"));
        assertTrue(file.getLog().get(0).contains("dogTEST @Husky.jpeg"));
        assertTrue(file.getLog().get(1).contains("dogTEST @Husky.jpeg"));
        assertTrue(file.getLog().get(1).contains("dogTEST @Husky @Alaska.jpeg"));
    }

    @Test
    void testImageFileGetFile() throws IOException {
        File outFileDog = new File("/dogTEST.jpeg");
        ImageFile fileDog = new ImageFile(outFileDog);
        File testFileDog = fileDog.getFile();

        File outFileCat = new File("/catTEST @Garfield.jpeg");
        ImageFile fileCat = new ImageFile(outFileCat);
        File testFileCat = fileCat.getFile();

        assertEquals(testFileDog, outFileDog);
        assertEquals(testFileCat, outFileCat);
    }

    @Test
    void testImageFileGetOriginalName() throws IOException {
        File outFileDog = new File("/dogTEST.jpeg");
        ImageFile fileDog = new ImageFile(outFileDog);

        File outFileCat = new File("/catTEST @Garfield.jpeg");
        ImageFile fileCat = new ImageFile(outFileCat);

        fileDog.addTag("Husky");
        fileDog.addTag("Alaska");
        fileDog.addTag("Samoyed");

        assertEquals(fileDog.getOriginalName(), "dogTEST");

        assertEquals(fileCat.getOriginalName(), "catTEST");
    }

    @Test
    void testImageFileGetOldName() throws IOException {
        File outFile = new File("/dogTEST.jpeg");
        ImageFile file = new ImageFile(outFile);

        assertTrue(file.getOldName().size() == 1);
        assertTrue(file.getOldName().contains("dogTEST.jpeg"));
    }

    @Test
    void testImageFileGetExistTag() throws IOException {
        File outFileDog = new File("/dogTEST.jpeg");
        ImageFile fileDog = new ImageFile(outFileDog);

        File outFileCat = new File("/catTEST @Garfield.jpeg");
        ImageFile fileCat = new ImageFile(outFileCat);

        fileDog.addTag("Husky");
        fileDog.addTag("Alaska");
        fileDog.addTag("Samoyed");

        ArrayList<String> existTagDog = fileDog.getExistTag();

        assertTrue(existTagDog.size() == 3);
        assertTrue(existTagDog.contains("Husky"));
        assertTrue(existTagDog.contains("Alaska"));
        assertTrue(existTagDog.contains("Samoyed"));

        fileCat.addTag("BlueCat");

        ArrayList<String> existTagCat = fileCat.getExistTag();

        assertTrue(existTagCat.size() == 2);
        assertTrue(existTagCat.contains("Garfield"));
        assertTrue(existTagCat.contains("BlueCat"));
    }

    @Test
    void testImageFileEquals() throws IOException {
        File outFileDog = new File("/dogTEST.jpeg");
        ImageFile fileDog = new ImageFile(outFileDog);

        File outFileCat = new File("/catTEST @Garfield.jpeg");
        ImageFile fileCat = new ImageFile(outFileCat);

        boolean test1 = fileDog.equals(fileCat);
        boolean test2 = fileDog.equals(fileDog);

        assertNotEquals(test1, true);
        assertEquals(test2, true);
    }

    @Test
    void testImageFileResetExistTag() throws IOException {
        File outFileDog = new File("/dogTEST.jpeg");
        ImageFile fileDog = new ImageFile(outFileDog);
        File testFileDog = fileDog.getFile();

        File outFileCat = new File("/catTEST @Garfield.jpeg");
        ImageFile fileCat = new ImageFile(outFileCat);
        File testFileCat = fileCat.getFile();

        assertEquals(testFileDog, outFileDog);
        assertEquals(testFileCat, outFileCat);
    }
}
