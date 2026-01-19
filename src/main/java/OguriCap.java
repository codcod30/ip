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

        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(spacing + byeMsg);
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(spacing + (i+1) + ". " + tasks[i]);
                }
                System.out.println(line);
            } else {
                if (taskCount < tasks.length) {
                    tasks[taskCount] = input;
                    taskCount++;
                    System.out.println(line);
                    System.out.println(spacing + "added: " + input);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println(spacing + "Task list is full. Cannot add more tasks.");
                    System.out.println(line);
                }
            }

        }
    }
}
