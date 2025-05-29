package PGO10;

public abstract class Container {
    protected static int counter = 1;
    protected String serialNumber;
    protected double loadCapacity;
    protected double currentLoad = 0;
    protected double weight;
    protected double height;
    protected double depth;

    public Container(double loadCapacity, double weight, double height, double depth, String type) {
        this.serialNumber = "KON-" + type + "-" + counter++;
        this.loadCapacity = loadCapacity;
        this.weight = weight;
        this.height = height;
        this.depth = depth;
    }

    public void unload() {
        currentLoad = 0;
    }

    public void load(double mass) throws Exception {
        if (currentLoad + mass > loadCapacity) {
            throw new Exception("OverfillException: Ładunek przekracza pojemność kontenera");
        }
        currentLoad += mass;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String toString() {
        return serialNumber + " [Load: " + currentLoad + "/" + loadCapacity + "]";
    }
}