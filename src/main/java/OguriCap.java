import java.util.Scanner;

public class OguriCap {

    static String line = "    ____________________________________________________________"; // 4 spaces for line indentation
    static String spacing = "     "; // 5 spaces for indentation for bot responses

    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void main(String[] args) {

        String chatBotName = "Oguri Cap";
        String byeMsg = "Bye. Hope to see you again soon!";

        System.out.println(line);
        System.out.println(spacing + "Hello! I'm " + chatBotName);
        System.out.println(spacing + "All right, what's first on today's agenda?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();
            String taskParts[] = input.split(" ", 2);
            String taskCmd = taskParts[0];

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

                if (parts.length < 2) {
                    System.out.println(line);
                    System.out.println(spacing + "OOPS!!! Please specify a task number to mark.");
                    System.out.println(line);
                    continue;
                }
                int index;

                try {
                    index = Integer.parseInt(parts[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println(line);
                    System.out.println(spacing + "OOPS!!! Please provide a valid task number.");
                    System.out.println(line);
                    continue;
                }

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

                if (parts.length < 2) {
                    System.out.println(line);
                    System.out.println(spacing + "OOPS!!! Please specify a task number to mark.");
                    System.out.println(line);
                    continue;
                }
                int index;

                try {
                    index = Integer.parseInt(parts[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println(line);
                    System.out.println(spacing + "OOPS!!! Please provide a valid task number.");
                    System.out.println(line);
                    continue;
                }

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

            switch (taskCmd) {
            case "todo":
                handleTodo(taskParts);
                break;
            case "deadline":
                handleDeadline(taskParts);
                break;
            case "event":
                handleEvent(taskParts);
                break;
            default:
                System.out.println(line);
                System.out.println(spacing + "OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }
        }
    }
    private static void handleTodo(String[] parts) {
        if (parts.length < 2 || parts[1].isBlank()) {
            System.out.println(line);
            System.out.println(spacing + "OOPS!!! The description of a todo cannot be empty.");
            System.out.println(line);
            return;
        }
        Todo todo = new Todo(parts[1].trim());
        addTask(todo);
    }

    private static void handleDeadline(String[] parts) {
        if (parts.length < 2 || parts[1].isBlank()) {
            System.out.println(line);
            System.out.println(spacing + "OOPS!!! The description of a deadline cannot be empty.");
            System.out.println(line);
            return;
        }
        String[] deadlineParts = parts[1].split("/by", 2);
        if (deadlineParts.length < 2) {
            System.out.println(line);
            System.out.println(spacing + "OOPS!!! Deadline must have /by.");
            System.out.println(line);
            return;
        }
        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        Deadline deadline = new Deadline(description, by);
        addTask(deadline);
    }

    private static void handleEvent(String[] parts) {
        if (parts.length < 2 || parts[1].isBlank()) {
            System.out.println(line);
            System.out.println(spacing + "OOPS!!! The description of an event cannot be empty.");
            System.out.println(line);
            return;
        }
        String[] fromSplit = parts[1].split("/from", 2);
        if  (fromSplit.length < 2) {
            System.out.println(line);
            System.out.println(spacing + "OOPS!!! Event must have /from.");
            System.out.println(line);
            return;
        }
        String description = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            System.out.println(line);
            System.out.println(spacing + "OOPS!!! Event must have /to.");
            System.out.println(line);
            return;
        }
        String from = toSplit[0].trim();
        String to = toSplit[1].trim();
        Event event = new Event(description, from, to);
        addTask(event);
    }

    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println(line);
        System.out.println(spacing + "Got it. I've added this task:");
        System.out.println(spacing + "  " + task);
        System.out.println(spacing + "Now you have " + taskCount + " tasks in the list.");
        System.out.println(line);
    }
}
