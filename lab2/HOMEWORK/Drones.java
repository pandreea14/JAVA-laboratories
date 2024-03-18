import java.util.Arrays;

public class Drones extends Vehicles {
    int maxFlightDuration;

    public Drones() {
    }
    public Drones(String registrationNumber, String color, String name) {
        super(registrationNumber, color, name);
    }

    public int getMaxFlightDuration() {
        return maxFlightDuration;
    }
    public void setMaxFlightDuration(int maxFlightDuration) {
        this.maxFlightDuration = maxFlightDuration;
    }

    /** Verification of the tour of each vehicle
     *
     * @param client the clients that were added to the tour
     * @return true if we have a PREMIUM client, false otherwise
     */
    @Override
    public boolean canTour(Clients client) {
        return client.clientType == Clients.Type.PREMIUM;
    }

    @Override
    public String toString() {
        return "Drones{" +
                "name='" + name + '\'' +
                ", depot=" + depot +
                '}';
    }
}
