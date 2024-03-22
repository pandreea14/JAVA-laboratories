import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Concert extends Attraction implements Visitable, Payable {
 String name;
 String description;
 String location;
 double ticketPrice;
 Map<LocalDate, TimeInterval> timetable;

 public Concert(String name, String location) {
  this.name = name;
  this.location = location;
  this.timetable = new HashMap<>();
 }

 public Concert(String name, String location, double ticketPrice) {
   this.name = name;
   this.location = location;
   this.ticketPrice = ticketPrice;
   this.timetable = new HashMap<>();
 }

 @Override
 public String toString() {
  return "Concert{" +
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
 public String getDescription() {
  return description;
 }
 public void setDescription(String description) {
  this.description = description;
 }

 public String getLocation() {
  return location;
 }
 public void setLocation(String location) {
  this.location = location;
 }

 //TimeInterval e practic un Pair<LocalTime, LocalTime>
 @Override
 public Map<LocalDate,TimeInterval> getTimetable() {
     return this.timetable;
 }
 public void setTimetable(LocalDate date, LocalTime startTime, LocalTime endTime) {
   TimeInterval interval = new TimeInterval(startTime, endTime);
   this.timetable.put(date, interval);
 }
 @Override
 public double getTicketPrice() {
  return ticketPrice;
 }
 public void setTicketPrice(double price) {
   this.ticketPrice = price;
 }
}
