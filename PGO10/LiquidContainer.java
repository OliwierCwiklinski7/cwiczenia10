package PGO10;

public class LiquidContainer extends Container implements IHazardNotifier {
    private boolean isHazardous;

    public LiquidContainer(double loadCapacity, double weight, double height, double depth, boolean isHazardous) {
        super(loadCapacity, weight, height, depth, "L");
        this.isHazardous = isHazardous;
    }

    @Override
    public void load(double mass) throws Exception {
        double limit = isHazardous ? loadCapacity * 0.5 : loadCapacity * 0.9;
        if (currentLoad + mass > limit) {
            notifyHazard("Próba załadowania niebezpiecznego ładunku powyżej limitu");
            throw new Exception("OverfillException: przekroczono limit");
        }
        currentLoad += mass;
    }

    @Override
    public void notifyHazard(String message) {
        System.out.println("! [HAZARD] " + message + " w kontenerze: " + serialNumber);
    }
}