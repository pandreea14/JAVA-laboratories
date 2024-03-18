import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem {
    Depots[] depots;
    Clients[] clients;

    /** retruns the vehicles from all depots
     *
     * @param depots the depots array
     * @return an array of all vehicles from all depots
     */
    public Vehicles[] getVehicles(Depots... depots) {
        List<Vehicles> vehicleList = new ArrayList<>();
        for (Depots depot : depots) {
            if(depot!=null) {
                Vehicles[] depotVehicles = depot.getVehicles();
                if (depotVehicles != null) {
                    vehicleList.addAll(Arrays.asList(depotVehicles));
                }
            }
        }
        return vehicleList.toArray(new Vehicles[0]);
    }

    public Clients[] getClients() {
        return clients;
    }
    public void addClients(Clients[] clients) {
        this.clients = clients;
    }
    public Depots[] getDepots() {
        return depots;
    }
    public void addDepots(Depots[] depots) {
        this.depots = depots;
    }

    public void printVehicles() {
        Vehicles[] totalVehicles = this.getVehicles(this.depots);
        System.out.println("All vehicles are: ");
        for (Vehicles vehicle : totalVehicles) {
            System.out.println(vehicle + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Problem{" +
                "depots=" + Arrays.toString(depots) +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }
}
