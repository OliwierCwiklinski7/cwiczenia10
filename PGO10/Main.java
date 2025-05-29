package PGO10;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Ship> ships = new ArrayList<>();
    static List<Container> containers = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Dodaj kontenerowiec");
            System.out.println("2. Dodaj kontener");
            System.out.println("3. Załaduj kontener na statek");
            System.out.println("4. Wypisz statki");
            System.out.println("5. Wypisz kontenery");
            System.out.println("0. Wyjście");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addShip();
                case 2 -> addContainer();
                case 3 -> loadContainerOntoShip();
                case 4 -> printShips();
                case 5 -> printContainers();
                case 0 -> {
                    System.out.println("Zakończono program.");
                    return;
                }
                default -> System.out.println("Nieznana opcja.");
            }
        }
    }

    private static void addShip() {
        System.out.print("Nazwa statku: ");
        String name = scanner.nextLine();
        System.out.print("Maksymalna waga (tony): ");
        double maxWeight = Double.parseDouble(scanner.nextLine());
        System.out.print("Maksymalna liczba kontenerów: ");
        int maxContainers = Integer.parseInt(scanner.nextLine());
        System.out.print("Prędkość (węzły): ");
        double speed = Double.parseDouble(scanner.nextLine());

        ships.add(new Ship(name, maxWeight, maxContainers, speed));
        System.out.println("Dodano statek.");
    }

    private static void addContainer() {
        System.out.println("Wybierz typ kontenera:");
        System.out.println("1. Kontener na płyny");
        System.out.println("2. Kontener na gaz");
        System.out.println("3. Kontener chłodniczy");

        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Pojemność (kg): ");
        double capacity = Double.parseDouble(scanner.nextLine());
        System.out.print("Waga własna (kg): ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Wysokość (cm): ");
        double height = Double.parseDouble(scanner.nextLine());
        System.out.print("Głębokość (cm): ");
        double depth = Double.parseDouble(scanner.nextLine());

        Container container = null;

        switch (type) {
            case 1 -> {
                System.out.print("Czy ładunek niebezpieczny? (true/false): ");
                boolean hazard = Boolean.parseBoolean(scanner.nextLine());
                container = new LiquidContainer(capacity, weight, height, depth, hazard);
            }
            case 2 -> {
                System.out.print("Ciśnienie (atm): ");
                double pressure = Double.parseDouble(scanner.nextLine());
                container = new GasContainer(capacity, weight, height, depth, pressure);
            }
            case 3 -> {
                System.out.print("Rodzaj produktu: ");
                String product = scanner.nextLine();
                System.out.print("Wymagana temperatura: ");
                double tempRequired = Double.parseDouble(scanner.nextLine());
                System.out.print("Aktualna temperatura: ");
                double tempCurrent = Double.parseDouble(scanner.nextLine());
                container = new RefrigeratedContainer(capacity, weight, height, depth, product, tempRequired, tempCurrent);
            }
            default -> System.out.println("Nieznany typ.");
        }

        if (container != null) {
            containers.add(container);
            System.out.println("Dodano kontener: " + container.getSerialNumber());
        }
    }

    private static void loadContainerOntoShip() {
        if (containers.isEmpty() || ships.isEmpty()) {
            System.out.println("Brak kontenerów lub statków.");
            return;
        }

        System.out.println("Dostępne kontenery:");
        for (int i = 0; i < containers.size(); i++) {
            System.out.println(i + ": " + containers.get(i).getSerialNumber());
        }

        System.out.print("Wybierz kontener (numer): ");
        int cIndex = Integer.parseInt(scanner.nextLine());
        Container container = containers.get(cIndex);

        System.out.print("Ile kg ładunku załadować: ");
        double load = Double.parseDouble(scanner.nextLine());
        try {
            container.load(load);
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
            return;
        }

        System.out.println("Dostępne statki:");
        for (int i = 0; i < ships.size(); i++) {
            System.out.println(i + ": " + ships.get(i).toString());
        }

        System.out.print("Wybierz statek (numer): ");
        int sIndex = Integer.parseInt(scanner.nextLine());
        Ship ship = ships.get(sIndex);

        if (ship.addContainer(container)) {
            containers.remove(container); // kontener załadowany na statek
            System.out.println("Kontener załadowany na statek.");
        } else {
            System.out.println("Nie można załadować kontenera (przekroczono limity).");
        }
    }

    private static void printShips() {
        if (ships.isEmpty()) {
            System.out.println("Brak statków.");
            return;
        }
        for (Ship ship : ships) {
            ship.printShipInfo();
        }
    }

    private static void printContainers() {
        if (containers.isEmpty()) {
            System.out.println("Brak kontenerów.");
            return;
        }
        for (Container c : containers) {
            System.out.println(c);
        }
    }
}