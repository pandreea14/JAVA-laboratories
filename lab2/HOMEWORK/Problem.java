import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem {
    /** retruns the vehicles from all depots
     *
     * @param depots the depots array
     * @return an array of all vehicles from all depots
     */
    public Vehicles[] getVehicles(Depots... depots) {
        List<Vehicles> vehicleList = new ArrayList<>();
        for (Depots depot : depots) {
            if (depot.getVehicles() != null) {
                vehicleList.addAll(Arrays.asList(depot.getVehicles()));
            }
        }
        return vehicleList.toArray(new Vehicles[0]);
    }

    public void printVehicles(Depots... depots) {
        Vehicles[] totalVehicles = this.getVehicles(depots);
        System.out.println("All vehicles are: ");
        for (Vehicles vehicle : totalVehicles) {
            System.out.println(vehicle + " ");
        }
        System.out.println();
    }
}
