package view_control;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ImageFile;

import java.util.ArrayList;

/**
 * The ContainTagScene class. Construct the layout of this ContainTagScene
 *
 * @author Jiayao Lin
 * @version J.R.E 1.8.0
 */
class ContainTagScene {

    /**
     * Magic number 50
     */
    private static final int MAGIC50 = 50;

    /**
     * Magic number 450
     */
    private static final int MAGIC450 = 450;

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
     * Magic number 500
     */
    private static final int MAGIC500 = 500;

    /**
     * Magic number 600
     */
    private static final int MAGIC600 = 600;

    /**
     * Magic number 950
     */
    private static final int MAGIC950 = 950;

    /**
     * Initialize an ListView of string to display current tags.
     */
    private static ListView<String> listView;

    /**
     * Initialize a collection of ImageFile contain selected tag(s)
     */
    private static ArrayList<ImageFile> imgFiles = new ArrayList<>();

    /**
     * Initialize a imageView
     */
    private static ImageView imageView = new ImageView();

    /**
     * Initialize a StackPane to place this image
     */
    private static StackPane paneCenter = new StackPane();

    /**
     * Initialize a BorderPane for this general layout format
     */
    private static BorderPane inputGridPane = new BorderPane();

    /**
     * Display this Scene and construct buttons.
     */
    static void display() {
        Stage window = new Stage();
        inputGridPane.setPrefSize(MAGIC500, MAGIC450);
        paneCenter.setStyle("-fx-background-color: #f5f5dc");

        window.setTitle("Show Images with Tags");
        window.initModality(Modality.APPLICATION_MODAL);
        Button select = new Button("Select");
        Button back = new Button("Go back");
        select.setMinWidth(MAGIC120);
        back.setMinWidth(MAGIC120);

        listView = new ListView<>();
        listView.setOnMouseClicked(event -> buttonClicked());

        for (ImageFile file : imgFiles) {
            listView.getItems().add(file.getFile().getName());
        }

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().add(back);
        select.setOnAction(e -> buttonClicked());

        back.setOnAction(
                e -> {
                    paneCenter.getChildren().remove(imageView);
                    window.close();
                });

        inputGridPane.setCenter(paneCenter);
        inputGridPane.setLeft(listView);
        inputGridPane.setBottom(toolbar);

        VBox layout = new VBox(MAGIC10);
        layout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        layout.getChildren().add(inputGridPane);

        Scene scene = new Scene(layout, MAGIC950, MAGIC600);
        window.setScene(scene);
        window.show();
    }

    /**
     * Delete this selected tags once button has been delete clicked.
     */
    private static void buttonClicked() {
        ObservableList<String> imgNames;
        imgNames = listView.getSelectionModel().getSelectedItems();
        if (imgNames.size() == 1) {
            String name = imgNames.get(0);
            for (ImageFile file : imgFiles) {
                if ((file.getFile().getName()).equals(name)) {
                    setImage(file);
                }
            }
        }
    }

    /**
     * Set the imgFiles imgFiles variable to be this imageFile.
     *
     * @param imageFile a list of imageFiles contain the selected tag(s)
     */
    static void setImageFilesWithTags(ArrayList<ImageFile> imageFile) {
        imgFiles = imageFile;
    }

    /**
     * Set the image of this selected file.
     *
     * @param file the file user selected to view.
     */
    static void setImage(ImageFile file) {
        paneCenter.getChildren().remove(imageView);
        if (file != null) {
            Image img = new Image(file.getFile().toURI().toString());
            imageView = new ImageView(img);
            imageView.setFitHeight(MAGIC400);
            imageView.setFitWidth(MAGIC400);
            paneCenter.getChildren().add(imageView);
            StackPane.setMargin(imageView, new Insets(MAGIC50, MAGIC10, MAGIC50, MAGIC50));
        }
    }
}
