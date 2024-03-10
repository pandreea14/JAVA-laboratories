import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// We consider the problem of allocating clients to vehicles, such that all clients are visited (if possible).
public class Main {
    static List<Tour> tours = new ArrayList<>(); //static pt ca trb folosit si in cealalta functie

    public static void main(String[] args) {
        Problem problem = new Problem();

        Clients clientKiki = new Clients();
        clientKiki.setName("kiki");
        clientKiki.setClientType(Clients.ClientType.REGULAR);
        clientKiki.setMinTime(LocalTime.of(8,0));
        clientKiki.setMaxTime(LocalTime.of(12,10));
        System.out.println(clientKiki);
        Clients clientGigi = new Clients("Gigi", Clients.ClientType.REGULAR);
        System.out.println(clientGigi);
        Clients clientDidi = new Clients("Didi", LocalTime.NOON, LocalTime.MIDNIGHT, Clients.ClientType.PREMIUM);
        System.out.println(clientDidi);
        Clients clientMimi = new Clients("Mimi", Clients.ClientType.PREMIUM);
        System.out.println(clientMimi);
        Clients clientCici = new Clients("Cici", Clients.ClientType.PREMIUM);
        System.out.println(clientCici);

        Depots depotNorth = new Depots("gara de nord");
        System.out.println(depotNorth);
        Depots depotSouth = new Depots("gara de sud");
        System.out.println(depotSouth);
        Depots depotEast = new Depots("gara de est");
        System.out.println(depotEast);

        Vehicles porumb = new Drones("teVad1000", "purple", "porumb");
        Vehicles caruta = new Trucks("calatara", "red", "caruta");
        Vehicles tesla = new Trucks("elonMusk", "royalblue", "tesla");

        // adding the vehicles to the existent depots
        depotSouth.setVehicles(porumb, caruta);
        depotNorth.setVehicles(tesla);

        porumb.setDepot(depotSouth);
        caruta.setDepot(depotSouth);
        tesla.setDepot(depotNorth);

        // prints all vehicles using getVehicles from the Problem class
        problem.printVehicles(depotEast, depotNorth, depotSouth);


        // setting the time interval matrix, known by each client
        // it represents each time interval between a client-depot or client-client
        Clients.setTimeInterval(8);
        printTimeIntervalMatrix();


        // the greedy allocation of the clients to the vehicles
        allocateClients(depotSouth, porumb, clientCici, clientDidi);
        allocateClients(depotNorth, tesla, clientKiki, clientMimi, clientGigi);

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
        // check if the depot and vehicle are null
        if (depot == null || vehicle == null) {
            System.out.println("Depot or vehicle is null. Cannot to allocate clients.");
            return;
        }

        Tour tour = new Tour(depot, vehicle);
        for (Clients client : clients) {
            //System.out.println("i am here");
            vehicle.setClients(client); //allocates the client to the vehicle

            if (vehicle.canTour(client)) {
                System.out.println("i am here");
                tour.addClients(client);    //allocates the client in the tour ?????????????????????????????????????????????
            } else {
                System.out.println("Vehicle " + vehicle.getName() + " cannot tour to client " + client.getName());
                System.out.println("Tour not possible.");
                return;
            }
        }
        tours.add(tour);
    }

    public static void printTimeIntervalMatrix() {
        int[][] timeMatrix = Clients.getTimeInterval();
        System.out.println("Time interval matrix is: ");
        for (int i = 1; i < timeMatrix.length; i++) {
            for (int j =1; j < timeMatrix[i].length; j++) {
                System.out.print(timeMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}