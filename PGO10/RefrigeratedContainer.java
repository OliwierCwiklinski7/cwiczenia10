package PGO10;

public class RefrigeratedContainer extends Container {
    private String productType;
    private double requiredTemperature;
    private double currentTemperature;

    public RefrigeratedContainer(double loadCapacity, double weight, double height, double depth,
                                 String productType, double requiredTemperature, double currentTemperature) {
        super(loadCapacity, weight, height, depth, "C");
        this.productType = productType;
        this.requiredTemperature = requiredTemperature;
        this.currentTemperature = currentTemperature;
    }

    @Override
    public void load(double mass) throws Exception {
        if (currentTemperature < requiredTemperature) {
            throw new Exception("Zbyt niska temperatura dla produktu: " + productType);
        }
        super.load(mass);
    }
}