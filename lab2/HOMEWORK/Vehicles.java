import java.util.Arrays;
import java.util.Objects;

abstract class Vehicles {
    String name;
    String registrationNumber;
    String color;
    Clients[] clients;
    Depots depot;

    public Vehicles(String registrationNumber, String color, String name) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.name = name;
    }
    public Vehicles() {
    }
    public Vehicles(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean verifyUniqueness(Vehicles v, Vehicles... vehicles) {
        for(Vehicles vehicle : vehicles) {
            if (vehicle.equals(v)) {
                System.out.println("Vehicle already exists..");
                return false;
            }
        }
        System.out.println("Vehicle added.");
        return true;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Clients[] getClients() {
        return clients;
    }
    public void setClients(Clients... clients) {
        this.clients = clients;
    }

    public Depots getDepot() {
        return depot;
    }
    public void setDepot(Depots depot) {
        this.depot = depot;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean canTour(Clients clients);

    @Override
    public String toString() {
        return "Vehicles{" +
                "name='" + name + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                ", clients=" + Arrays.toString(clients) +
                ", depot=" + depot +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicles vehicles = (Vehicles) o;
        return Objects.equals(registrationNumber, vehicles.registrationNumber) && Objects.equals(color, vehicles.color) && Objects.equals(name, vehicles.name) && Arrays.equals(clients, vehicles.clients) && Objects.equals(depot, vehicles.depot);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(registrationNumber, color, name, depot);
        result = 31 * result + Arrays.hashCode(clients);
        return result;
    }
}
