package model;

import java.io.IOException;

/**
 * The TagManager class which is extended from SerializeManager to manage tags.
 *
 * @author Chenyue Wang
 * @version J.R.E 1.8.0
 */
public class TagManager extends SerializeManager<String> {

    /**
     * Creates a new TagManager.
     *
     * @param filePath the file to write this managerList to
     * @throws IOException            throws an IOException
     * @throws ClassNotFoundException throws a ClassNotFoundException
     */
    public TagManager(String filePath) throws ClassNotFoundException, IOException {
        super(filePath);
    }

    /**
     * {@inheritDoc}
     * <p>
     * If newTag does not exist in this managerList, then adds it into ManagerList.
     * Otherwise, do nothing.
     */
    @Override
    public void add(String newTag, String filePath) throws IOException {
        String[] tagToAdd = newTag.split(",");

        for (String tag : tagToAdd) {
            if (!managerList.contains(tag.trim())) {
                managerList.add(tag.trim());
                writeToFile(filePath);
            }
        }
    }

}