package cn.drelang.designPattern;

import java.util.ArrayList;

/**
 * Created by Drelang on 2019/08/26 21:44
 */

public class Observer {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay display = new CurrentConditionDisplay(weatherData);
        weatherData.setMeasurements(24.0f, 60.0f, 1000.0f);
        weatherData.setMeasurements(25.0f, 50.0f, 1001.0f);
    }
}

interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}

interface IObserver {
    void update(float temp, float humidity, float pressure);
}

class WeatherData implements ISubject {
    private ArrayList<IObserver> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    WeatherData() {
        this.observers = new ArrayList<>();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }
}

class CurrentConditionDisplay implements IObserver {

    CurrentConditionDisplay(ISubject weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.printf("CurrentConditionDisplay.update: temperature=%.2f, humidity=%.2f, pressure=%.2f \n", temp, humidity, pressure);
    }
}