package com.smartscale.recipe_app;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javafx.util.Duration;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

public class TextSpeech {
    public static void speak(String text) throws Exception {
        // URL vorbereiten
        String encoded = URLEncoder.encode(text, "UTF-8");
        String url = "https://translate.google.com/translate_tts?ie=UTF-8&q=" + encoded +
                "&tl=de&client=tw-ob";

        // MP3 speichern
        File mp3File = new File("output.mp3");
        try (InputStream in = new URL(url).openStream();
             OutputStream out = new FileOutputStream(mp3File)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        // JavaFX initialisieren
        new JFXPanel();

        // MediaPlayer vorbereiten
        Media hit = new Media(mp3File.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);

        // Optional: Lautstärke und Rate anpassen
        mediaPlayer.setVolume(1.0);
        mediaPlayer.setRate(1.0);

        // Start und Ende überwachen
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.play();
            System.out.println("🔊 Starte Wiedergabe: " + text);
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            System.out.println("✅ Wiedergabe abgeschlossen");
            mediaPlayer.dispose();
            mp3File.delete(); // temporäre Datei löschen
        });

        // Optional: Warte, bis Wiedergabe abgeschlossen ist
        Thread.sleep((long) (text.split(" ").length * 400)); // grober Richtwert
    }
}