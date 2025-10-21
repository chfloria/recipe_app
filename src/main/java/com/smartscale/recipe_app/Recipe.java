package com.smartscale.recipe_app;

import java.util.List;
import java.util.Objects;

public record Recipe(String name, List<RecipeStep> steps) {
    public Recipe(String name, List<RecipeStep> steps) {
        this.name = Objects.requireNonNull(name);
        this.steps = Objects.requireNonNull(steps);
    }

    @Override
    public String toString() {
        return name;
    }
}
