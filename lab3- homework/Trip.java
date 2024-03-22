import java.time.LocalDate;
import java.util.*;

public class Trip {
    private String city;
    private LocalDate start;
    private LocalDate end;
    private final List<Attraction> attractions;

    public Trip(String city, LocalDate start, LocalDate end) {
        this.city = city;
        this.start = start;
        this.end = end;
        this.attractions = new ArrayList<>();
    }

    public Trip(String city) {
        this.city = city;
        this.attractions = new ArrayList<>();
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }

    /**
     * @return the list of attractions
     */
    public List<Attraction> getAttractions() {
        return this.attractions;
    }

    /**
     * Adds a given attraction into the list of attractions, based on the city the trip is in
     * if the attraction is in a wrong city, we get an error message
     * @param a attractin to add to the list of attractions
     * @param timetable we need the timetable for each attraction so we can filter by the date key of the map and add the attraction to the trip or not
     */
    public void addAttractions(Attraction a, Map<LocalDate, TimeInterval> timetable) {
        for (Map.Entry<LocalDate, TimeInterval> entry : timetable.entrySet()) {
            LocalDate date = entry.getKey();
            if (a.getLocation().equals(this.city)) {
                this.attractions.add(a);
                return;
            } else {
                System.out.println("On " + date + " we are not in " + a.getLocation() + ", we cannot visit " + a.getName());
            }
        }
    }

    /**
     * displays the locations that are visitable and are not payable, sorted by their opening hour
     * @param date the given date we are supposed to display
     */
    public void displayVisitableNonPayableAttractions(LocalDate date) {
        List<Attraction> visitableNonPayableAttractions = new ArrayList<>();
        for (Attraction attraction : attractions) {
            if (!(attraction instanceof Payable)) {
                visitableNonPayableAttractions.add(attraction);
            }
        }
        Collections.sort(visitableNonPayableAttractions, (location1, location2) -> location1.getOpeningHour(date).compareTo(location2.getOpeningHour(date)));
        // display the sorted list of attractions
        System.out.println("Visitable and non-payable attractions for " + date + " sorted by their opening hour:");
        for (Attraction a : visitableNonPayableAttractions) {
            System.out.println("- " + a.getName() + ": " + a.getOpeningHour(date));
        }
    }

    @Override
    public String toString() {
        return "Trip{" +
                "city='" + city + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", attractions=" + attractions +
                '}';
    }
}
