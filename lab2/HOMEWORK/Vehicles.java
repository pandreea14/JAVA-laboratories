import java.util.Arrays;
import java.util.Objects;

abstract class Vehicles {
    String registrationNumber;
    String color;

    public Vehicles(String registrationNumber, String color, String name) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.name = name;
    }

    String name;
    Clients[] clients;
    Depots depot;

    public Vehicles() {
    }
    public Vehicles(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Vehicles(String registrationNumber, Depots depot) {
        this.registrationNumber = registrationNumber;
        this.depot = depot;
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
    public void addClients(Clients... clients) {
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

    /**
     * @param client the given clients that need to be toured
     * @return true if the client can be toured
     */
    public abstract boolean canTour(Clients client);

    /** verifies if the vehicle needs to be added as new
     *
     * @param v the vehicle that is verified
     * @param vehicles the existing vehicles
     * @return true if the vehicle can be added into the depot, false if it is already added into this or another depot
     */
    public boolean verifyUniqueness(Vehicles v, Vehicles... vehicles) {
        for(Vehicles vehicle : vehicles) {
            if (vehicle.equals(v)) {
                System.out.println("Vehicle already exists..");
                return false;
            } else if (!vehicle.getDepot().equals(v.getDepot())) {
                System.out.println("Vehicle is in another depot!");
            }
        }
        System.out.println("Vehicle added.");
        return true;
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

    @Override
    public String toString() {
        return "Vehicles{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", clients=" + Arrays.toString(clients) +
                ", depot=" + depot +
                '}';
    }
}
