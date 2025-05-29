package PGO10;

import java.util.*;

public class Ship {
    private String name;
    private double maxWeight; // w kg
    private int maxContainers;
    private double speed; // węzły
    private List<Container> containers = new ArrayList<>();

    public Ship(String name, double maxWeight, int maxContainers, double speed) {
        this.name = name;
        this.maxWeight = maxWeight * 1000; // tony na kg
        this.maxContainers = maxContainers;
        this.speed = speed;
    }

    public boolean addContainer(Container c) {
        if (containers.size() >= maxContainers) return false;
        double totalWeight = containers.stream().mapToDouble(container -> container.weight + container.currentLoad).sum();
        if (totalWeight + c.weight + c.currentLoad > maxWeight) return false;
        return containers.add(c);
    }

    public void removeContainer(String serial) {
        containers.removeIf(c -> c.getSerialNumber().equals(serial));
    }

    public void printShipInfo() {
        System.out.println("Statek: " + name + " (" + speed + " węzłów)");
        containers.forEach(System.out::println);
    }
}