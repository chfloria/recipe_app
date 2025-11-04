package com.smartscale.recipe_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RecipeStore {
    private static final List<Recipe> recipes = new ArrayList<>();

    static {
        List<RecipeStep> lemon = new ArrayList<>();
        lemon.add(new RecipeStep("Stelle den Ofen auf 180 Grad ein.", 0, "/images/180.jpg", 0));
        lemon.add(new RecipeStep("Stelle die Schüssel auf die Waage.", 0, "/images/schussel_waage.jpg", 1));
        lemon.add(new RecipeStep("Gib 170 Gramm Butter in die Schüssel.", 170, "/images/butter.jpg", 2));
        lemon.add(new RecipeStep("Gib 170 Gramm Zucker in die Schüssel.", 170, "/images/zucker.jpg", 3));
        lemon.add(new RecipeStep("Reibe die Schale von 1 Zitrone mit der Reibe in die Schüssel.", 0, "/images/zitrone_schale.jpg", 4));
        lemon.add(new RecipeStep("Nimm die Küchenmaschine hervor.", 0, "/images/kuchenmaschine.jpg", 5));
        lemon.add(new RecipeStep("Setze den Schwingbesen in die Küchenmaschine ein.", 0, "/images/schwingbesen.jpg", 6));
        lemon.add(new RecipeStep("Schlage alles mit der Küchenmaschine schaumig.", 0, "/images/schaumig.png", 7));
        lemon.add(new RecipeStep("Gib 1 Ei in die Schüssel (1/3).", 0, "/images/ei.png", 8));
        lemon.add(new RecipeStep("Gib 1 Ei in die Schüssel (2/3).", 0, "/images/ei.png", 9));
        lemon.add(new RecipeStep("Gib 1 Ei in die Schüssel (3/3).", 0, "/images/ei.png", 10));
        lemon.add(new RecipeStep("Verrühre alles mit der Küchenmaschine.", 0, "/images/vermengen.jpg", 11));
        lemon.add(new RecipeStep("Gib 170 Gramm Mehl in die Schüssel.", 170, "/images/mehl.jpg", 12));
        lemon.add(new RecipeStep("Gib 6 Gramm Backpulver in die Schüssel.", 6, "/images/backpulver.jpg", 13));
        lemon.add(new RecipeStep("Verrühre alles der Küchenmaschine.", 0, "/images/vermengen.jpg", 14));
        lemon.add(new RecipeStep("Nimm die Kastenform hervor.", 0, "/images/kastenform.jpg", 15));
        lemon.add(new RecipeStep("Lege Backpapier in die Kastenform.", 0, "/images/kastenform_backpapier.jpg", 16));
        lemon.add(new RecipeStep("Fülle den Teig in die Kastenform ab.", 0, "/images/teig_kastenform.jpg", 17));
        lemon.add(new RecipeStep("Stelle die Kastenform in den Ofen.", 0, "/images/kastenform_ofen.jpg", 18));
        lemon.add(new RecipeStep("Stelle den Ofen auf 45 Minuten.", 0, "/images/45.jpg", 19));
        lemon.add(new RecipeStep("Stelle den Messbecher auf die Waage.", 0, "/images/messbecher.jpg", 20));
        lemon.add(new RecipeStep("Gib 25 Gramm Zucker in den Messbecher.", 25, "/images/zucker.jpg", 21));
        lemon.add(new RecipeStep("Gib 25 Gramm Wasser in den Messbecher.", 25, "/images/wasser.jpg", 22));
        lemon.add(new RecipeStep("Presse eine Zitrone mit der Saftpresse aus.", 0, "/images/zitrone_auspressen.jpeg", 23));
        lemon.add(new RecipeStep("Gib den Zitronensaft in den Messbecher.", 0, "/images/zitronensaft.jpg", 24));
        lemon.add(new RecipeStep("Rühre alles mit einem Löffel um.", 0, "/images/löffel.jpg", 25));
        lemon.add(new RecipeStep("Nimm den Kuchen aus dem Ofen.", 0, "/images/kastenform_ofen.jpg", 26));
        lemon.add(new RecipeStep("Lass den Kuchen auskühlen.", 0, "/images/auskühlen.jpg", 27));
        lemon.add(new RecipeStep("Stich mit einer Gabel viele kleine Löcher in den Kuchen.", 0, "/images/stechen.jpg", 28));
        lemon.add(new RecipeStep("Kipp den Messbecher über den Kuchen.", 0, "/images/guss.jpg", 29));
        lemon.add(new RecipeStep("Nimm den Kuchen aus der Form heraus.", 0, "/images/fertig.jpg", 30));
        lemon.add(new RecipeStep("Super, du bist fertig!.", 0, "/images/hacken.png", 31));
        recipes.add(new Recipe("Zitronenkuchen", lemon));

        List<RecipeStep> ketchup = new ArrayList<>();
        ketchup.add(new RecipeStep("Nimm die Waage hervor.", 0, "", 0));
        ketchup.add(new RecipeStep("Lege 100 Gramm Zwiebeln auf die Waage.", 100, "", 1));
        ketchup.add(new RecipeStep("Stelle eine Schussel auf die Waage.", 0, "/images/schussel_waage.jpg", 2));
        ketchup.add(new RecipeStep("Gib 255 Gramm Tomatenmark in die Schussel.", 255, "", 3));
        ketchup.add(new RecipeStep("Stelle eine Schuessel auf die Waage", 0, "/images/schuessel_waage.jpg", 4));
        ketchup.add(new RecipeStep("Gib 500 Gramm gehackte Tomaten in die Schüssel", 500, "", 5));
        recipes.add(new Recipe("Ketchup", ketchup));

        List<RecipeStep> focciacia = new ArrayList<>();
        focciacia.add(new RecipeStep("Stelle den Ofen auf 230 Grad ein.", 0, "", 0));
        focciacia.add(new RecipeStep("Stelle die Schüssel auf die Waage.", 0, "/images/schussel_waage.jpg", 1));
        focciacia.add(new RecipeStep("Gib 750 Gramm Mehl in die Schüssel.", 750, "/images/mehl.jpg", 2));
        focciacia.add(new RecipeStep("Gib 15 Gramm Salz in die Schüssel", 15, "/images/salz.jpg", 3));
        recipes.add(new Recipe("Focciacia", focciacia));

        List<RecipeStep> pommesgewuerz = new ArrayList<>();
        pommesgewuerz.add(new RecipeStep("Hole Salz.", 0, "/images/salz.jpg", 0));
        pommesgewuerz.add(new RecipeStep("Hole Paprika Pulver.", 0, "/images/paprikapulver.jpg", 1));
        pommesgewuerz.add(new RecipeStep("Hole Pfeffer.", 0, "/images/pfeffer.jpg", 2));
        pommesgewuerz.add(new RecipeStep("Hole Muskatnuss.", 0, "/images/muskatnuss.jpg", 3));
        pommesgewuerz.add(new RecipeStep("Hole Rosmarin Pulver.", 0, "/images/rosmarin.jpg", 4));
        pommesgewuerz.add(new RecipeStep("Hole Curry Pulver.", 0, "/images/curry.jpg", 5));
        pommesgewuerz.add(new RecipeStep("Hole kleine Schüssel.", 0, "/images/schussel.png", 6));
        pommesgewuerz.add(new RecipeStep("Hole Schwingbesen.", 0, "/images/schwingbesen_hand.jpg", 7));
        pommesgewuerz.add(new RecipeStep("Messe 1000 Gramm Salz mit der Waage ab.", 1000, "/images/salz.jpg", 8));
        pommesgewuerz.add(new RecipeStep("Messe 100 Gramm Paprika Pulver mit der Waage ab.", 100, "/images/paprikapulver.jpg", 9));
        pommesgewuerz.add(new RecipeStep("Messe 15 Gramm Pfeffer mit der Waage ab.", 15, "/images/pfeffer.jpg", 10));
        pommesgewuerz.add(new RecipeStep("Messe 15 Gramm Muskatnuss mit der Waage ab.", 15, "/images/muskatnuss.jpg", 11));
        pommesgewuerz.add(new RecipeStep("Messe 15 Gramm Rosmarin Pulver mit der Waage ab.", 15, "/images/rosmarin.jpg", 12));
        pommesgewuerz.add(new RecipeStep("Messe 50 Gramm Curry Pulver mit der Waage ab.", 50, "/images/curry.jpg", 13));
        pommesgewuerz.add(new RecipeStep("Vermische alles mit dem Schwingbesen.", 0, "/images/umruhren.jpg", 14));
        recipes.add(new Recipe("Pommes Gewürz", pommesgewuerz));
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
