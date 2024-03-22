import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Museum extends Attraction implements Visitable, Payable {
    String name;
    String location;
    LocalTime time;
    LocalDate date;
    double ticketPrice;
    Map<LocalDate, TimeInterval> timetable;

    public Museum(String name, String location) {
        this.name = name;
        this.location = location;
        this.timetable = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Museum{" +
                "name='" + name + '\'' +
                ", ticketPrice=" + ticketPrice +
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

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimetable(LocalDate date, LocalTime startTime, LocalTime endTime) {
        TimeInterval interval = new TimeInterval(startTime, endTime);
        this.timetable.put(date, interval);
    }
    public void setTicketPrice(double price) {
        this.ticketPrice=price;
    }
    @Override // from Visitable interface
    public Map<LocalDate, TimeInterval> getTimetable() {
        return this.timetable;
    }
    @Override // from Payable interface
    public double getTicketPrice() {
        return ticketPrice;
    }

}
