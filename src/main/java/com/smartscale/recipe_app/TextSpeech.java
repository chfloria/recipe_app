package com.smartscale.recipe_app;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextSpeech {
    private static final String VOICENAME = "kevin16"; // Standard-Stimme

    private final Voice voice;

    public TextSpeech() {
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);
        voice.setRate(100);
        if (voice == null) {
            throw new IllegalStateException("Stimme '" + VOICENAME + "' wurde nicht gefunden!");
        }
        voice.allocate();
    }

    public void speak(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        voice.speak(text);
    }

    public void close() {
        voice.deallocate();
    }

}