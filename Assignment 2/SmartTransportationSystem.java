import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * =========================================================
 * SMART TRANSPORTATION MANAGEMENT SYSTEM
 * Refactored using the Factory Method Design Pattern
 * =========================================================
 */


/* =========================================================
 * PRODUCT INTERFACE
 * =========================================================
 */
interface Vehicle {
    void startTrip();
    double calculateFare(double distance);
    void assignRoute(String route);
    void getVehicleInfo();
    String getVehicleType();
}


/* =========================================================
 * CONCRETE PRODUCTS
 * =========================================================
 */
class Bus implements Vehicle {
    private final int capacity = 40;
    private final double farePerKm = 15;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("[Bus] Trip started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("[Bus] Assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("  Vehicle   : Bus");
        System.out.println("  Capacity  : " + capacity + " passengers");
        System.out.println("  Fare/KM   : BDT " + farePerKm);
    }

    @Override
    public String getVehicleType() {
        return "Bus";
    }
}

class Taxi implements Vehicle {
    private final int capacity = 4;
    private final double farePerKm = 30;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("[Taxi] Trip started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("[Taxi] Assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("  Vehicle   : Taxi");
        System.out.println("  Capacity  : " + capacity + " passengers");
        System.out.println("  Fare/KM   : BDT " + farePerKm);
    }

    @Override
    public String getVehicleType() {
        return "Taxi";
    }
}

class MotorcycleDelivery implements Vehicle {
    private final int capacity = 1;
    private final double farePerKm = 10;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("[MotorcycleDelivery] Delivery started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("[MotorcycleDelivery] Assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("  Vehicle   : Motorcycle Delivery");
        System.out.println("  Capacity  : " + capacity + " package");
        System.out.println("  Fare/KM   : BDT " + farePerKm);
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle Delivery";
    }
}

class ElectricScooter implements Vehicle {
    private final int capacity = 1;
    private final double farePerKm = 8;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("[ElectricScooter] Trip started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("[ElectricScooter] Assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("  Vehicle   : Electric Scooter");
        System.out.println("  Capacity  : " + capacity + " rider");
        System.out.println("  Fare/KM   : BDT " + farePerKm);
    }

    @Override
    public String getVehicleType() {
        return "Electric Scooter";
    }
}


/* =========================================================
 * CREATOR — Abstract Factory (Factory Method Pattern)
 * =========================================================
 */
abstract class VehicleFactory {

    /**
     * The Factory Method
     */
    public abstract Vehicle createVehicle();

    public Vehicle registerVehicle(String route) {
        Vehicle vehicle = createVehicle();           // factory method call
        System.out.println("\n--- Registering Vehicle ---");
        vehicle.getVehicleInfo();
        vehicle.assignRoute(route);
        System.out.println("Vehicle registered successfully.");
        return vehicle;
    }

    /**
     * Dispatches an existing vehicle for a trip and prints the fare.
     */
    public void dispatchVehicle(Vehicle vehicle, double distance) {
        System.out.println("\n--- Dispatching: " + vehicle.getVehicleType() + " ---");
        vehicle.startTrip();
        double fare = vehicle.calculateFare(distance);
        System.out.printf("Distance : %.1f km%n", distance);
        System.out.printf("Total Fare: BDT %.2f%n", fare);
    }
}


/* =========================================================
 * CONCRETE CREATORS
 * =========================================================
 */
class BusFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Bus();
    }
}

class TaxiFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Taxi();
    }
}

class MotorcycleDeliveryFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new MotorcycleDelivery();
    }
}

class ElectricScooterFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new ElectricScooter();
    }
}


/* =========================================================
 * CLIENT — Transportation Manager
 * =========================================================
 */
class TransportationManager {

    private final List<Vehicle> fleet = new ArrayList<>();

    public void addVehicle(VehicleFactory factory, String route) {
        Vehicle vehicle = factory.registerVehicle(route);
        fleet.add(vehicle);
    }

    public void dispatch(VehicleFactory factory, double distance) {
        String targetType = factory.createVehicle().getVehicleType();
        for (Vehicle v : fleet) {
            if (v.getVehicleType().equals(targetType)) {
                factory.dispatchVehicle(v, distance);
                return;
            }
        }
        System.out.println("No vehicle of type '" + targetType + "' found in fleet.");
    }

    public void printFleet() {
        System.out.println("\n========= CURRENT FLEET =========");
        if (fleet.isEmpty()) {
            System.out.println("  (no vehicles registered)");
        } else {
            for (int i = 0; i < fleet.size(); i++) {
                System.out.println("  [" + (i + 1) + "]");
                fleet.get(i).getVehicleInfo();
            }
        }
        System.out.println("=================================");
    }
}

/* =========================================================
 * EXTENSIBILITY — New Vehicle Type: CargoBike
 * =========================================================
 */
class CargoBike implements Vehicle {
    private final int capacity = 2;
    private final double farePerKm = 12;
    private String route;

    @Override public void startTrip() {
        System.out.println("[CargoBike] Cargo trip started.");
    }

    @Override public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override public void assignRoute(String route) {
        this.route = route;
        System.out.println("[CargoBike] Assigned to route: " + route);
    }

    @Override public void getVehicleInfo() {
        System.out.println("  Vehicle   : Cargo Bike");
        System.out.println("  Capacity  : " + capacity + " parcels");
        System.out.println("  Fare/KM   : BDT " + farePerKm);
    }

    @Override public String getVehicleType() { return "Cargo Bike"; }
}

class CargoBikeFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new CargoBike();
    }
}

/* =========================================================
 * MAIN — Entry Point / Demo
 * =========================================================
 */
public class SmartTransportationSystem {

    public static void main(String[] args) {

        System.out.println("=========================================================");
        System.out.println("   SMART TRANSPORTATION MANAGEMENT SYSTEM");
        System.out.println("   (Factory Method Design Pattern)");
        System.out.println("=========================================================");

        // ------------------------------------------------------------------
        // 1. Instantiate concrete factories.
        // ------------------------------------------------------------------
        VehicleFactory busFactory       = new BusFactory();
        VehicleFactory taxiFactory      = new TaxiFactory();
        VehicleFactory motoFactory      = new MotorcycleDeliveryFactory();
        VehicleFactory scooterFactory   = new ElectricScooterFactory();
        VehicleFactory cargoBikeFactory = new CargoBikeFactory();

        TransportationManager manager = new TransportationManager();

        // ------------------------------------------------------------------
        // 2. Register vehicles
        // ------------------------------------------------------------------
        manager.addVehicle(busFactory,       "Route-1: Mirpur → Motijheel");
        manager.addVehicle(taxiFactory,      "Route-2: Airport → Gulshan");
        manager.addVehicle(motoFactory,      "Route-3: Dhanmondi local delivery");
        manager.addVehicle(scooterFactory,   "Route-4: Banani last-mile");
        manager.addVehicle(cargoBikeFactory, "Route-5: Uttara warehouse run");

        // ------------------------------------------------------------------
        // 3. Print the full fleet (all 5 vehicles).
        // ------------------------------------------------------------------
        manager.printFleet();

        // ------------------------------------------------------------------
        // 4. Dispatch vehicles for trips.
        // ------------------------------------------------------------------
        manager.dispatch(busFactory,       25.0);
        manager.dispatch(taxiFactory,      12.5);
        manager.dispatch(motoFactory,       8.0);
        manager.dispatch(scooterFactory,    3.5);
        manager.dispatch(cargoBikeFactory, 18.0);

        System.out.println("\n[Done] All operations complete.");
    }
}