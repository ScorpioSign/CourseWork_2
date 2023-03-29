import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            printMenu();
            System.out.print("Выберите пункт меню: ");
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                switch (menu) {
                    case 1 -> {
                        scanner.nextLine();
                        System.out.println("Введите название задачи");
                        String title = scanner.nextLine();

                        System.out.println("Введите тип задачи: WORK, PERSONAL");
                        String typeString = scanner.nextLine();


                        System.out.println("Введите описание задачи");
                        String description = scanner.nextLine();

                        System.out.println("Введите повторяемость задачи: ONE_TIME_TASK, YEARLY_TASK, DAILY_TASK, MONTHLY_TASK, WEEKLY_TASK");
                        String repeatabilityString = scanner.nextLine();
                        try {
                            TaskService.addTask(title, description, typeString, repeatabilityString);
                        } catch (IncorrectArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println();
                    }


                    case 2 -> {
                        System.out.println("Введите id удаляемой задачи");
                        int idRemovingTask = scanner.nextInt();
                        try {
                            TaskService.removeTask(idRemovingTask);
                        } catch (TaskNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println();
                    }

                    case 3 -> {
                        System.out.print("Введите год: ");
                        int year = scanner.nextInt();
                        System.out.print("Введите номер месяца: ");
                        int month = scanner.nextInt();
                        System.out.print("Введите день задачи: ");
                        int day = scanner.nextInt();
                        LocalDate dateTasks = LocalDate.of(year, month, day);
                        System.out.println("Список задач на " + dateTasks + ":");
                        System.out.println(TaskService.getAllByDate(dateTasks));
                        System.out.println();
                    }
                    case 4 -> {
                        System.out.println("Архив удаленных задач: ");
                        System.out.println(TaskService.getRemovedTaskMap());
                        System.out.println();
                    }
                    case 5 -> {
                        System.out.println("Введите id редактируемой задачи: ");
                        try {
                            int idTask = scanner.nextInt();
                            System.out.println("Текущее название: " + TaskService.getTasksMap().get(idTask).getTitle());
                            System.out.println("Введите новое название задачи: ");
                            scanner.nextLine();
                            String title = scanner.nextLine();
                            TaskService.updateTitle(idTask, title);
                        } catch (RuntimeException e) {
                            System.out.println("Задача не найдена");
                        }
                        System.out.println();
                    }
                    case 6 -> {
                        System.out.println("Введите id редактируемой задачи: ");
                        try {
                            int idTask = scanner.nextInt();
                            System.out.println("Текущее описание: " + TaskService.getTasksMap().get(idTask).getDescription());
                            System.out.println("Введите новое описание задачи: ");
                            scanner.nextLine();
                            String description = scanner.nextLine();
                            TaskService.updateDescription(idTask, description);
                        } catch (RuntimeException e) {
                            System.out.println("Задача не найдена");
                        }
                        System.out.println();
                    }
                    case 7 -> {
                        System.out.println("Список всех задач, сгруппированных по дате");
                        System.out.println(TaskService.getAllGroupByDate());
                    }


                    case 0 -> {
                        break label;
                    }
                    default -> System.out.println("Такого пункта в меню нет! Нужно выбрать из указанного списка: ");
                }
            }
        }
    }


    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачи на указанный день
                        4. Получить архивные задачи
                        5. Редактировать заголовок
                        6. Редактировать описание
                        7. Получить задачи, сгруппированные по датам
                        0. Выход
                        """
        );
    }
}