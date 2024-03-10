import java.util.ArrayList;
import java.util.List;

public class Tour {
    Depots depots;
    Vehicles vehicles;
    List<Clients> clients;

    /** The constructor Tour with depots and vehicles knows the tour made from the given depot with the given vehicle and creates a new clients list
     *
     * @param depots chosen depot
     * @param vehicles chosen vehicle
     */
    public Tour(Depots depots, Vehicles vehicles) {
        this.depots = depots;
        this.vehicles = vehicles;
        this.clients = new ArrayList<>();
    }

    public void addClients(Clients client) {
        clients.add(client);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "depots=" + depots +
                ", vehicles=" + vehicles +
                ", clients=" + clients +
                '}';
    }
}
