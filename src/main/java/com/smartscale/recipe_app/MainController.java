package com.smartscale.recipe_app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private BorderPane root;
    public final List<RecipeStep> steps = new ArrayList<>();
    private int stepIndex = 0;

    public void setStage(Stage stage) {
        this.root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }

    public void loadRecipe(Recipe recipe) {
        steps.clear();
        steps.addAll(recipe.steps());
        stepIndex = 0;
    }

    public boolean loadRecipeByName(String recipeName) {
        return RecipeStore.findByName(recipeName).map(r -> {
            loadRecipe(r);
            return true;
        }).orElse(false);
    }

    public void startRecipe() {
        if (!steps.isEmpty()) {
            showStep(steps.get(stepIndex));
        }
    }

    /** Go to previous step */
    public void showBack() {
        if (stepIndex > 0) {
            stepIndex--;
            showStep(steps.get(stepIndex));
        }
    }

    /** Go to next step */
    public void showNext() {
        if (stepIndex < steps.size() - 1) {
            stepIndex++;
            showStep(steps.get(stepIndex));
        }
    }

    public void showStep(RecipeStep step) {
        try {
            FXMLLoader loader;
            Parent content;
            if (step.requiresWeight()) {
                loader = new FXMLLoader(getClass().getResource("/com/smartscale/recipe_app/step_with_weight.fxml"));
                content = loader.load();
                ControllerWithWeight controller = loader.getController();
                controller.setMainController(this);
                controller.showStepwithWeight(step);
            } else {
                loader = new FXMLLoader(getClass().getResource("/com/smartscale/recipe_app/step_without_weight.fxml"));
                content = loader.load();
                ControllerWithoutWeight controller = loader.getController();
                controller.setMainController(this);
                controller.showStepwithoutWeight(step);
            }

            root.setCenter(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
