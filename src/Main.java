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
                            TaskService.addTask(title, typeString, description, repeatabilityString);
                        } catch (IncorrectArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println();
                    }


                    case 2 -> {
                        System.out.println("Введите id удаляемойзадачи");
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
                        System.out.println(TaskService.getAllByDate(dateTasks));

                        System.out.println();
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
                        0. Выход
                        """
        );
    }
}