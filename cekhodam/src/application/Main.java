package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private ProgressBar progressBar;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cek Khodam");

        // Label and text field for name
        Label nameLabel = new Label("Masukkan Nama:");
        nameLabel.getStyleClass().add("label");
        TextField nameTextField = new TextField();
        nameTextField.getStyleClass().add("text-field");
        VBox nameContainer = new VBox(5);
        nameContainer.getChildren().addAll(nameLabel, nameTextField);
        nameContainer.setPadding(new Insets(20));

        // Button for generate random name
        Button generateButton = new Button("Cek khodam");
        generateButton.getStyleClass().add("button");

     // Button container with adjusted padding to move the button higher
        HBox buttonContainer = new HBox(10, generateButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(10, 0, 0, 0)); 
        // Text area for displaying results
        TextArea resultTextArea = new TextArea();
        resultTextArea.setEditable(false);
        resultTextArea.getStyleClass().add("text-area");
        resultTextArea.setPrefSize(300, 200);
        resultTextArea.setStyle("-fx-font-size: 20px;");// Set preferred size for text area

        // Progress bar for loading indication
        progressBar = new ProgressBar();
        progressBar.getStyleClass().add("progress-bar");
        progressBar.setVisible(false); // Initially hidden
        progressBar.setPrefWidth(200); // Adjust width as needed

        // Main layout
        VBox layout = new VBox(20); // Increase vertical spacing
        layout.getChildren().addAll(nameContainer, buttonContainer, progressBar, resultTextArea);
        layout.setPadding(new Insets(40)); // Increase padding
        layout.setAlignment(Pos.CENTER);
        // Action to generate name on button click
        generateButton.setOnAction(event -> {
            // Show loading progress
            progressBar.setVisible(true);
            progressBar.setProgress(-1); // Indeterminate state

            // Generate random name
            generateRandomName(nameTextField, resultTextArea);
        });

        // Main scene
        Scene scene = new Scene(layout, 500, 500); // Adjust width and height as needed
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void generateRandomName(TextField nameTextField, TextArea resultTextArea) {
        // Create a task for generating character name
        Task<String> generateNameTask = new Task<>() {
            @Override
            protected String call() throws Exception {
                // Simulate some delay
                Thread.sleep(2000);

                // Generate random name
                return generateRandomNameFromList();
            }
        };

        // When task is successfully completed
        generateNameTask.setOnSucceeded(e -> {
            String characterName = generateNameTask.getValue();
            Platform.runLater(() -> {
                resultTextArea.setText(characterName);
            });

            // Hide progress bar
            progressBar.setVisible(false);
        });

        // When task fails
        generateNameTask.setOnFailed(e -> {
            progressBar.setVisible(false);
            Platform.runLater(() -> {
                resultTextArea.setText("Failed to generate random name.");
            });
        });

        // Start the task in a new thread
        new Thread(generateNameTask).start();
    }

    private String generateRandomNameFromList() {
        String[] names = {"raja iblis", "martabak telor", "ular tangga", "bolang", "dede inoen", "balmond", "tunggu kiris", "Kucing Isriwil", "Kak Gem", "Pisang Coklat", "Nenek Unta", "Ronaldo", "Banteng PEDEI", "Jotaro", "Mobil duduk", "emyu", "conditioner", "kucing palestina", "kunti bogel", "sundel bolong", "kim jong un", "laptop bekas", "tanah kusir", "mega kill", "teh puan", "temen seken", "putin", "prabroro", "janggar", "must a nice"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    public static void main(String[] args) {
        launch(args);
    }
}
