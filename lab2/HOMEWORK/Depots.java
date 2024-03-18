import java.util.Arrays;
import java.util.Objects;

public class Depots {
    String name;
    int countVehicles;
    String location;
    Vehicles[] vehicles;

    public Depots(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getcountVehicles() {
        return countVehicles;
    }
    public void setcountVehicles(int countVehicles) {
        this.countVehicles = countVehicles;
    }

    public Vehicles[] getVehicles() {
        return vehicles;
    }
    public void setVehicles(Vehicles... vehicles) {
        for(Vehicles v : vehicles) {
            if(countVehicles < vehicles.length) {
                this.vehicles = vehicles;
                countVehicles++;
                v.setDepot(this);
            }
        }
    }

    /** verifies if the depot needs to be added as new
     *
     * @param d the depot that is verified
     * @param depots the existing depots
     * @return true if the depot can be added, false otherwise
     */
    public boolean verifyUniqueness(Depots d, Depots... depots) {
        for(Depots depot : depots) {
            if (depot.equals(d)) {
                System.out.println("Depot already exists..");
                return false;
            }
        }
        System.out.println("Depot can be added.");
        return true;
    }

    @Override
    public String toString() {
        return "Depots{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", countVehicles=" + countVehicles +
                //", vehicles=" + Arrays.toString(vehicles) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depots depots = (Depots) o;
        return countVehicles == depots.countVehicles && Objects.equals(name, depots.name) && Objects.equals(location, depots.location) && Arrays.equals(vehicles, depots.vehicles);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(name, location, countVehicles);
        result = 31 * result + Arrays.hashCode(vehicles);
        return result;
    }
}