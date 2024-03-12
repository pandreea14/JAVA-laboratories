class Depots {
    String name;
    String location;
    int id;
    Vehicles[] vehicule;

    public Depots(String name, String location, int id) {
        this.name = name;
        this.location = location;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Depots{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", id=" + id +
                '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class Vehicles {
    int tour;
    String registrationNumber;

    public Vehicles(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "Vehicles{" +
                "tour=" + tour +
                ", registrationNumber='" + registrationNumber + '\'' +
                '}';
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}

class Clients {
    String name;
    int timeInterval;
    Type clientType;

    public Clients(String name, int timeInterval, Type clientType) {
        this.name = name;
        this.timeInterval = timeInterval;
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "name='" + name + '\'' +
                ", timeInterval=" + timeInterval +
                ", clientType=" + clientType +
                '}';
    }

    public Type getClientType() {
        return clientType;
    }

    public void setClientType(Type clientType) {
        this.clientType = clientType;
    }

    public enum Type {
        regular, premium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
public class Main {
    public static void main(String[] args) {
        Clients koala = new Clients("kiki", 12, Clients.Type.premium);
        System.out.println(koala);

        Depots depou = new Depots("gara nord", "bucuresti", 123);
        System.out.println(depou);

        Vehicles skoda = new Vehicles("BV12CVC");
        System.out.println(skoda);
    }
}