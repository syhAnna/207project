package view_control;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import model.Collision;
import model.ImageFile;

import java.io.IOException;
import java.util.Objects;


/**
 * The AddTagScene class.
 * Construct the layout of this AddTagScene
 *
 * @author Jiayao Lin
 * @version J.R.E 1.8.0
 */
class AddTagScene {

    /**
     * Magic number 250
     */
    private static final int MAGIC250 = 250;

    /**
     * Magic number 100
     */
    private static final int MAGIC100 = 100;

    /**
     * Magic number 10
     */
    private static final int MAGIC10 = 10;

    /**
     * Magic number 20
     */
    private static final int MAGIC20 = 20;

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
        Stage addScene = new Stage();
        addScene.initModality(Modality.APPLICATION_MODAL);
        addScene.setTitle("Add tag(s)");
        addScene.setMinWidth(MAGIC250);
        Label AddInstruction = new Label();
        AddInstruction.setText("Please enter tag(s), separated with \",\"");

        // Create two buttons
        Button done = new Button("Done");
        Button goBack = new Button("Go back");
        // Form a Text area
        TextField tagInput = new TextField();

        done.setOnAction(
                e -> {
                    String tags = tagInput.getText();
                    if (!Objects.equals(tags, "") && inputFile != null) {
                        String potentialName = collision.changeNameAdd(inputFile.getFile().getName(), tags);
                        if (!collisionAdd(potentialName)) {
                            try {
                                ImageFile inputFileSer = inputFile;
                                ImageFile saveCurrent = inputFile;
                                String logHistory = inputFileSer.addTag(tags);
                                ManipulationManagerScene.imageFileManager.delete(saveCurrent, ManipulationManagerScene.imageFileManagerPath);
                                ManipulationManagerScene.imageFileManager.add(inputFileSer, ManipulationManagerScene.imageFileManagerPath);
                                ManipulationManagerScene.logManager.add(logHistory, ManipulationManagerScene.logManagerPath);
                                ManipulationManagerScene.tagManager.add(tags, ManipulationManagerScene.tagManagerPath);
                                inputFile = inputFileSer;
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            tagInput.clear();
                            ManipulationManagerScene.setImageListView(ManipulationManagerScene.imgFiles);
                            ManipulationManagerScene.setTagSetView();
                            ManipulationManagerScene.setPath(inputFile);
                        }
                    }
                });

        goBack.setOnAction(e -> addScene.close());

        done.setMinWidth(MAGIC100);
        goBack.setMinWidth(MAGIC100);
        VBox addTagLayout = new VBox(MAGIC10);
        addTagLayout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        addTagLayout.getChildren().addAll(AddInstruction, tagInput, done, goBack);
        addTagLayout.setAlignment(Pos.CENTER);

        Scene tagScene = new Scene(addTagLayout);
        addScene.setScene(tagScene);
        addScene.showAndWait();
    }

    /**
     * Set this ImageFile
     *
     * @param imageFile the ImageFile
     */
    static void setImageFile(ImageFile imageFile) {
        inputFile = imageFile;
    }


    /**
     * A pop up warning box that says Inappropriate Add
     */
    private static void inappropriateAdd() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Inappropriate Tag To Add");
        alert.setHeaderText("This directory already has an image with the same name!");
        alert.setContentText("Choose Another Tag To Add");

        alert.showAndWait();
    }

    /**
     * Return true if there is collision in add, false otherwise.
     *
     * @param potentialName the potential name that this inputfile might change to
     * @return whether there is collision
     */
    private static boolean collisionAdd(String potentialName) {
        for (ImageFile file : ManipulationManagerScene.imgFiles) {
            if (!inputFile.equals(file)) {
                if (inputFile.getFile().getParent().equals(file.getFile().getParent())) {
                    if ((file.getFile().getName().equals(potentialName))) {
                        inappropriateAdd();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}






