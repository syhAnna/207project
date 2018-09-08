package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * This class generates an ImageFile instance with some variables and methods
 *
 * @author Yuhan Shao
 * @version J.R.E 1.8.0
 */
public class ImageFile implements Serializable {
    /**
     * A File object represented by file
     */
    private File file;

    /**
     * The original name of this ImageFile object without tags
     */
    private String originalName;

    /**
     * An ArrayList of String of the exist Tag
     */
    private ArrayList<String> existTag;

    /**
     * An ArrayList of String of the old names of this ImageFile object
     */
    private ArrayList<String> oldName;

    /**
     * An ArrayList of String of the log history of this ImageFile object
     */
    private ArrayList<String> history;

    /**
     * Creates a new empty ImageFile.
     *
     * @param file the File object which used to construct ImageFile
     * @throws IOException throw an IOException
     */
    public ImageFile(File file) throws IOException {
        this.file = file;
        this.history = new ArrayList<>();
        this.existTag = new ArrayList<>();
        this.oldName = new ArrayList<>();

        if (!this.file.getName().contains("@")) {
            this.originalName = this.getNameWithoutSuffix(this.file);
        } else {
            Integer target = this.file.getName().indexOf("@");
            this.originalName = this.file.getName().substring(0, target).trim();

            String[] originalTag = this.getNameWithoutSuffix(this.file).split("@");
            for (int i = 1; i < originalTag.length; i++) {
                this.existTag.add(originalTag[i].trim());
            }
        }

        this.oldName.add(this.file.getName());
    }

    /**
     * Gets the name of a File object without suffix
     *
     * @param file the File object to get its name without suffix
     * @return the name of the File object without suffix
     */
    private String getNameWithoutSuffix(File file) {
        String[] separate = file.getName().split("\\.(?=[^.]+$)");
        return separate[0].trim();
    }

    /**
     * Gets the suffix of a File object
     *
     * @param file the File object to get its suffix
     * @return the suffix of the File object
     */
    private String getSuffix(File file) {
        String[] separate = file.getName().split("\\.(?=[^.]+$)");
        return separate[1].trim();
    }

    /**
     * Adds user input tag name if it does not exist and rename this ImageFile.
     *
     * @param userInputAdd the tag name which is supposed to be added to this ImageFile name
     * @return the String of the name after adding the tags
     */
    public String addTag(String userInputAdd) throws IOException {
        String[] tagToAdd = userInputAdd.split(",");

        StringBuilder tagAdd = new StringBuilder();

        for (String tag : tagToAdd) {
            if (!this.existTag.contains(tag.trim())) {
                this.existTag.add(tag.trim());
                tagAdd = tagAdd.append(tag.trim()).append(" @");
            }
        }

        if (tagAdd.length() != 0) {
            tagAdd.replace(tagAdd.length() - 2, tagAdd.length(), "");
            return this.renameAdd(tagAdd.toString());
        }
        return " ";
    }

    /**
     * Deletes user input tag name and rename this ImageFile.
     *
     * @param tagToDelete the tag name which is supposed to be deleted from this ImageFile name
     * @return the String of the name after deleting the tag
     */
    public String deleteTag(String tagToDelete) throws IOException {
        this.existTag.remove(tagToDelete);
        return this.renameDelete(tagToDelete);
    }

    /**
     * Changes the Directory of this ImageFile object and reset the image of this ImageFile object.
     *
     * @param newParentDirectory new parent directory of this ImageFile object
     */
    @SuppressWarnings("unused")
    public void changeDirectory(String newParentDirectory) throws IOException {
        File dir = new File(newParentDirectory);

        boolean success = file.renameTo(new File(dir, file.getName()));
        this.file = new File(dir, file.getName());
    }

    /**
     * Changes the name of this ImageFile object.
     *
     * @param newImageName new name of this ImageFile object
     * @return the String of new name
     */
    public String changeImageName(String newImageName) throws IOException {
        Date time = new Date();
        String result = passHistory(time, this.file.getName(), newImageName, this.getSuffix(this.file));
        history.add(time + " Renamed this image file from " + this.file.getName() +
                " to " + newImageName + "." + this.getSuffix(this.file) + "\n");

        File renameFile = new File(this.file.getAbsolutePath().replace(this.getNameWithoutSuffix(this.file), newImageName));
        boolean success = this.file.renameTo(renameFile);
        this.file = renameFile;

        if (success) {
            if (!this.oldName.contains(this.file.getName())) {
                this.oldName.add(this.file.getName());
            }
        }
        return result;
    }

    /**
     * Renames this ImageFile by deleting a tag.
     *
     * @param tagToDelete the tag name need to be deleted
     * @return the String of new name after deleting the tag
     */
    private String renameDelete(String tagToDelete) throws IOException {
        StringBuilder newName = new StringBuilder(this.getOriginalName());
        for (String tag : this.existTag) {
            if (!Objects.equals(tag, tagToDelete)) {
                newName.append(" @").append(tag);
            }
        }
        return this.changeImageName(newName.toString());
    }

    /**
     * Renames this ImageFile by adding a tag.
     *
     * @param tagToAdd the tag name need to be added
     * @return the String of new name after adding the tag
     */
    private String renameAdd(String tagToAdd) throws IOException {
        return this.changeImageName(this.getNameWithoutSuffix(this.file) + " @" + tagToAdd);
    }

    /**
     * Updates the existTag.
     *
     * @param tagsToRename the ArrayList of tags to be renamed
     */
    public void changeTagHistory(ArrayList<String> tagsToRename) throws IOException {
        this.existTag = tagsToRename;
    }

    /**
     * Gets the log history of this ImageFile object.
     *
     * @return the ArrayList of String of log history
     */
    public ArrayList<String> getLog() {
        return history;
    }

    /**
     * Gets this ImageFile object's file.
     *
     * @return the file of this ImageFile object
     */
    public File getFile() {
        return file;
    }

    /**
     * Gets this ImageFile object's originalName.
     *
     * @return the originalName of this ImageFile object
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * Gets this ImageFile object's oldName.
     *
     * @return the oldName of this ImageFile object
     */
    public ArrayList<String> getOldName() {
        return oldName;
    }

    /**
     * Gets this ImageFile object's existTag.
     *
     * @return the existTag of this ImageFile object
     */
    public ArrayList<String> getExistTag() {
        return existTag;
    }

    /**
     * Returns whether this ImageFile's file has the same absolute path as the other ImageFile's file's.
     *
     * @param imageFile other ImageFile object which compare with this ImageFile object
     * @return whether this ImageFile's file has the same absolute path as the other ImageFile's file's
     */
    public boolean equals(ImageFile imageFile) {
        return (imageFile.getFile().getAbsolutePath()).equals(this.file.getAbsolutePath());
    }

    /**
     * Returns a String of this ImageFile's pass log history.
     *
     * @param time       the time stamp of this ImageFile when it change the name
     * @param oldName    the old name of this ImageFile before it changed name
     * @param newName    the old name of this ImageFile before it changed name
     * @param suffixName the new name of this ImageFile after it changed name
     * @return whether this ImageFile's file has the same absolute path as the other ImageFile's file's
     */
    private String passHistory(Date time, String oldName, String newName, String suffixName) {
        String result = "";
        result += time + " Renamed this image file from " + oldName + " to " + newName + "." + suffixName + "\n";
        return result;
    }

    /**
     * Resets this ImageFile's exist tag in order to delete the tag in the exist tag.
     *
     * @param tagToDelete the tag to be deleted in the exist set
     */
    public void resetExistTag(String tagToDelete) {
        this.existTag.remove(tagToDelete);
    }
}
