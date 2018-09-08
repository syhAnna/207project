package model;

import java.io.*;
import java.util.ArrayList;

/**
 * An abstract class to represent a SerializeManager to manage objects which are to be serialized.
 *
 * @param <T> the type of elements to be serialized
 * @author Chenyue Wang
 * @version J.R.E 1.8.0
 */
public abstract class SerializeManager<T> {

    /**
     * A list that contains all instances of items with type T to be serialized.
     */
    ArrayList<T> managerList = new ArrayList<>();

    /**
     * Creates a new SerializeManager.
     *
     * @param filePath the file to write this managerList to
     * @throws IOException            throws an IOException
     * @throws ClassNotFoundException throws a ClassNotFoundException
     */
    SerializeManager(String filePath) throws ClassNotFoundException, IOException {
        managerList = new ArrayList<>();

        File file = new File(filePath);
        if (file.exists()) {
            {
                readFromFile(filePath);
            }
        } else {
            file.createNewFile();
        }
    }

    /**
     * Reads this managerList from the file at path filePath.
     *
     * @param filePath the path of this serialized file
     * @throws ClassNotFoundException throws a ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private void readFromFile(String filePath) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            managerList = (ArrayList<T>) input.readObject();
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Writes this managerList to file at filePath.
     *
     * @param filePath the file path to write this managerList to
     * @throws IOException throws an IOException
     */
    void writeToFile(String filePath) throws IOException {
        try {
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(managerList);
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds newItem to this SerializeManager.
     *
     * @param newItem  a newItem with type T to be added into this managerList.
     * @param filePath a filePath of this serialized file where to write this managerList to
     */
    public void add(T newItem, String filePath) throws IOException {
        managerList.add(newItem);
        writeToFile(filePath);
    }

    /**
     * Gets this managerList from this SerializeManager.
     *
     * @return ArrayList<T> the managerList of serialized items
     */
    public ArrayList<T> getSerializedList() {
        return managerList;
    }

    /**
     * Deletes oldItem form this SerializeManager.
     *
     * @param oldItem  an oldItem with type T to be deleted from this managerList.
     * @param filePath a filePath of this serialized file where to write this managerList to
     */
    public void delete(T oldItem, String filePath) throws IOException {
        managerList.remove(oldItem);
        writeToFile(filePath);
    }
}

