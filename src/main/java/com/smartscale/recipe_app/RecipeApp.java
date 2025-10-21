package com.smartscale.recipe_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * App is the JavaFX Application. It loads the main FXML and shows the stage.
 */
public class RecipeApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/com/smartscale/recipe_app/recipe_select.fxml"));
        Parent root = Loader.load();
        RecipeSelectController controller = Loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Smart Scale Recipe App");
        stage.show();

        controller.setOnRecipeSelected(recipe -> {
            try {
                MainController mainController = new MainController();
                mainController.setStage(stage);
                mainController.loadRecipe(recipe);
                mainController.startRecipe();
                stage.setTitle("Rezeptanleitung" + recipe.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
