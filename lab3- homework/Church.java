import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Church extends Attraction implements Visitable {
    String name;
    String location;
    Map<LocalDate, TimeInterval> timetable;

    public Church(String name, String location) {
        this.name = name;
        this.location = location;
        this.timetable = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Church{" +
                "name='" + name + '\'' +
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

    public void setTimetable(LocalDate date, LocalTime startTime, LocalTime endTime) {
        TimeInterval interval = new TimeInterval(startTime, endTime);
        this.timetable.put(date, interval);
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return this.timetable;
    }
}

