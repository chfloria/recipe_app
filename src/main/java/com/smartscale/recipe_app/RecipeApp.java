package com.smartscale.recipe_app;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * App is the JavaFX Application. It loads the main FXML and shows the stage.
 */
public class RecipeApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MainController mainController = new MainController();
        mainController.setStage(stage);
        mainController.createRecipeSteps();
        stage.setTitle("Rezeptanleitung - Prototyp");
        mainController.startRecipe();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        // Get controller and shut down scale
        // You can pass controller reference via FXMLLoader if needed
    }

    // A standard main so you can also run App directly
    public static void main(String[] args) {
        launch(args);
    }
}
