package application;

import java.util.Random;

public class TemperatureSensor implements Sensor{
    private int temp;
    private boolean status;

    public TemperatureSensor(){
        this.temp = 0;
        this.status = false;
    }
    @Override
    public void setOff() {
        this.status = false;
    }
    @Override
    public boolean isOn() {
        return this.status;
    }

    @Override
    public void setOn() {
        this.status=true;
    }

    @Override
    public int read() {
        if(!isOn()){
            throw new IllegalStateException("TemperatureSensor is off");
        }
        return (new Random().nextInt(61))-30;
    }
}
