package model;

import java.io.IOException;

/**
 * The ImageFileManager class which is extended from SerializeManager to manage all ImageFiles.
 *
 * @author Chenyue Wang
 * @version J.R.E 1.8.0
 */
public class ImageFileManager extends SerializeManager<ImageFile> {

    /**
     * Creates a new ImageFileManager.
     *
     * @param filePath the file to write this managerList to
     * @throws IOException            throws an IOException
     * @throws ClassNotFoundException throws a ClassNotFoundException
     */
    public ImageFileManager(String filePath) throws ClassNotFoundException, IOException {
        super(filePath);
    }
}