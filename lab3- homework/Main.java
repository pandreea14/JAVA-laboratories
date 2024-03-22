import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Museum museum1 = new Museum("Muzeul Antipa", "Rome");
        museum1.setTimetable(LocalDate.of(2024,4,20), LocalTime.of(8,30), LocalTime.of(16,50));
        museum1.setTimetable(LocalDate.of(2024,4,21), LocalTime.of(9,0), LocalTime.of(16,50));
        museum1.setTimetable(LocalDate.of(2024,4,23), LocalTime.of(8,50), LocalTime.of(16,50));
        museum1.setTicketPrice(50.6);
        Museum museum2 = new Museum("Muzeul Zapezii", "Barcelona");
        museum2.setTimetable(LocalDate.of(2024,5,23), LocalTime.of(8,30), LocalTime.of(16,50));
        museum2.setTimetable(LocalDate.of(2024,4,21), LocalTime.of(8,30), LocalTime.of(16,50));
        museum2.setTimetable(LocalDate.of(2024,4,20), LocalTime.of(11,30), LocalTime.of(17,50));
        museum2.setTicketPrice(20);

        Church church1 = new Church("Sf Parascheva", "Rome");
        church1.setTimetable(LocalDate.of(2024,4,20), LocalTime.of(8,0), LocalTime.of(21,0));
        church1.setTimetable(LocalDate.of(2024,4,19), LocalTime.of(9,1), LocalTime.of(22,0));
        church1.setTimetable(LocalDate.of(2024,4,18), LocalTime.of(8,30), LocalTime.of(19,0));
        church1.setTimetable(LocalDate.of(2024,4,21), LocalTime.of(9,0), LocalTime.of(20,0));
        church1.setTimetable(LocalDate.of(2024,4,22), LocalTime.of(10,0), LocalTime.of(23,0));
        Church church2 = new Church("Sf Lazar", "Barcelona");
        church2.setTimetable(LocalDate.of(2024,3,19), LocalTime.of(9,0), LocalTime.of(16,0));
        church2.setTimetable(LocalDate.of(2024,3,19), LocalTime.of(9,0), LocalTime.of(16,0));
        church2.setTimetable(LocalDate.of(2024,3,19), LocalTime.of(9,0), LocalTime.of(16,0));
        church2.setTimetable(LocalDate.of(2024,3,19), LocalTime.of(9,0), LocalTime.of(16,0));
        church2.setTimetable(LocalDate.of(2024,3,19), LocalTime.of(9,0), LocalTime.of(16,0));

        Statue statue1 = new Statue("Romulus", "Rome");
        statue1.setTimetable(LocalDate.of(2024,4,20), LocalTime.of(7,0), LocalTime.of(23,59));
        Statue statue2 = new Statue("fontava di trevi", "Rome");
        statue2.setTimetable(LocalDate.of(2024,4,21), LocalTime.of(4,0), LocalTime.of(23,59));
        Statue statue3 = new Statue("chica del fantana", "Barcelona");
        statue3.setTimetable(LocalDate.of(2024,9,28), LocalTime.of(5,0), LocalTime.of(23,59));

        Concert concert1 = new Concert("Mirel din turnu magurele Concert","Rome");
        concert1.setTimetable(LocalDate.of(2024,4,25), LocalTime.of(19,0), LocalTime.of(23,34));
        concert1.setTicketPrice(152.34);
        Concert concert2 = new Concert("Nothing But Thieves Concert","Barcelona");
        concert2.setTimetable(LocalDate.of(2024,9,20), LocalTime.of(22,0), LocalTime.of(1,34));
        concert2.setTicketPrice(100.34);
        Concert concert3 = new Concert("CCCConcert","Rome");
        concert3.setTimetable(LocalDate.of(2024,4,21), LocalTime.of(12,0), LocalTime.of(23,34));
        concert3.setTicketPrice(15.34);

        // getTimetable() for all visitable attractions in variable visitableTimetable
        Map<LocalDate, TimeInterval> visitableTimetable;
        Visitable[] arr = {museum1, museum2, church1, church2, statue1, statue2, statue3, concert1, concert2, concert3};
        System.out.println("Visitable attractions are: ");
        for (Visitable visitable : arr) {
            System.out.print(visitable + ", on date and hour = ");
            visitableTimetable = visitable.getTimetable();
            System.out.println(visitableTimetable);
        }
        System.out.println();

        // creating the trip
        Trip trip1 = new Trip("Rome", LocalDate.of(2024,4,21), LocalDate.of(2024,5,8));

        // adding attractions to the trip, based on the time period and the city the trip is in
        trip1.addAttractions(museum1, museum1.getTimetable());
        trip1.addAttractions(statue1, statue1.getTimetable());
        trip1.addAttractions(statue2, statue2.getTimetable());
        trip1.addAttractions(church1, church1.getTimetable());
        trip1.addAttractions(concert1,concert1.getTimetable());
        trip1.addAttractions(concert3,concert3.getTimetable());
        trip1.addAttractions(concert2,concert2.getTimetable()); // orasul e pus intentionat prost => nu se adauga la trip
        List<Attraction> attractionsTrip1 = trip1.getAttractions();
        System.out.println("Attractions for " + trip1.getCity() + " are: ");
        for (Attraction a : attractionsTrip1) {
            if (a.getLocation().equals(trip1.getCity())) {
                System.out.println(a);
            }
        }
        System.out.println();
        trip1.displayVisitableNonPayableAttractions(LocalDate.of(2024, 4, 21));

        // creating the travel plan for the existing trip
        TravelPlan travelPlan1 = new TravelPlan();
        travelPlan1.addTrip(trip1);
        System.out.println(trip1);
        System.out.println();
        travelPlan1.printTravelPlan();

        //Trip trip2 = new Trip("Barcelona", LocalDate.of(2024,9,20), LocalDate.of(2024,9,30));
        //System.out.println(trip2);

    }
}

