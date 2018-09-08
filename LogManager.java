package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The LogManager class which is extended from SerializeManager to manage log history for all files.
 *
 * @author Chenyue Wang
 * @version J.R.E 1.8.0
 */
public class LogManager extends SerializeManager<String> {

    /**
     * Creates a new LogManager.
     *
     * @param filePath the file to write this managerList to
     * @throws IOException            throws an IOException
     * @throws ClassNotFoundException throws a ClassNotFoundException
     */
    public LogManager(String filePath) throws ClassNotFoundException, IOException {
        super(filePath);
    }

    /**
     * Gets log history for all image files.
     *
     * @return ArrayList<String> a list of log histories
     */
    public ArrayList<String> getLogHistory() {
        ArrayList<String> result = new ArrayList<>();
        for (String logHistory : managerList) {
            if (!Objects.equals(logHistory, " ")) {
                result.add(logHistory);
            }
        }
        return result;
    }
}
