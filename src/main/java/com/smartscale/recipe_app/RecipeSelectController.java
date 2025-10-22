package com.smartscale.recipe_app;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RecipeSelectController {

    private MainController mainController;

    @FXML
    private Label titleLabel;
    @FXML
    private ListView<String> recipeListView;
    @FXML
    private Button btnStartRecipe;

    private Consumer<Recipe> onRecipeSelected = recipe -> {};


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setRecipes(List<String> recipes) {
        recipeListView.setItems(FXCollections.observableList(recipes));
        if (!recipes.isEmpty()) {
            recipeListView.getSelectionModel().selectFirst();
        }
    }

    public void setOnRecipeSelected(Consumer<Recipe> callback) {
        this.onRecipeSelected = callback == null ? recipe -> {} : callback;
    }

    @FXML
    private void initialize() {
        if (titleLabel != null) {
            titleLabel.setText("Wähle ein Rezept");
            titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");
        }

        if(recipeListView != null && recipeListView.getItems().isEmpty()) {
            recipeListView.setItems(FXCollections.observableList(
                RecipeStore.getAllRecipes().stream().map(Recipe::name).toList()
            ));
            if (!recipeListView.getItems().isEmpty()) {
                recipeListView.getSelectionModel().selectFirst();
            }
        }
        if (btnStartRecipe != null) {
            btnStartRecipe.setText("Start");
            btnStartRecipe.setOnAction(e -> onStart());
        }
    }

    private void onStart() {
        String name = recipeListView.getSelectionModel().getSelectedItem();
        if (name == null && !recipeListView.getItems().isEmpty()) {
            name = recipeListView.getItems().getFirst();
        }
        if (name == null) return;

        Optional<Recipe> opt = RecipeStore.findByName(name);
        if (opt.isPresent()) {
            Recipe recipe = opt.get();
            if (mainController != null) {
                mainController.loadRecipe(recipe);
                mainController.startRecipe();
            }
            onRecipeSelected.accept(recipe);
        }
    }

}
