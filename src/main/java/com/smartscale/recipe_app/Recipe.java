package com.smartscale.recipe_app;

import java.util.List;
import java.util.Objects;

public class Recipe {
    private final String name;
    private final List<RecipeStep> steps;

    public Recipe(String name, List<RecipeStep> steps) {
        this.name = Objects.requireNonNull(name);
        this.steps = Objects.requireNonNull(steps);
    }

    public String getName() {
        return name;
    }

    public List<RecipeStep> getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return name;
    }
}
