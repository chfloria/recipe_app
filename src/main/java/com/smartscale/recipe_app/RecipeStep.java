package com.smartscale.recipe_app;

/**
 * Simple data class that holds a single recipe step.
 * instruction: text to show
 * targetWeight: grams; zero means "no weighing required"
 *
 * @param imagePath z.B. "/images/step1.png"
 */
public record RecipeStep(String instruction, double targetWeight, String imagePath, int stepNumber) {

    public String getInstruction() { return instruction; }
    public double getTargetWeight() { return targetWeight; }
    public String getImagePath() { return imagePath; }
    public int getStepNumber() { return stepNumber; }

    public boolean requiresWeight() {
        return targetWeight > 0;
    }
}

