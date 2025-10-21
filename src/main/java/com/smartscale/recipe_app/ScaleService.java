package com.smartscale.recipe_app;

import com.phidget22.*;

public class ScaleService {

    private VoltageRatioInput loadCell;
    private final double gain;
    private double offset = 0;
    private boolean calibrated = false;

    // Listener interface so the controller can get weight updates
    public interface WeightListener {
        void onWeightChanged(int weight);
    }

    private WeightListener listener;

    public ScaleService(double gain) {
        this.gain = gain; // calibration factor from Phidget Control Panel
    }

    public void setWeightListener(WeightListener listener) {
        this.listener = listener;
    }

    public void start() throws Exception {
        loadCell = new VoltageRatioInput();

        // Listen for raw voltage ratio changes
        loadCell.addVoltageRatioChangeListener(event -> {
            if (calibrated && listener != null) {
                double raw = event.getVoltageRatio();
                int weight = (int) ((raw - offset) * gain);
                listener.onWeightChanged(weight);
            }
        });

        loadCell.open(5000);
        loadCell.setDataInterval(50); // update every 100 ms
    }

    public void tare() throws Exception {
        int numSamples = 25;
        double sum = 0;

        for (int i = 0; i < numSamples; i++) {
            sum += loadCell.getVoltageRatio();
            Thread.sleep(loadCell.getDataInterval());
        }

        offset = sum / numSamples;
        calibrated = true;
        // System.out.println("Scale tared with offset: " + offset);
    }

    public void stop() throws Exception {
        if (loadCell != null) {
            loadCell.close();
        }
    }
}
