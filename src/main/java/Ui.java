import java.util.Scanner;

public class Ui {
    private static final String LINE = "    ____________________________________________________________"; // 4 spaces for line indentation
    private static final String SPACING = "     "; // 5 spaces for indentation for bot responses
    private Scanner scanner = new Scanner(System.in);

    public void showWelcome(String name) {
        System.out.println(LINE);
        System.out.println(SPACING + "Hello! I'm " + name);
        System.out.println(SPACING + "All right, what's first on today's agenda?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showMessage(String message) {
        System.out.println(SPACING + message);
    }

    public void showTask(String message, Task task) {
        System.out.println(SPACING + message);
        System.out.println(SPACING + "  " + task);
    }

    public void showTaskCount(int count) {
        System.out.println(SPACING + "Now you have " + count + " tasks in the list.");
    }

    public void showError(String message) {
        System.out.println(SPACING + message);
    }
}
