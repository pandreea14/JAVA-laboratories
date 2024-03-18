import java.util.Objects;
import java.util.Random;

public class Clients {
    String name;
    static int[][] timeInterval;
    Type clientType;

    public Clients(String name, Type clientType) {
        this.name = name;
        this.clientType = clientType;
    }

    public enum Type {
        REGULAR, PREMIUM;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /** aici se face alocarea random a intervalului de timp in care un vehicul ajunge de la un client la altul (1-9)
     * timpul dintre un client si el insusi e 0 si la fel si timpul depot-depot
     * matricea este simetrica fata de diagonala principala
     * pt tema, consider ca exista 'drum' aka interval de timp intre toti clientii si orice client-depou
     * @param locations sunt nr total de noduri din graf (clienti+depots)
     */
    public static void setTimeInterval(int locations) {
        Random random = new Random();
        Clients.timeInterval = new int[locations+1][locations+1];
        for (int i=1; i<=locations; i++) {
            for (int j=1; j<=locations; j++) {
                if (i==j || (i>5 && j>5)) {
                    Clients.timeInterval[i][j] = 0;
                } else if (j>i) {
                    Clients.timeInterval[i][j] = random.nextInt(9) + 1;
                } else {
                    Clients.timeInterval[i][j] = Clients.timeInterval[j][i];
                }
            }
        }
    }
    public static int[][] getTimeInterval() {
        return timeInterval;
    }
    public static void printTimeIntervalMatrix() {
        int[][] timeMatrix = Clients.getTimeInterval();
        System.out.println("Time interval matrix is: ");
        for (int i = 1; i < timeMatrix.length; i++) {
            for (int j = 1; j < timeMatrix[i].length; j++) {
                System.out.print(timeMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Type getClientType() {
        return clientType;
    }
    public void setClientType(Type clientType) {
        this.clientType = clientType;
    }

    /** verifies if the client needs to be added as new
     *
     * @param c the given client
     * @param clients the existing clients
     * @return true if the client is new and needs to be added, false otherwise
     */
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
                ", clientType=" + clientType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return Objects.equals(name, clients.name) && clientType == clients.clientType;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, clientType);
    }

}