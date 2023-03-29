import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private static final Map<Integer, Task> tasksMap = new HashMap<>();

    public static Map<Integer, Task> getTasksMap() {
        return tasksMap;
    }


    public static void addTask(String title, String typeString, String description, String repeatabilityString) throws IncorrectArgumentException {
        if (title.isEmpty() || description.isEmpty() || typeString.isEmpty() || repeatabilityString.isEmpty()) {
            throw new IncorrectArgumentException("В задаче не может быть пустых полей! \n Попробуйте ещё раз: ");
        }
        boolean typeValid = typeString.matches("(WORK||PERSONAL)");
        boolean repeatabilityValid = repeatabilityString.matches("(ONE_TIME_TASK||YEARLY_TASK||DAILY_TASK||MONTHLY_TASK||WEEKLY_TASK)");
        if (typeValid && repeatabilityValid) {
            Task task = new Task(title, Type.valueOf(typeString), description, Repeatability.valueOf(repeatabilityString));
            tasksMap.put(task.getId(), task);
            System.out.println("Задача добавлена");
        } else {
            throw new IncorrectArgumentException("Введённые данные некорректны. Тип и повторяемость задач необходимо выбирать только из предложенных вариантов!");
        }
    }

    public static void removeTask(int id) throws TaskNotFoundException {
        if (tasksMap.containsKey(id)) {
            tasksMap.remove(id);
            System.out.println("Задача удалена");
        } else {
            throw new TaskNotFoundException("Задача не найдена");
        }
    }


    public static Map<Integer, Task> getAllByDate(LocalDate date) {

        Map<Integer, Task> newTaskMap = tasksMap.entrySet().stream()
                .filter(entry -> entry.getValue().predicate(date))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return newTaskMap;
    }


}
