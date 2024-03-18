import java.util.Arrays;

public class Trucks extends Vehicles {
    int capacity;
    public Trucks(String registrationNumber, String color, String name) {
        super(registrationNumber, color, name);
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Trucks(String registrationNumber) {
        super(registrationNumber);
    }

    /** Verification of the tour of each vehicle
     *
     * @param client the client that was added to the tour -- if there is time between client-client
     * @return true if we have a REGULAR client, false otherwise
     */
    @Override
    public boolean canTour(Clients client) {
        return client.clientType == Clients.Type.REGULAR;
    }

    @Override
    public String toString() {
        return "Trucks{" +
                "name='" + name + '\'' +
                ", depot=" + depot +
                '}';
    }
}
