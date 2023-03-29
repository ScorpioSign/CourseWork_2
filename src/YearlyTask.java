import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(String title, String description, Type type) {
        super(title, description, type);
    }

    @Override
    public boolean appearsIn(LocalDate dateTime) {

        return getDateTime().getDayOfMonth() == dateTime.getDayOfMonth() &&
                getLocalDateTime().getMonthValue() == dateTime.getMonthValue();
    }

    @Override
    public LocalDate getNextDate(LocalDateTime dateTime) {
        LocalDate localDate = dateTime.toLocalDate();
        return localDate.plusYears(1);
    }

    @Override
    public String toString() {
        return super.toString() + "Выполнять ежегодно\n";
    }
}

