import java.util.Scanner;

public class OguriCap {
    public static void main(String[] args) {

        String chatBotName = "Oguri Cap";
        String byeMsg = "Bye. Hope to see you again soon!";
        String line = "    ____________________________________________________________"; // 4 spaces for line indentation
        String spacing = "     "; // 5 spaces for indentation for bot responses

        System.out.println(line);
        System.out.println(spacing + "Hello! I'm " + chatBotName);
        System.out.println(spacing + "All right, what's first on today's agenda?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(spacing + byeMsg);
                System.out.println(line);
                break;
            }
            if (input.equals("list")) {
                System.out.println(line);
                System.out.println(spacing + "Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(spacing + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
                continue;
            }
            if (input.startsWith("mark ")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;

                if (index < 0 || index >= taskCount) {
                    System.out.println(line);
                    System.out.println(spacing + "Task number out of range.");
                    System.out.println(line);
                    continue;
                }
                tasks[index].markAsDone();

                System.out.println(line);
                System.out.println(spacing + "Nice! I've marked this task as done:");
                System.out.println(spacing + "  " + tasks[index]);
                System.out.println(line);
                continue;
            }
            if (input.startsWith("unmark ")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;

                if (index < 0 || index >= taskCount) {
                    System.out.println(line);
                    System.out.println(spacing + "Task number out of range.");
                    System.out.println(line);
                    continue;
                }
                tasks[index].markNotDone();

                System.out.println(line);
                System.out.println(spacing + "OK, I've marked this task as not done yet:");
                System.out.println(spacing + "  " + tasks[index]);
                System.out.println(line);
                continue;
            }

            Task inputTask = new Task(input);
            tasks[taskCount] = inputTask;
            taskCount++;

            System.out.println(line);
            System.out.println(spacing + "added: " + input);
            System.out.println(line);
        }
    }
}
