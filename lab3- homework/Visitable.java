import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable {
    Map<LocalDate, TimeInterval> getTimetable();
    default LocalTime getOpeningHour(LocalDate date) {
        Map<LocalDate, TimeInterval> timetable = getTimetable();
        TimeInterval interval = timetable.get(date);
        if (interval != null) {
            LocalTime startTime = interval.getFirst();
            //System.out.println("Opening hour for " + date + " is " + startTime);
            return startTime;
        }
        //System.out.print("It is closed on " + date);
        return LocalTime.MIDNIGHT;
    }
}

