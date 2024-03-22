import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Attraction implements Comparable<Attraction>, Visitable {
        private String name;

    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getLocation();
    public abstract void setLocation(String location);

    @Override
    public LocalTime getOpeningHour(LocalDate date) {
        return Visitable.super.getOpeningHour(date);
    }

    @Override
        public String toString() {
            return "Attraction{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Attraction other) {
            if (this.name == null) {
                if (other.name == null) {
                    return 0; //both are null
                }
                return -1; //error when other.name is not null
            }

            if (other.name == null) {
                return 1;
            }

            return this.name.compareTo(other.name);
        }

    }
