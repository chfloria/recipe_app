package com.smartscale.recipe_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RecipeStore {
    private static final List<Recipe> recipes = new ArrayList<>();

    static {
        List<RecipeStep> steps = new ArrayList<>();
        steps.add(new RecipeStep("1. Stelle den Ofen auf 180 Grad ein.", 0, "/images/180.jpg", 0));
        steps.add(new RecipeStep("2. Stelle die Schüssel auf die Waage.", 0, "/images/schussel_waage.jpg", 1));
        steps.add(new RecipeStep("3. Gib 170 Gramm Butter in die Schüssel.", 170, "/images/butter.jpg", 2));
        steps.add(new RecipeStep("4. Gib 170 Gramm Zucker in die Schüssel.", 170, "/images/zucker.jpg", 3));
        steps.add(new RecipeStep("5. Reibe die Schale von 1 Zitrone mit der Reibe in die Schüssel.", 0, "/images/zitrone_schale.jpg", 4));
        steps.add(new RecipeStep("6. Nimm die Küchenmaschine hervor.", 0, "/images/kuchenmaschine.jpg", 5));
        steps.add(new RecipeStep("7. Setze den Schwingbesen in die Küchenmaschine ein.", 0, "/images/schwingbesen.jpg", 6));
        steps.add(new RecipeStep("8. Schlage alles mit der Küchenmaschine schaumig.", 0, "/images/schaumig.png", 7));
        steps.add(new RecipeStep("9. Gib 1 Ei in die Schüssel (1/3).", 0, "/images/ei.png", 8));
        steps.add(new RecipeStep("10. Gib 1 Ei in die Schüssel (2/3).", 0, "/images/ei.png", 9));
        steps.add(new RecipeStep("11. Gib 1 Ei in die Schüssel (3/3).", 0, "/images/ei.png", 10));
        steps.add(new RecipeStep("12. Verrühre alles mit der Küchenmaschine.", 0, "/images/vermengen.jpg", 11));
        steps.add(new RecipeStep("13. Gib 170 Gramm Mehl in die Schüssel.", 170, "/images/mehl.jpg", 12));
        steps.add(new RecipeStep("14. Gib 6 Gramm Backpulver in die Schüssel.", 6, "/images/backpulver.jpg", 13));
        steps.add(new RecipeStep("15. Verrühre alles der Küchenmaschine.", 0, "/images/vermengen.jpg", 14));
        steps.add(new RecipeStep("16. Nimm die Kastenform hervor.", 0, "/images/kastenform.jpg", 15));
        steps.add(new RecipeStep("15. Lege Backpapier in die Kastenform.", 0, "/images/kastenform_backpapier.jpg", 16));
        steps.add(new RecipeStep("16. Fülle den Teig in die Kastenform ab.", 0, "/images/teig_kastenform.jpg", 17));
        steps.add(new RecipeStep("17. Stelle die Kastenform in den Ofen.", 0, "/images/kastenform_ofen.jpg", 18));
        steps.add(new RecipeStep("18. Stelle den Ofen auf 45 Minuten.", 0, "/images/45.jpg", 19));
        steps.add(new RecipeStep("19. Stelle den Messbecher auf die Waage.", 0, "/images/messbecher.jpg", 20));
        steps.add(new RecipeStep("20. Gib 25 Gramm Zucker in den Messbecher.", 25, "/images/zucker.jpg", 21));
        steps.add(new RecipeStep("21. Gib 25 Gramm Wasser in den Messbecher.", 25, "/images/wasser.jpg", 22));
        steps.add(new RecipeStep("22. Presse eine Zitrone mit der Saftpresse aus.", 0, "/images/zitrone_auspressen.jpeg", 23));
        steps.add(new RecipeStep("23. Gib den Zitronensaft in den Messbecher.", 0, "/images/zitronensaft.jpg", 24));
        steps.add(new RecipeStep("24. Rühre alles mit einem Löffel um.", 0, "/images/löffel.jpg", 25));
        steps.add(new RecipeStep("25. Nimm den Kuchen aus dem Ofen.", 0, "/images/kastenform_ofen.jpg", 26));
        steps.add(new RecipeStep("26. Lass den Kuchen auskühlen.", 0, "/images/auskühlen.jpg", 27));
        steps.add(new RecipeStep("27. Stich mit einer Gabel viele kleine Löcher in den Kuchen.", 0, "/images/stechen.jpg", 28));
        steps.add(new RecipeStep("28. Kipp den Messbecher über den Kuchen.", 0, "/images/guss.jpg", 29));
        steps.add(new RecipeStep("29. Nimm den Kuchen aus der Form heraus.", 0, "/images/fertig.jpg", 30));
        steps.add(new RecipeStep("30. Super, du bist fertig!.", 0, "/images/hacken.png", 31));

        recipes.add(new Recipe("Zitronenkuchen", steps));
    }

    public static List<Recipe> getAllRecipes() {
        return Collections.unmodifiableList(recipes);
    }

    public static Optional<Recipe> findByName(String name) {
        return recipes.stream()
                .filter(r -> r.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
