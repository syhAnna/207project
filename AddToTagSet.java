package view_control;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The AddToTagSet class. Construct the layout of this AddToTagSet Scene.
 *
 * @author Jiayao Lin
 * @version J.R.E 1.8.0
 */
class AddToTagSet {

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
     * Display this Scene and construct this buttons.
     */
    static void display() {
        Stage addScene = new Stage();
        addScene.initModality(Modality.APPLICATION_MODAL);
        addScene.setTitle("Add tag(s) to tag Set");
        addScene.setMinWidth(MAGIC250);
        Label AddInstruction = new Label();
        AddInstruction.setText("Please enter tag(s), separated with \",\"");

        Button done = new Button("Done");
        Button goBack = new Button("Go back");
        TextField tagInput = new TextField();

        done.setOnAction(
                e -> {
                    String tags = tagInput.getText();
                    if (!Objects.equals(tags, "")) {
                        try {
                            ManipulationManagerScene.tagManager.add(
                                    tags, ManipulationManagerScene.tagManagerPath);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        tagInput.clear();
                        ManipulationManagerScene.setTagSetView();
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
}
