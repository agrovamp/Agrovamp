package com.agrovamp.agrovamp;

/**
 * Created by Nishat Sayyed on 17-02-2018.
 */

public class Sensor {
    private double temperature;
    private double moisture;
    private double humidity;

    public Sensor(){}

    Sensor(double temperature, double moisture, double humidity){
        this.temperature = temperature;
        this.humidity = humidity;
        this.moisture = moisture;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getMoisture() {
        return moisture;
    }

    public double getHumidity() {
        return humidity;
    }

}
