package PGO10;

public class GasContainer extends Container implements IHazardNotifier {
    private double pressure;

    public GasContainer(double loadCapacity, double weight, double height, double depth, double pressure) {
        super(loadCapacity, weight, height, depth, "G");
        this.pressure = pressure;
    }

    @Override
    public void unload() {
        currentLoad *= 0.05; // zostaje 5%
    }

    @Override
    public void notifyHazard(String message) {
        System.out.println("! [HAZARD] " + message + " w kontenerze: " + serialNumber);
    }
}