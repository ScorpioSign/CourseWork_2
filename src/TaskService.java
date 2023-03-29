import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private static final Map<Integer, Task> tasksMap = new HashMap<>();
    private static final Map<Integer, Task> removedTaskMap = new HashMap<>();

    public static Map<Integer, Task> getTasksMap() {
        return tasksMap;
    }

    public static Map<Integer, Task> getRemovedTaskMap() {
        return removedTaskMap;
    }

    public static void addTask(String title, String description, String typeString, String repeatabilityString) throws IncorrectArgumentException {


        if (title.isEmpty() || description.isEmpty() || typeString.isEmpty()) {
            throw new IncorrectArgumentException("В задаче не может быть пустых полей! \n Попробуйте ещё раз: ");
        }
        boolean typeValid = typeString.matches("(WORK||PERSONAL)");

        if (typeValid) {
            if (repeatabilityString.equals("ONE_TIME_TASK")) {
                Task task = new OneTimeTask(title, description, Type.valueOf(typeString), LocalDate.now());
                tasksMap.put(task.getId(), task);
                System.out.println("Задача добавлена");
            } else if (repeatabilityString.equals("DAILY_TASK")) {
                Task task = new DailyTask(title, description, Type.valueOf(typeString));
                tasksMap.put(task.getId(), task);
                System.out.println("Задача добавлена");

            } else if (repeatabilityString.equals("WEEKLY_TASK")) {
                Task task = new WeeklyTask(title, description, Type.valueOf(typeString));
                tasksMap.put(task.getId(), task);
                System.out.println("Задача добавлена");
            } else if (repeatabilityString.equals("MONTHLY_TASK")) {
                Task task = new MonthlyTask(title, description, Type.valueOf(typeString));
                tasksMap.put(task.getId(), task);
                System.out.println("Задача добавлена");
            } else if (repeatabilityString.equals("YEARLY_TASK")) {
                Task task = new YearlyTask(title, description, Type.valueOf(typeString));
                tasksMap.put(task.getId(), task);
                System.out.println("Задача добавлена");
            } else {
                throw new IncorrectArgumentException("Введённые данные некорректны. Повторяемость задач необходимо выбирать только из предложенных вариантов!");
            }

        } else {
            throw new IncorrectArgumentException("Введённые данные некорректны. Тип задач необходимо выбирать только из предложенных вариантов!");
        }


    }

    public static void removeTask(int id) throws TaskNotFoundException {
        if (tasksMap.containsKey(id)) {
            removedTaskMap.put(id, tasksMap.get(id));
            tasksMap.remove(id);
            System.out.println("Задача удалена");
        } else {
            throw new TaskNotFoundException("Задача не найдена");
        }
    }


    public static List<Task> getAllByDate(LocalDate date) {
        return tasksMap
                .values()
                .stream()
                .filter(task -> task.appearsIn(date))
                .collect(Collectors.toList());
    }

    public static void updateDescription(int id, String description) throws RuntimeException {

        if (tasksMap.containsKey(id)) {
            tasksMap.get(id).setDescription(description);
            System.out.println("Описание изменено");
        } else {
            throw new RuntimeException();

        }

    }

    public static void updateTitle(int id, String title) throws RuntimeException {
        if (tasksMap.containsKey(id)) {
            tasksMap.get(id).setTitle(title);
            System.out.println("Название изменено");
        } else {
            throw new RuntimeException();
        }
    }

    public static Map<LocalDate, List<Task>> getAllGroupByDate() {
        List<LocalDate> localDateList = tasksMap
                .values()
                .stream()
                .map(task -> task.getNextDate(task.getLocalDateTime()))
                .sorted(LocalDate::compareTo)
                .collect(Collectors.toList());

        Map<LocalDate, List<Task>> allTasksGroupedByDate = new LinkedHashMap<>();
        for (LocalDate localDate : localDateList) {
            allTasksGroupedByDate.put(localDate, getAllByDate(localDate));
        }
        return allTasksGroupedByDate;
    }


}
