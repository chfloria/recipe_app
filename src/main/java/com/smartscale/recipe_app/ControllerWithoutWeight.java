package com.smartscale.recipe_app;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * Controller for step_without_weight.fxml
 */
public class ControllerWithoutWeight {

    // FXML-injected nodes
    @FXML private Label instructionLabel;
    @FXML private ImageView pictureView;
    @FXML private Button btnBack;
    @FXML private Button btnNext;
    @FXML private Button btnSpeak;

    private MainController mainController;
    private RecipeStep currentStep;

    public void setMainController(MainController controller) {this.mainController = controller;}

    /**
     * initialize() is called by the FXMLLoader after the FXML nodes are injected.
     * Here we:
     * - build the recipe steps
     * - wire up the UI button actions
     * - show the first step
     */
    @FXML
    public void initialize() {
        // Initialize UI controls
        btnBack.setOnAction(e -> onBack());
        btnNext.setOnAction(e -> onNext());
        btnSpeak.setOnAction(e -> onSpeak(currentStep));
    }

    /** Go to previous step */
    private void onBack() {
        mainController.showBack();
    }

    /** Go to next step */
    private void onNext() {
        mainController.showNext();
    }

    public  void onSpeak(RecipeStep step) {
        this.currentStep = step;
        mainController.speakstep(step.getInstruction());
    }

    /**
     * Display the step at index and update which UI elements are visible.
     * If the step requires weight, show the circle and controls; otherwise hide them.
     */
    public void showStepwithoutWeight(RecipeStep step) {
        this.currentStep = step;
        instructionLabel.setText(step.getInstruction());
        // load image if available
        if (step.imagePath() != null && !step.imagePath().isEmpty()) {
            Image img = new Image(getClass().getResourceAsStream(step.getImagePath()));
            pictureView.setImage(img);
        } else {
            pictureView.setImage(null);
        }
        btnBack.setVisible(step.getStepNumber() > 0);
        btnNext.setVisible(step.getStepNumber() < mainController.steps.size() - 1);
    }
}
