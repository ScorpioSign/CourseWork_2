import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;

public class Task {
    private static int counter;
    private String title;
    private final Type type;
    private final int id;
    private final LocalDateTime dateTime;
    private String description;
    private final Repeatability repeatability;

    public Task(String title, Type type, String description, Repeatability repeatability) {
        counter++;
        this.title = title;
        this.type = type;
        this.id = counter;
        this.dateTime = LocalDateTime.now();
        this.description = description;
        this.repeatability = repeatability;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && title.equals(task.title) && type == task.type && dateTime.equals(task.dateTime) && description.equals(task.description) && repeatability == task.repeatability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description, repeatability);
    }

    @Override
    public String toString() {
        return "\nЗадача №" + id + " " + title +
                "\n" + type +
                "\nОписание задачи: " + description +
                "\n" + repeatability +
                "\nДата создания: " + dateTime + "\n";
    }


    public boolean appearsIn(LocalDate date) {
        Period p = Period.between(date, dateTime.toLocalDate());
        boolean b = false;
        switch (repeatability) {
            case DAILY_TASK -> b = true;

            case WEEKLY_TASK -> b = date.getDayOfWeek() == dateTime.getDayOfWeek();

            case YEARLY_TASK -> b = p.getDays() == 0 && p.getMonths() == 0;

            case MONTHLY_TASK -> b = p.getDays() == 0;

            case ONE_TIME_TASK -> b = (date.compareTo(this.getDate()) == 0);

        }
        return b;
    }


    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public Boolean predicate(LocalDate date) {
        return (this.appearsIn(date) && !date.isBefore(this.getDate()));
    }
}
