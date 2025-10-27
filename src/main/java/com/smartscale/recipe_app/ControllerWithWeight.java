package com.smartscale.recipe_app;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.application.Platform;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;

import java.util.ArrayDeque;

/**
 * Controller for step_with_weight.fxml
 *
 * Responsibilities:
 * - hold the list of RecipeStep objects (the lemon cake recipe)
 * - show the right UI for weighing vs. non-weighing steps
 * - create a circular fill that fills from bottom -> top using a clip rectangle
 * - animate the fill smoothly when the user updates the simulated weight
 *
 * Notes about the circle implementation:
 * - We create two circles with the same center: a "fillCircle" (colored) and an outline circle (stroke only).
 * - The fillCircle is clipped by a Rectangle (fillClip). By changing the rectangle's height and y,
 *   we expose more of the fill circle from bottom to top.
 * - All circle nodes are positioned inside a Pane whose size equals the circle bounds, then wrapped in a StackPane
 *   to preserve perfect centering inside the FXML layout.
 */
public class ControllerWithWeight {

    // FXML-injected nodes
    @FXML private Label instructionLabel;
    @FXML private ImageView pictureView;
    @FXML private Pane scaleContainer;
    @FXML private Label weightLabel;
    @FXML private Label warningLabel;
    @FXML private Button btnTare;
    @FXML private Button btnBack;
    @FXML private Button btnNext;
    @FXML private Button btnSpeak;

    private ScaleService scaleService;
    private MainController mainController;
    private RecipeStep currentStep;

    public void setMainController(MainController controller) {this.mainController = controller;}

    // sliding average window for weight readings
    private static final int AVERAGE_WINDOW = 25;
    private final ArrayDeque<Integer> weightWindow = new ArrayDeque<>();

    private Rectangle fillRect;
    private final double WIDTH = 200;
    private final double HEIGHT = WIDTH * 1.5;
    private final double HEIGHT_BIG = HEIGHT * 1.5;

    /**
     * initialize() is called by the FXMLLoader after the FXML nodes are injected.
     * Here we:
     * - build the recipe steps
     * - wire up the UI button actions
     * - show the first step
     */
    @FXML
    public void initialize() {
        createContainerGraphics();

        // Initialize UI controls
        btnBack.setOnAction(e -> onBack());
        btnNext.setOnAction(e -> onNext());
        btnTare.setOnAction(e -> {
            try {
                scaleService.tare(); // ✅ Call tare on ScaleService
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnSpeak.setOnAction(e -> onSpeak(currentStep));

        // Small initial state
        weightLabel.setText("");
        warningLabel.setText("");

        // Initialize the ScaleService
        try {
            // Use your actual calibration gain value here
            scaleService = new ScaleService(47641820.4092032);

            scaleService.setWeightListener(weight -> {
                Platform.runLater(() -> {
                    // add weight to window
                    weightWindow.addLast(weight);
                    if (weightWindow.size() > AVERAGE_WINDOW) {
                        weightWindow.removeFirst();
                    }
                    // compute average
                    double avg = weightWindow.stream().mapToInt(Integer::intValue).average().orElse(0);
                    weightLabel.setText(String.format("%dg", Math.round(avg)));
                    animateFillToWeight((int)Math.round(avg));
                });
            });

            scaleService.start();
            scaleService.tare();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates the visual rectangle and places them inside the FXML `scaleContainer` Pane.
     */
    public void createContainerGraphics() {
        // colored filling rectangle (initially orange)
        fillRect = new Rectangle(0, 0, WIDTH, 0);
        fillRect.setFill(Color.ORANGE);

        // outline rectangle (on top so stroke is visible)
        // outline rectangle on top so border remains visible
        // Rectangle outlineRect = new Rectangle(0, 0, WIDTH, HEIGHT_BIG);
        Path outlineRect = new Path(
                new MoveTo(0, 0),
                new LineTo(0, HEIGHT_BIG),    // left side
                new LineTo(WIDTH, HEIGHT_BIG),// bottom
                new LineTo(WIDTH, 0)          // right side
        );
        outlineRect.setFill(Color.TRANSPARENT);
        outlineRect.setStroke(Color.BLACK);
        outlineRect.setStrokeWidth(3);

        // target line across the Rectangle
        Line targetLine = new Line(0, 0.5 * HEIGHT, WIDTH, 0.5 * HEIGHT);
        targetLine.setStroke(Color.BLUE);
        targetLine.setStrokeWidth(5);

        // place wrapper into the FXML container (clear first)
        scaleContainer.getChildren().clear();
        scaleContainer.getChildren().addAll(fillRect, outlineRect, targetLine);
    }

    /** Go to previous step */
    @FXML
    public void onBack() {
        try {
            shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mainController.showBack(); }

    /** Go to next step */
    public void onNext() {
        try {
            shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
    public void showStepwithWeight(RecipeStep step) {
        this.currentStep = step;
        instructionLabel.setText(step.getInstruction());
        // load image if available
        if (step.imagePath() != null && !step.imagePath().isEmpty()) {
            Image img = new Image(getClass().getResourceAsStream(step.getImagePath()));
            pictureView.setImage(img);
        } else {
            pictureView.setImage(null);
        }
        warningLabel.setText("");
        btnBack.setVisible(step.getStepNumber() > 0);
        btnNext.setVisible(step.getStepNumber() < mainController.steps.size() - 1);
    }

    /**
     * Animate the clip rectangle and update color/warning appropriately.
     * weight: weight in grams (simulated input)
     */
    private void animateFillToWeight(int weight) {
        RecipeStep step = this.currentStep;
        double target = step.getTargetWeight();
        if (target <= 0) {
            // nothing to animate
            fillRect.setHeight(0);
            fillRect.setFill(Color.LIGHTGRAY);
            weightLabel.setText(String.format("%dg", weight));
            warningLabel.setText("");
            return;
        }

        // compute progress (0..1)
        double progress = clamp(weight / target, 0.0, 1.5);
        double newHeight = HEIGHT * progress;
        double newY = HEIGHT_BIG - newHeight;

        // animate clip height and y properties (smooth bottom->top)
        Timeline t = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(fillRect.heightProperty(), fillRect.getHeight()),
                        new KeyValue(fillRect.yProperty(), fillRect.getY())
                ),
                new KeyFrame(Duration.millis(100),
                        new KeyValue(fillRect.heightProperty(), newHeight),
                        new KeyValue(fillRect.yProperty(), newY)
                )
        );
        t.play();

        // update color & warning instantly for crisp feedback
        // tolerance for "green" state (5% of target)
        double TOLERANCE_FACTOR = 0.05;
        double tol = target * TOLERANCE_FACTOR;
        if (Math.abs(weight - target) <= tol) {
            fillRect.setFill(Color.LIGHTGREEN);
            warningLabel.setText("");
        } else if (weight > target) {
            fillRect.setFill(Color.RED);
            warningLabel.setText("Zu viel!");
        } else {
            fillRect.setFill(Color.ORANGE);
            warningLabel.setText("");
        }

        weightLabel.setText(String.format("%dg", weight));
    }

    private static double clamp(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }

    public void shutdown() {
        try {
            if (scaleService != null) {
                scaleService.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

