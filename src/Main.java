import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private TextField directoryPathField;
    private TextField searchField;
    private TextArea resultArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Browser and Search");
        resultArea = new TextArea();
        resultArea.setPrefHeight(400);

        directoryPathField = new TextField();
        directoryPathField.setPromptText("Enter directory path");

        searchField = new TextField();
        searchField.setPromptText("Enter search phrase");

        var browseButton = new Button();
        browseButton.setText("Browse");
        browseButton.setOnAction(event -> browseDirectory(primaryStage));

        var searchButton = new Button();
        searchButton.setText("Search");

        var hbox = new HBox(10, directoryPathField, browseButton);
        var vbox = new VBox(10, hbox, searchField, searchButton,resultArea);

        var scene = new Scene(vbox, 600, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void browseDirectory(Stage stage) {
        var directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            directoryPathField.setText(selectedDirectory.getAbsolutePath());
        }
    }
}