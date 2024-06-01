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
        searchButton.setOnAction(event -> searchFiles(primaryStage));

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

    private void searchFiles() {
        String directoryPath = directoryPathField.getText();
        if (directoryPath.isEmpty()) {
            resultArea.setText("Please provide a directory path.");
            return;
        }

        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            resultArea.setText("The provided path is not a directory.");
            return;
        }

        StringBuilder results = new StringBuilder();
        listFilesInDirectory(directory, results);
        resultArea.setText(results.toString());
    }

    private void listFilesInDirectory(File directory, StringBuilder results) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    results.append(file.getName()).append("\n");
                }
            }
        }
    }
}