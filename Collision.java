package model;


/**
 * This class deals with the potential file collision.
 *
 * @author Yuhan Shao
 * @version J.R.E 1.8.0
 */
public class Collision {

    /**
     * Returns a String of name which has added the tags to the pass-in name.
     *
     * @param nameBeforeChange the String of name before the changes
     * @param tagsToAdd        the ArrayList of String of tags which wanted to be added
     * @return the String of the current name
     */
    public String changeNameAdd(String nameBeforeChange, String tagsToAdd) {
        Integer target = nameBeforeChange.lastIndexOf(".");

        StringBuilder previousName = new StringBuilder(nameBeforeChange.substring(0, target));
        String suffix = nameBeforeChange.substring(target, nameBeforeChange.length());

        if (tagsToAdd.contains(",")) {
            String[] tagsAdd = tagsToAdd.split(",");

            for (String tags : tagsAdd) {
                previousName.append(" @").append(tags.trim());
            }
        } else {
            previousName.append(" @").append(tagsToAdd.trim());
        }
        return previousName.append(suffix).toString();
    }

    /**
     * Returns a String of name which has deleted the pass-in tag in the pass-in name.
     *
     * @param nameBeforeChange the String of name before the changes
     * @param tagToDelete      the String of a tag which wanted to be deleted
     * @return the String of the current name
     */
    public String changeNameDelete(String nameBeforeChange, String tagToDelete) {
        return nameBeforeChange.replace(" @" + tagToDelete, "");
    }

    /**
     * Return a name which wanted to be selected.
     *
     * @param name      the String of a pass-in name
     * @param getSuffix the String of a name which we want to get the suffix
     * @return a name wanted to be selected
     */
    public String selectName(String name, String getSuffix) {
        Integer suffixLocation = getSuffix.lastIndexOf(".");
        String suffix = getSuffix.substring(suffixLocation, getSuffix.length());
        return name + suffix;
    }
}
