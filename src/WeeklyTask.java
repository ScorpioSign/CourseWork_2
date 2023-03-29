import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, String description, Type type) {
        super(title, description, type);
    }

    @Override
    public boolean appearsIn(LocalDate dateTime) {
        return getDateTime().getDayOfWeek().equals(dateTime.getDayOfWeek());
    }

    @Override
    public LocalDate getNextDate(LocalDateTime dateTime) {
        LocalDate localDate = dateTime.toLocalDate();
        return localDate.plusWeeks(1);
    }

    @Override
    public String toString() {
        return super.toString() + "Выполнять еженедельно\n";
    }
}

