package view_control;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Collision;
import model.ImageFile;

/**
 * The DeleteTagScene class. Construct the layout of this AddTagScene
 *
 * @author Dingyi Yu
 * @version J.R.E 1.8.0
 */
class DeleteTagScene {

    /**
     * Magic number 350
     */
    private static final int MAGIC350 = 350;

    /**
     * Magic number 120
     */
    private static final int MAGIC120 = 120;

    /**
     * Magic number 10
     */
    private static final int MAGIC10 = 10;

    /**
     * Magic number 20
     */
    private static final int MAGIC20 = 20;

    /**
     * Magic number 400
     */
    private static final int MAGIC400 = 400;

    /**
     * Initialize an ImageFile
     */
    private static ImageFile inputFile;

    /**
     * Initialize an ListView of string to display current tags.
     */
    private static ListView<String> listView;

    /**
     * Initialize a Collision Object
     */
    private static Collision collision = new Collision();

    /**
     * Display the Scene and construct the buttons.
     */
    static void display() {
        Stage window = new Stage();
        window.setTitle("Delete Tag(s)");
        Label label = new Label("Please check the tag(s) you want to delete");
        Button delete = new Button("Delete");
        Button back = new Button("Go back");
        delete.setMinWidth(MAGIC120);
        back.setMinWidth(MAGIC120);

        listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if (inputFile != null) {
            for (String tag : inputFile.getExistTag()) {
                listView.getItems().add(tag);
            }
        }

        delete.setOnAction(e -> buttonClicked());

        back.setOnAction(e -> window.close());

        VBox layout = new VBox(MAGIC10);
        layout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        layout.getChildren().addAll(label, listView, delete, back);

        Scene scene = new Scene(layout, MAGIC350, MAGIC400);
        window.setScene(scene);
        window.show();
    }

    /**
     * Delete selected tags once button has been delete clicked.
     */
    private static void buttonClicked() {
        ObservableList<String> tags;
        tags = listView.getSelectionModel().getSelectedItems();
        StringBuilder collection = new StringBuilder();
        for (String tag : tags) {
            collection.append(tag);
        }
        String PotentialName =
                collision.changeNameDelete(inputFile.getFile().getName(), collection.toString());
        if (!collisionDelete(PotentialName)) {
            for (String tag : tags) {
                try {
                    ImageFile inputFileSer = inputFile;
                    ImageFile saveCurrent = inputFile;
                    String logHistory = inputFileSer.deleteTag(tag);
                    ManipulationManagerScene.imageFileManager.delete(
                            saveCurrent, ManipulationManagerScene.imageFileManagerPath);
                    ManipulationManagerScene.imageFileManager.add(
                            inputFileSer, ManipulationManagerScene.imageFileManagerPath);
                    ManipulationManagerScene.logManager.add(
                            logHistory, ManipulationManagerScene.logManagerPath);
                    inputFile = inputFileSer;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listView.getItems().remove(tag);
                ManipulationManagerScene.setImageListView(ManipulationManagerScene.imgFiles);
                ManipulationManagerScene.setPath(inputFile);
            }
        }
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
     * A pop up warning box that says Inappropriate deletion
     */
    private static void inappropriateDeletion() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Inappropriate Tag Deletion");
        alert.setHeaderText("This directory already has an image with the same name!");
        alert.setContentText("Choose Another Tag To Delete");

        alert.showAndWait();
    }

    /**
     * Return true if there is collision in delete, false otherwise.
     *
     * @param potentialName the potential name that this inputfile might change to
     * @return whether there is collision
     */
    private static boolean collisionDelete(String potentialName) {
        for (ImageFile file : ManipulationManagerScene.imgFiles) {
            if (!inputFile.equals(file)) {
                if (inputFile.getFile().getParent().equals(file.getFile().getParent())) {
                    if ((file.getFile().getName().equals(potentialName))) {
                        inappropriateDeletion();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
