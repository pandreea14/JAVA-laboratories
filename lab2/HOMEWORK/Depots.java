import java.util.Arrays;
import java.util.Objects;

public class Depots {
    String name;
    Vehicles[] vehicles;

    public Depots(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setVehicles(Vehicles... vehicles) {
        this.vehicles = vehicles;
        for(Vehicles v : vehicles) {
            v.setDepot(this);
        }
    }
    public Vehicles[] getVehicles() {
        return vehicles;
    }

    public boolean verifyUniqueness(Depots d, Depots... depots) {
        for(Depots depot : depots) {
            if (depot.equals(d)) {
                System.out.println("Depot already exists..");
                return false;
            }
        }
        System.out.println("Depot added.");
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depots depots = (Depots) o;
        return Objects.equals(name, depots.name) && Arrays.equals(vehicles, depots.vehicles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(vehicles);
        return result;
    }

    @Override
    public String toString() {
        return "Depots{" +
                "name='" + name + '\'' +
                ", vehicles=" + Arrays.toString(vehicles) +
                '}';
    }

}
