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

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(spacing + byeMsg);
                System.out.println(line);
                break;
            }

            System.out.println(line);
            System.out.println(spacing + input);
            System.out.println(line);
        }
    }
}
