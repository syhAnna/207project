package view_control;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Collision;
import model.ImageFile;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The SelectTagScene class. Construct the layout of this SelectTagScene
 *
 * @author Dingyi Yu
 * @version J.R.E 1.8.0
 */
class SelectTagScene {

    /**
     * Magic Number 10
     */
    private static final int MAGIC10 = 10;

    /**
     * Magic Number 20
     */
    private static final int MAGIC20 = 20;

    /**
     * Magic Number 120
     */
    private static final int MAGIC120 = 120;

    /**
     * Magic Number 550
     */
    private static final int MAGIC550 = 550;

    /**
     * Magic Number 600
     */
    private static final int MAGIC600 = 600;

    /**
     * Initialize an ImageFile
     */
    private static ImageFile inputFile;

    /**
     * Initialize a Collision Object
     */
    private static Collision collision = new Collision();

    /**
     * Display the Scene and construct the buttons.
     */
    static void display() {
        Stage window = new Stage();
        window.setTitle("Select Old Tag(s)");
        window.initModality(Modality.APPLICATION_MODAL);
        Label selectInstruction = new Label("Please check the tag(s) you want to rename the photo for");
        Button submit = new Button("Rename");
        Button back = new Button("Go back");
        submit.setMinWidth(MAGIC120);
        back.setMinWidth(MAGIC120);
        VBox layout = new VBox(MAGIC10);
        layout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        layout.getChildren().add(selectInstruction);
        ArrayList<CheckBox> checkBox = new ArrayList<>();

        if (inputFile != null) {
            for (String tag : inputFile.getExistTag()) {
                CheckBox box = new CheckBox(tag);
                checkBox.add(box);
                layout.getChildren().add(box);
            }
        }
        layout.getChildren().add(submit);
        layout.getChildren().add(back);
        submit.setOnAction(
                e -> {
                    try {
                        handleOptions((checkBox));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
        back.setOnAction(e -> window.close());
        Scene scene = new Scene(layout, MAGIC550, MAGIC600);
        window.setScene(scene);
        window.show();
    }

    /**
     * The tags of the file will be renewed to be the checked tags once submit button was clicked.
     *
     * @param checkBox the Checkbox that displayed on the scene
     * @throws IOException IOException will be thrown.
     */
    private static void handleOptions(ArrayList<CheckBox> checkBox) throws IOException {
        if (inputFile != null) {
            StringBuilder currentName = new StringBuilder();
            currentName.append(inputFile.getOriginalName());
            ArrayList<String> deleteTag = new ArrayList<>();
            boolean checkDelete = false;

            for (CheckBox box : checkBox) {
                if (box.isSelected()) {
                    currentName.append(" @").append(box.getText());
                    checkDelete = true;
                } else {
                    deleteTag.add(box.getText());
                }
            }
            String potentialName =
                    collision.selectName(currentName.toString(), inputFile.getFile().getName());
            if (!(collisionSelection(potentialName))) {
                if (checkDelete) {
                    ImageFile inputFileSer = inputFile;
                    ImageFile saveCurrent = inputFile;
                    String logHistory1 = inputFileSer.changeImageName(currentName.toString());
                    for (String a : deleteTag) {
                        inputFileSer.resetExistTag(a);
                    }
                    ManipulationManagerScene.imageFileManager.delete(
                            saveCurrent, ManipulationManagerScene.imageFileManagerPath);
                    ManipulationManagerScene.imageFileManager.add(
                            inputFileSer, ManipulationManagerScene.imageFileManagerPath);
                    ManipulationManagerScene.logManager.add(
                            logHistory1, ManipulationManagerScene.logManagerPath);
                    inputFile = inputFileSer;
                    ManipulationManagerScene.setImageListView(ManipulationManagerScene.imgFiles);
                    ManipulationManagerScene.setPath(inputFile);
                }
            }
        }
    }

    /**
     * Set the ImageFile
     *
     * @param imageFile the ImageFile
     */
    static void setImageFile(ImageFile imageFile) {
        inputFile = imageFile;
    }

    /**
     * A pop up warning box that says Inappropriate choose of old tags
     */
    private static void inappropriateCombination() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Inappropriate Tag Combination");
        alert.setHeaderText("This directory already has an image with the same name!");
        alert.setContentText("Choose another Tag Combination");
        alert.showAndWait();
    }

    /**
     * Return true if there is collision in the directory, false otherwise.
     *
     * @param potentialName the potential name that this inputfile might change to
     * @return whether there is collision
     */
    private static boolean collisionSelection(String potentialName) {
        for (ImageFile file : ManipulationManagerScene.imgFiles) {
            if (!inputFile.equals(file)) {
                if (inputFile.getFile().getParent().equals(file.getFile().getParent())) {
                    if ((file.getFile().getName().equals(potentialName))) {
                        inappropriateCombination();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
