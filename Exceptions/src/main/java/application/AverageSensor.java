package application;

import java.util.ArrayList;
import java.util.List;

public class AverageSensor implements Sensor{

    private ArrayList<Sensor> sensors;
    private ArrayList<Integer> readings;

    public static void main(String[] args) {
        Sensor kumpula = new TemperatureSensor();
        Sensor kaisaniemi = new TemperatureSensor();
        Sensor helsinkiVantaaAirport = new TemperatureSensor();

        AverageSensor helsinkiRegion = new AverageSensor();
        helsinkiRegion.addSensor(kumpula);
        helsinkiRegion.addSensor(kaisaniemi);
        helsinkiRegion.addSensor(helsinkiVantaaAirport);

        helsinkiRegion.setOn();
        System.out.println("temperature in Helsinki region " + helsinkiRegion.read() + " degrees Celsius");
        System.out.println("temperature in Helsinki region " + helsinkiRegion.read() + " degrees Celsius");
        System.out.println("temperature in Helsinki region " + helsinkiRegion.read() + " degrees Celsius");

        System.out.println("readings: " + helsinkiRegion.readings());
    }

    public AverageSensor(){
        this.sensors = new ArrayList<>();
        this.readings = new ArrayList<>();
    }
    public void addSensor(Sensor toAdd){
        this.sensors.add(toAdd);
    }
    public List<Integer> readings(){
        return this.readings;
    }
    @Override
    public boolean isOn() {
        for(Sensor x : sensors){
            if(!x.isOn()){
                return false;
            }
        }
        return true;
    }
    @Override
    public void setOn() {
        this.sensors.forEach(sensor -> sensor.setOn());
    }
    @Override
    public void setOff() {
        this.sensors.forEach(sensor -> sensor.setOff());
    }
    @Override
    public int read() {

        if(!this.isOn() ||this.sensors.isEmpty()){
            throw new IllegalStateException("AverageSensors are off or empty");
        }

        int average = (this.sensors.stream()
                .map(sensor -> sensor.read())
                .reduce(0, (x,y) -> x+y))
                /(this.sensors.size());

        this.readings.add(average);

        return average;
    }
}
