import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask(String title, String description, Type type) {

        super(title, description, type);

    }

    @Override
    public boolean appearsIn(LocalDate dateTime) {
        return true;
    }

    @Override
    public LocalDate getNextDate(LocalDateTime dateTime) {
        LocalDate localDate = dateTime.toLocalDate();
        return localDate.plusDays(1);
    }

    @Override
    public String toString() {
        return super.toString() + "Выполнять ежедневно\n";
    }
}
