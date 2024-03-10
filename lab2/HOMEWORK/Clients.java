import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;

public class Clients {
    String name;
    LocalTime minTime;
    LocalTime maxTime;
    static int[][] timeInterval;
    ClientType type;

    public Clients(String name, LocalTime minTime, LocalTime maxTime, ClientType type) {
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.type = type;
    }

    public Clients(String name, LocalTime minTime, LocalTime maxTime) {
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public Clients() {
    }

    public Clients(String name, ClientType type) {
        this(name, null, null);
        this.type = type;
    }

    public enum ClientType {
        REGULAR, PREMIUM;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /** aici se face alocarea random a intervalului de timp in care un vehicul ajunge de la un client la altul
     *
     * @param locations sunt nr total de noduri din graf (clienti+depots)
     */
    public static void setTimeInterval(int locations) {
        Random random = new Random();
        Clients.timeInterval = new int[locations+1][locations+1];
        for (int i=1; i<=locations; i++) {
            for (int j=1; j<=locations; j++) {
                if (i==j || (i>5 && j>5)) { // distanta dintre un client si el insusi e 0 + dist depot-depot
                    Clients.timeInterval[i][j] = 0;
                } else if (j>i) {
                    Clients.timeInterval[i][j] = random.nextInt(9) + 1; //nr intre 1 si 10
                } else {
                    Clients.timeInterval[i][j] = Clients.timeInterval[j][i];
                }
            }
        }
    }
    public static int[][] getTimeInterval() {
        return timeInterval;
    }

    public LocalTime getMinTime() {
        return minTime;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    public LocalTime getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    public ClientType getClientType() {
        return type;
    }
    public void setClientType(ClientType type) {
        this.type = type;
    }

    public boolean verifyUniqueness(Clients c, Clients... clients) {
        for(Clients client : clients) {
            if (client.equals(c)) {
                System.out.println("Client already exists..");
                return false;
            }
        }
        System.out.println("Client added.");
        return true;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "name='" + name + '\'' +
                ", clientType=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return Objects.equals(name, clients.name) && type == clients.type;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

}
