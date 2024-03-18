import java.util.ArrayList;
import java.util.List;

// We consider the problem of allocating clients to vehicles, such that all clients are visited (if possible).
// Modify the algorithm that allocates clients to vehicles, such that the vehicles will travel on the shortest paths when moving from the depot to the clients, from one client to another or back to the depot.
// Can you improve the complexity of the algorithm if all the values in the cost matrix are the same?
public class Main {
    static List<Tour> tours = new ArrayList<>(); //static pt ca trb folosit si in cealalta functie

    public static void main(String[] args) {
        Problem problem = new Problem();

        Clients[] client = new Clients[5];
        client[0] = new Clients("kiki", Clients.Type.PREMIUM);
        System.out.println(client[0]);
        client[1] = new Clients("mimi", Clients.Type.REGULAR);
        System.out.println(client[1]);
        client[2] = new Clients("didi", Clients.Type.PREMIUM);
        System.out.println(client[2]);
        client[3] = new Clients("cici", Clients.Type.REGULAR);
        System.out.println(client[3]);
        client[4] = new Clients("hihi", Clients.Type.PREMIUM);
        System.out.println(client[4]);

        // adding the clients to the problem
        problem.addClients(client);

        Depots[] depot = new Depots[3];
        depot[0] = new Depots("gara de nord", "bucuresti");
        System.out.println(depot[0]);
        depot[1] = new Depots("gara de sud", "onesti");
        System.out.println(depot[1]);
        depot[2] = new Depots("gara de est", "galati");
        System.out.println(depot[2]);

        problem.addDepots(depot);

        // setting the time interval matrix, known by each client
        // it represents each time interval between a client-depot or client-client
        Clients.setTimeInterval(8);
        Clients.printTimeIntervalMatrix();

        Vehicles[] vehicle = new Vehicles[3];
        vehicle[0] = new Drones("teVad1000", "blue", "porumb");
        vehicle[1] = new Trucks("calatara", "red", "caruta");
        vehicle[2] = new Trucks("elonMusk", "green", "tesla");

        // adding the vehicles to the existent depots
        depot[1].setVehicles(vehicle[0], vehicle[1]);
        depot[0].setVehicles(vehicle[2]);

        // prints all vehicles using getVehicles from the Problem class
        problem.printVehicles();

        // the greedy allocation of the clients to the vehicles
        allocateClients(depot[1], vehicle[0], client[0], client[1], client[2], client[3], client[4]);
        allocateClients(depot[0], vehicle[2], client[0], client[1], client[2], client[3], client[4]);

        for(Tour tour : tours) {
            System.out.println(tour);
        }

        System.out.println(problem);
    }

    /** Initially, the tour only has the restrictions of the clients' type.
     * Then, in the bonus part, I check whether there is a connection between the given clients or not
     * @param depot the chosen depot, the starting and end point of the tour
     * @param vehicle the vehicle doing the tour
     * @param clients the clients visited in the specific tour
     */
    public static void allocateClients(Depots depot, Vehicles vehicle, Clients... clients) {
        Tour tour = new Tour(depot, vehicle);
        for (Clients client : clients) {
            if (vehicle.canTour(client)) {
                System.out.println("Vehicle " + vehicle.getName() + " can tour to client " + client.getName());
                vehicle.addClients(client); //allocates the client to the vehicle
                tour.addClients(client);    //allocates the client in the tour
            } else {
                System.out.println("Vehicle " + vehicle.getName() + " cannot tour to client " + client.getName());
            }
        }
        tours.add(tour);
    }
}