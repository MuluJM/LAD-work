import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PropertymanagmentApp {
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        registerUser(scanner);
                        break;
                    case 2:
                        loginUser(scanner);
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("1. Add property");
                System.out.println("2. Delete property");
                System.out.println("3. Edit property");
                System.out.println("4. Show My Properties");
                System.out.println("5. Search Properties");
                System.out.println("6. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addProperty(scanner);
                        break;
                    case 2:
                        deleteProperty(scanner);
                        break;
                    case 3:
                        editProperty(scanner);
                        break;
                    case 4:
                        showProperties(currentUser);
                        break;
                    case 5:
                        searchProperties(scanner);
                        break;
                    case 6:
                        currentUser = null;
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }   
}
