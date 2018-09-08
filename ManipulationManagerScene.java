package view_control;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class constructs and displays the Manipulation Scene
 *
 * @author Dingyi Yu, Jiayao Lin
 * @version J.R.E 1.8.0
 */
public class ManipulationManagerScene extends Application {

    /**
     * Magic number 10
     */
    private static final int MAGIC10 = 10;

    /**
     * Magic number 500
     */
    private static final int MAGIC500 = 500;

    /**
     * Magic number 0
     */
    private static final int MAGIC0 = 0;

    /**
     * Magic number 1
     */
    private static final int MAGIC1 = 1;

    /**
     * Magic number 50
     */
    private static final int MAGIC50 = 50;

    /**
     * Magic number 20
     */
    private static final int MAGIC20 = 20;

    /**
     * Magic number 200
     */
    private static final int MAGIC200 = 200;

    /**
     * Magic number 30
     */
    private static final int MAGIC30 = 30;

    /**
     * Magic number 800
     */
    private static final int MAGIC800 = 800;

    /**
     * Magic number 950
     */
    private static final int MAGIC950 = 950;

    /**
     * Magic number 100
     */
    private static final int MAGIC100 = 100;

    /**
     * Magic number 150
     */
    private static final int MAGIC150 = 150;

    /**
     * Magic number 5
     */
    private static final int MAGIC5 = 5;

    /**
     * Magic number 15
     */
    private static final int MAGIC15 = 15;

    /**
     * Magic number 3
     */
    private static final int MAGIC3 = 3;

    /**
     * Magic number 1000
     */
    private static final int MAGIC1000 = 1000;

    /**
     * Magic number 1349
     */
    private static final int MAGIC1349 = 1349;

    /**
     * Magic number 1350
     */
    private static final int MAGIC1350 = 1350;

    /**
     * Magic number 40
     */
    private static final int MAGIC40 = 40;

    /**
     * Magic number 553
     */
    private static final int MAGIC553 = 553;

    /**
     * Magic number 600
     */
    private static final int MAGIC600 = 600;

    /**
     * Magic number 650
     */
    private static final int MAGIC650 = 650;

    /**
     * Magic number 700
     */
    private static final int MAGIC700 = 700;

    /**
     * Magic number 153
     */
    private static final int MAGIC153 = 153;

    /**
     * Magic number -40
     */
    private static final int MAGIC_40 = -40;

    /**
     * Magic number 60
     */
    private static final int MAGIC60 = 60;

    /**
     * Magic number 80
     */
    private static final int MAGIC80 = 80;

    /**
     * Magic number 210
     */
    private static final int MAGIC210 = 210;

    /**
     * Magic number 55
     */
    private static final int MAGIC55 = 55;

    /**
     * Magic number 570
     */
    private static final int MAGIC570 = 570;

    /**
     * Magic number 280
     */
    private static final int MAGIC280 = 280;

    /**
     * Magic number 120
     */
    private static final int MAGIC120 = 120;

    /**
     * Magic number 60
     */
    private static final int MAGIC_150 = -150;

    /**
     * Initialize a new label for the path of the selected image
     */
    private static Label path = new Label();

    /**
     * Initialize a imgFiles that contains the collection of ImageFile under directory
     */
    static ArrayList<ImageFile> imgFiles = new ArrayList<>();

    /**
     * Initialize a imgListView for the to display imgFiles
     */
    private static ListView<String> imgListView = new ListView<>();

    /**
     * Initialize a Stage window for this scene
     */
    private static Stage window = new Stage();

    /**
     * Initialize a StackPane paneCenter to place the imageView of the selected image
     */
    private static StackPane paneCenter = new StackPane();

    /**
     * Initialize a tagsView to place the imageView of the selected file
     */
    private static ListView<String> tagsView = new ListView<>();

    /**
     * Initialize a directoryImageFile that contains the collection of ImageFile under directory
     */
    static ArrayList<ImageFile> directoryImageFile = new ArrayList<>();

    /**
     * Initialize a new ImageFile Object
     */
    private static ImageFile inputFile;

    /**
     * Initialize a new ImageView Object
     */
    private static ImageView imageView = new ImageView();

    /**
     * Initialize a new Scene to display the selected image's log text
     */
    private static Scene logTextScene;

    /**
     * Initialize a new Scene to display all log text
     */
    private static Scene allLogTextScene;

    /**
     * Initialize a logListView for the logTextScene to display the selected image's log text
     */
    private static ListView<String> logListView = new ListView<>();

    /**
     * Initialize an allLogListView for the allLogTextScene to display all log text
     */
    private static ListView<String> allLogListView = new ListView<>();

    /**
     * Initialize an inputGridPane to format the layout of manipulation scene
     */
    private static BorderPane inputGridPane = new BorderPane();

    /**
     * Initialize an generalLayout to place all elements and buttons
     */
    private static VBox generalLayout = new VBox(MAGIC20);

    /**
     * A Directory File
     */
    private static File currentDirectory;

    /**
     * Initialize a pathArea to place the path of the selected file
     */
    private static Pane pathArea = new Pane();

    /**
     * Initialize a Desktop to get user's desktop
     */
    private static Desktop desktop = Desktop.getDesktop();

    /**
     * Path to serialize the logManager
     */
    static String logManagerPath = "./serializedLogFiles.ser";

    /**
     * Path to serialize the tagManager
     */
    static String tagManagerPath = "./serializedTagFiles.ser";

    /**
     * Path to serialize the imageFileManager
     */
    static String imageFileManagerPath = "./serializedImageFiles.ser";

    /**
     * A tagManager object
     */
    static TagManager tagManager;

    /**
     * A LogManager object
     */
    static LogManager logManager;

    /**
     * A imageFileManager object
     */
    static ImageFileManager imageFileManager;

    /**
     * Initialize a Collision Object
     */
    private static Collision collision = new Collision();

    static {
        try {
            tagManager = new TagManager(tagManagerPath);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            imageFileManager = new ImageFileManager(imageFileManagerPath);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            logManager = new LogManager(logManagerPath);
            logManager.add(" ", logManagerPath);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method is where the Application runs
     *
     * @param args use to run the Application
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Display the Scene and construct the buttons.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        window.setTitle("Image Editor");

        Button add = new Button("Add Tag");
        add.setMinWidth(MAGIC100);

        Button delete = new Button("Delete Tag");
        delete.setMinWidth(MAGIC100);

        Button selectOldTag = new Button("Select Old Tag");
        selectOldTag.setMinWidth(MAGIC100);

        Button move = new Button("Move To");
        move.setMinWidth(MAGIC100);

        Button getDirectory = new Button("Show Current Directory");
        getDirectory.setMinWidth(MAGIC150);

        Button quit = new Button("Quit The Program");
        quit.setMinWidth(MAGIC150);

        Button rename = new Button("Rename");
        rename.setMinWidth(MAGIC100);

        Button getLog = new Button("Get Log History");
        getLog.setMinWidth(MAGIC150);

        Button goBack = new Button("Go Back");
        goBack.setMinWidth(MAGIC100);

        Button back = new Button("Go Back");
        back.setMinWidth(MAGIC100);

        Button openButton = new Button("Choose A Directory");
        openButton.setMinWidth(MAGIC100);

        Button imgContainTag = new Button("Show Image with Tag");
        imgContainTag.setMinWidth(MAGIC150);

        Pane addToTagSet = new Pane();
        Tooltip addTagInstruction = new Tooltip("Click to add tags into Tag Set");
        Tooltip.install(addToTagSet, addTagInstruction);
        addToTagSet.setStyle("-fx-background-color: #c0c0c0");
        addToTagSet.setMinHeight(MAGIC20);
        addToTagSet.setMinWidth(MAGIC20);
        addToTagSet.setMaxWidth(MAGIC20);
        addToTagSet.setMaxHeight(MAGIC20);
        Line horizontal = new Line(MAGIC5, MAGIC10, MAGIC15, MAGIC10);
        horizontal.setStrokeWidth(MAGIC3);
        Line vertical = new Line(MAGIC10, MAGIC5, MAGIC10, MAGIC15);
        vertical.setStrokeWidth(MAGIC3);
        addToTagSet.getChildren().addAll(horizontal, vertical);

        Pane deleteTagHistory = new Pane();
        Tooltip deleteTagHistoryInstruction = new Tooltip("Select tag(s) and click to remove");
        Tooltip.install(deleteTagHistory, deleteTagHistoryInstruction);
        deleteTagHistory.setStyle("-fx-background-color: #c0c0c0");
        deleteTagHistory.setMinHeight(MAGIC20);
        deleteTagHistory.setMinWidth(MAGIC20);
        deleteTagHistory.setMaxWidth(MAGIC20);
        deleteTagHistory.setMaxHeight(MAGIC20);
        Line deleteLine = new Line(MAGIC5, MAGIC10, MAGIC15, MAGIC10);
        deleteLine.setStrokeWidth(MAGIC3);
        deleteTagHistory.getChildren().add(deleteLine);

        Button addToImageFile = new Button("Add Tag to Image File");
        addToImageFile.setMinWidth(MAGIC150);

        Button getAllLog = new Button(("Get All Log History"));

        setTagSetView();

        VBox logLayout = new VBox(MAGIC20);
        logTextScene = new Scene(logLayout, MAGIC1349, MAGIC1000);

        VBox allLogLayout = new VBox(MAGIC20);
        allLogTextScene = new Scene(allLogLayout, MAGIC1349, MAGIC1000);

        tagsView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        add.setOnAction(
                (ActionEvent e) -> {
                    AddTagScene.setImageFile(inputFile);
                    AddTagScene.display();
                });

        delete.setOnAction(
                (ActionEvent e) -> {
                    DeleteTagScene.setImageFile(inputFile);
                    DeleteTagScene.display();
                });
        selectOldTag.setOnAction(
                (ActionEvent e) -> {
                    SelectTagScene.setImageFile(inputFile);
                    SelectTagScene.display();
                });
        move.setOnAction(
                (ActionEvent e) -> {
                    MoveFileScene.setImageFile(inputFile);
                    MoveFileScene.display();
                });

        quit.setOnAction((ActionEvent event) -> window.close());

        rename.setOnAction(
                (ActionEvent e) -> {
                    FileRenameScene.setImageFile(inputFile);
                    FileRenameScene.display();
                });

        logLayout.getChildren().add(goBack);
        allLogLayout.getChildren().add(back);

        getLog.setOnAction(
                (ActionEvent e) -> {
                    logLayout.getChildren().remove(logListView);
                    logListView.getItems().clear();

                    if (inputFile != null) {
                        for (String logHistory : inputFile.getLog()) {
                            logListView.getItems().add(logHistory);
                        }
                    }
                    logLayout.getChildren().add(logListView);
                    window.setScene(logTextScene);
                });

        getAllLog.setOnAction(
                (ActionEvent e) -> {
                    allLogLayout.getChildren().remove(allLogListView);
                    allLogListView.getItems().clear();
                    for (String allLogHistory : logManager.getLogHistory()) {
                        allLogListView.getItems().add(allLogHistory);
                    }
                    allLogLayout.getChildren().add(allLogListView);
                    window.setScene(allLogTextScene);
                });

        openButton.setOnAction(
                (ActionEvent e) -> {
                    directoryImageFile.clear();
                    currentDirectory = null;
                    openButtonClicked();
                    setTagSetView();
                });

        getDirectory.setOnAction(
                (ActionEvent event) -> {
                    if (inputFile != null) {
                        String OS = System.getProperty("os.name").toLowerCase();
                        Boolean isLinux = OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0;
                        String path = inputFile.getFile().getParent();
                        Runtime runtime = Runtime.getRuntime();

                        if (isLinux) {
                            try {
                                runtime.exec("nautilus " + path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            File file = new File(path);
                            try {
                                desktop.open(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        addToTagSet.setOnMouseClicked(event -> AddToTagSet.display());

        deleteTagHistory.setOnMouseClicked(
                event -> {
                    try {
                        deleteTagHistoryButtonClicked();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        addToImageFile.setOnAction(
                event -> {
                    try {
                        addTagToFileButtonClicked();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        imgContainTag.setOnAction(
                event -> {
                    ObservableList<String> chooseTags = tagsView.getSelectionModel().getSelectedItems();
                    ArrayList<ImageFile> chooseFile = new ArrayList<>();

                    if (chooseTags.size() >= 1) {
                        for (String chooseTag : chooseTags) {
                            for (ImageFile file : imgFiles) {
                                if (file.getExistTag().contains(chooseTag) && !(chooseFile.contains(file))) {
                                    chooseFile.add(file);
                                }
                            }
                        }
                        ContainTagScene.setImageFilesWithTags(chooseFile);
                        ContainTagScene.setImage(null);
                        ContainTagScene.display();
                    }
                });

        inputGridPane.setPrefSize(MAGIC800, MAGIC800);
        Label tagHistory = new Label("Tag Set");
        pathArea.setPrefSize(MAGIC200, MAGIC30);
        pathArea.getChildren().add(path);
        Line divTop = new Line(MAGIC10, 0, MAGIC10, MAGIC40);
        divTop.setStroke(Paint.valueOf("#c0c0c0"));
        divTop.setTranslateX(MAGIC553);
        ToolBar toolbar = new ToolBar();
        toolbar
                .getItems()
                .addAll(
                        openButton, getAllLog, getDirectory, divTop, deleteTagHistory, tagHistory, addToTagSet);
        toolbar.setPadding(new Insets(0, 0, 0, 0));
        deleteTagHistory.setTranslateX(MAGIC600);
        deleteTagHistory.setTranslateY(MAGIC1);
        tagHistory.setTranslateX(MAGIC650);
        addToTagSet.setTranslateX(MAGIC700);
        addToTagSet.setTranslateY(MAGIC1);

        Line divBottom = new Line(MAGIC10, MAGIC_40, MAGIC10, MAGIC60);
        divBottom.setStroke(Paint.valueOf("#c0c0c0"));
        divBottom.setTranslateX(MAGIC153);
        ToolBar toolbarBottom = new ToolBar();
        toolbarBottom.setMinHeight(MAGIC100);
        toolbarBottom.setPadding(new Insets(MAGIC0, MAGIC0, MAGIC0, MAGIC10));

        toolbarBottom
                .getItems()
                .addAll(
                        quit,
                        getLog,
                        add,
                        delete,
                        selectOldTag,
                        rename,
                        move,
                        divBottom,
                        addToImageFile,
                        imgContainTag);

        quit.setTranslateX(MAGIC0);
        quit.setTranslateY(MAGIC20);
        getLog.setTranslateX(MAGIC_150);
        getLog.setTranslateY(-MAGIC30);
        add.setTranslateX(MAGIC_40);
        add.setTranslateY(-MAGIC30);
        delete.setTranslateX(0);
        delete.setTranslateY(-MAGIC30);
        selectOldTag.setTranslateX(MAGIC40);
        selectOldTag.setTranslateY(-MAGIC30);
        rename.setTranslateX(MAGIC80);
        rename.setTranslateY(-MAGIC30);
        move.setTranslateX(MAGIC120);
        move.setTranslateY(-MAGIC30);
        addToImageFile.setTranslateX(MAGIC210);
        addToImageFile.setTranslateY(-MAGIC30);
        imgContainTag.setTranslateX(MAGIC55);
        imgContainTag.setTranslateY(MAGIC20);

        paneCenter.setStyle("-fx-background-color: #f5f5dc");
        paneCenter.setMinHeight(MAGIC570);
        paneCenter.setMaxHeight(MAGIC570);
        StackPane tagsViewPane = new StackPane();
        tagsViewPane.setPrefWidth(MAGIC280);
        tagsViewPane.getChildren().add(tagsView);
        inputGridPane.setCenter(paneCenter);
        inputGridPane.setTop(toolbar);
        inputGridPane.setLeft(imgListView);
        inputGridPane.setRight(tagsViewPane);
        inputGridPane.setBottom(toolbarBottom);
        inputGridPane.getChildren().add(tagHistory);
        generalLayout.getChildren().addAll(inputGridPane);

        final Scene general = new Scene(generalLayout, MAGIC1350, MAGIC950);
        goBack.setOnAction((ActionEvent event) -> window.setScene(general));
        back.setOnAction((ActionEvent event) -> window.setScene(general));

        window.setScene(general);
        window.show();
    }

    /**
     * Add all potential ImgFiles under directory inside directoryImageFile
     * and set up imageFileManager and tagManager
     *
     * @param fileCollection: All files inside a direction
     */
    private void setAction(File fileCollection) {
        for (File file : fileCollection.listFiles()) {
            if (file.getName().toLowerCase().endsWith(".jpg")
                    || file.getName().toLowerCase().endsWith(".jpeg")
                    || file.getName().toLowerCase().endsWith(".gif")
                    || file.getName().toLowerCase().endsWith(".png")) {
                boolean checkFileExist = false;
                ImageFile inputImageFile = null;
                try {
                    inputImageFile = new ImageFile(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ArrayList<ImageFile> imageFiles = imageFileManager.getSerializedList();

                for (ImageFile imgFile : imageFiles) {
                    if (imgFile.equals(inputImageFile)) {
                        ArrayList<String> currentTagList = tagManager.getSerializedList();
                        ArrayList<String> existTagList = imgFile.getExistTag();
                        for (String tag : existTagList) {
                            if (!currentTagList.contains(tag)) {
                                try {
                                    tagManager.add(tag, tagManagerPath);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                        inputImageFile = imgFile;
                        checkFileExist = true;
                    }
                }
                if (!checkFileExist) {
                    try {
                        ArrayList<String> autoAddTags = inputImageFile.getExistTag();
                        for (String tag : autoAddTags) {
                            tagManager.add(tag, tagManagerPath);
                        }
                        imageFileManager.add(inputImageFile, imageFileManagerPath);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                directoryImageFile.add(inputImageFile);
            } else if (file.isDirectory()) {
                setAction(file);
            }
        }
    }

    /**
     * Open the directory chooser and set up the imgFiles and ImageListView
     */
    private void openButtonClicked() {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        currentDirectory = directoryChooser.showDialog(window);
        if (currentDirectory != null) {
            setAction(currentDirectory);
        }
        imgFiles = directoryImageFile;
        setImageListView(imgFiles);
    }

    /**
     * Get the Image that user selected and show it onto the scene.
     */
    private static void setImage() {
        paneCenter.getChildren().remove(imageView);
        if (inputFile != null) {
            Image img = new Image(inputFile.getFile().toURI().toString());
            imageView = new ImageView(img);
            imageView.setFitHeight(MAGIC500);
            imageView.setFitWidth(MAGIC650);

            paneCenter.getChildren().add(imageView);
            StackPane.setMargin(imageView, new Insets(MAGIC50, MAGIC10, MAGIC50, MAGIC50));
        }
    }

    /**
     * Pass in the file
     *
     * @param imageFile the imageFile selected
     */
    static void setFile(ImageFile imageFile) throws IOException {
        inputFile = imageFile;
    }

    private static void buttonClicked() throws IOException {
        if (imgListView.getSelectionModel().getSelectedIndices().size() == 1) {
            Integer index = imgListView.getSelectionModel().getSelectedIndices().get(0);
            setFile(imgFiles.get(index));
            setImage();
        }
    }

    /**
     * set the ImageListView to display the list of images under the selected directory.
     *
     * @param imgList: the collection of ImageFile under selected directory
     */
    static void setImageListView(ArrayList<ImageFile> imgList) {
        imgListView.getItems().clear();
        for (ImageFile file : imgList) {
            imgListView.getItems().add(file.getFile().getName());
        }
        imgListView.setOnMouseClicked(
                event -> {
                    try {
                        buttonClicked();
                        if (inputFile != null) {
                            setPath(inputFile);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * Set the path of the file selected to display.
     *
     * @param inputFile: the file user selected to view.
     */
    static void setPath(ImageFile inputFile) {
        pathArea.getChildren().clear();
        if (inputFile != null) {
            path.setText(ManipulationManagerScene.inputFile.getFile().getAbsolutePath());
            pathArea.getChildren().add(path);
            paneCenter.getChildren().remove(pathArea);
            paneCenter.getChildren().add(pathArea);
        }
    }

    /**
     * Set all history tag set list view
     */
    static void setTagSetView() {
        tagsView.getItems().clear();
        ArrayList<String> tagHistory = tagManager.getSerializedList();
        for (String tag : tagHistory) {
            tagsView.getItems().add(tag);
        }
    }

    /**
     * Delete the selected tag from the tag set
     *
     * @throws IOException: throw an IOException
     */
    private void deleteTagHistoryButtonClicked() throws IOException {
        ObservableList<String> tags = tagsView.getSelectionModel().getSelectedItems();
        for (String tag : tags) {
            tagManager.delete(tag, tagManagerPath);
        }
        setTagSetView();
    }

    /**
     * Add the selected tag to file
     *
     * @throws IOException: throw an IOException
     */
    private void addTagToFileButtonClicked() throws IOException {

        ObservableList<String> tags = tagsView.getSelectionModel().getSelectedItems();
        if (tags.size() >= 1 && inputFile != null) {
            StringBuilder collection = new StringBuilder();
            for (String tag : tags) {
                collection.append(tag);
            }
            String potentialName = collision.changeNameAdd(inputFile.getFile().getName(), collection.toString());
            if (!collisionAdd(potentialName)) {
                for (String tag : tags) {
                    ImageFile saveCurrent = inputFile;
                    String logHistory = inputFile.addTag(tag);
                    imageFileManager.add(inputFile, imageFileManagerPath);
                    imageFileManager.delete(saveCurrent, imageFileManagerPath);
                    logManager.add(logHistory, logManagerPath);
                    setImageListView(imgFiles);
                }
                setPath(inputFile);
            }
        }
    }

    /**
     * Return the directory File currentDirectory
     *
     * @return Directory that this inputfile is currently in
     */
    static File getCurrentDirectory() {
        return currentDirectory;
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
