import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelPlan {
    Map<Attraction, LocalDate> plan;
    Trip trip;

    public TravelPlan() {
        this.plan = new HashMap<>();
    }

    /**
     * for each attraction I iterate over the LocalDate map and add the date into the plan map<Attraction, LocalDate>
     * @param trip existing trip that is added to the travel plan
     */
    public void addTrip(Trip trip) {
        this.trip = trip;
        List<Attraction> attractions = trip.getAttractions();
        for (Attraction a:attractions) {
            for (Map.Entry<LocalDate, TimeInterval> entry : a.getTimetable().entrySet()) {
                System.out.println("addTrip- " + a.getName() + " on " + entry.getKey());
                this.plan.put(a, entry.getKey());
            }
        }
    }

    public void printTravelPlan() {
        System.out.println("Travel Plan for trip to " + trip.getCity() + " from " + trip.getStart() + " to " + trip.getEnd() + " is: ");

        for (Map.Entry<Attraction, LocalDate> entry : plan.entrySet()) {
            Attraction attraction = entry.getKey();
            for (Map.Entry<LocalDate, TimeInterval> e : attraction.getTimetable().entrySet()) {
                LocalDate attractionDate = e.getKey();
                if(attractionDate.compareTo(trip.getStart()) >= 0 && attractionDate.compareTo(trip.getEnd()) <= 0) {
                    System.out.println("-On " + attractionDate + " we will visit " + attraction.getName());
                    break;
                } //else {
                    //System.out.println("On " + attractionDate + " we are not in the trip, we cannot visit " + attraction.getName());
                //}
            }
        }
    }
}
