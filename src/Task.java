import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private static int counter;
    private String title;
    private final Type type;
    private final int id;
    private final LocalDateTime dateTime;
    private String description;


    public Task(String title, String description, Type type) {
        counter++;
        this.title = title;
        this.type = type;
        this.id = counter;
        this.dateTime = LocalDateTime.now();
        this.description = description;

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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLocalDateTime() {
        return dateTime;
    }

    public abstract boolean appearsIn(LocalDate dateTime);

    public abstract LocalDate getNextDate(LocalDateTime dateTime);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(description, task.description) && type == task.type && Objects.equals(dateTime, task.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description);
    }

    @Override
    public String toString() {
        return "\nЗадача №" + id + " " + title +
                "\n   " + type.getTypeTask() +
                "\n   Описание задачи: " + description +

                "\n   Дата создания: " + dateTime + "\n";
    }


    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }
}



