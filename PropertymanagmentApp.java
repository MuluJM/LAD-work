import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private List<Property> properties;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        //This line of code initializes a new ArrayList called "properties" as an instance variable for the User class. It will be used to store instances of the Property class associated with the user.
        this.properties = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void deleteProperty(Property property) {
        properties.remove(property);
    }

    public void editProperty(Property oldProperty, Property newProperty) {
        int index = properties.indexOf(oldProperty);
        if (index != -1) {
            properties.set(index, newProperty);
        }
    }
}

class Property {
    private String name;
    private String description;

    public Property(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

    class PropertyManagementApp {
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
                System.out.println("1. Add Property");
                System.out.println("2. Delete Property");
                System.out.println("3. Edit Property");
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

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        User newUser = new User(username, password);
        users.put(username, newUser);
        System.out.println("Registration successful.");
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            currentUser = user;
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void addProperty(Scanner scanner) {
        System.out.print("Enter property name: ");
        String name = scanner.next();
        System.out.print("Enter property description: ");
        String description = scanner.next();
        Property property = new Property(name, description);
        currentUser.addProperty(property);
        System.out.println("Property added successfully.");
    }

    private static void deleteProperty(Scanner scanner) {
        System.out.print("Enter the name of the property you want to delete: ");
        String name = scanner.next();
        List<Property> properties = currentUser.getProperties();
        Property propertyToDelete = null;
        for (Property property : properties) {
            if (property.getName().equals(name)) {
                propertyToDelete = property;
                break;
            }
        }
        if (propertyToDelete != null) {
            currentUser.deleteProperty(propertyToDelete);
            System.out.println("Property deleted successfully.");
        } else {
            System.out.println("Property not found.");
        }
    }

    private static void editProperty(Scanner scanner) {
        System.out.print("Enter the name of the property you want to edit: ");
        String name = scanner.next();
        List<Property> properties = currentUser.getProperties();
        Property propertyToEdit = null;
        for (Property property : properties) {
            if (property.getName().equals(name)) {
                propertyToEdit = property;
                break;
            }
        }
        if (propertyToEdit != null) {
            System.out.print("Enter new property name: ");
            String newName = scanner.next();
            System.out.print("Enter new property description: ");
            String newDescription = scanner.next();
            Property newProperty = new Property(newName, newDescription);
            currentUser.editProperty(propertyToEdit, newProperty);
            System.out.println("Property edited successfully.");
        } else {
            System.out.println("Property not found.");
        }
    }

    private static void showProperties(User user) {
        List<Property> properties = user.getProperties();
        if (properties.isEmpty()) {
            System.out.println("You have no properties.");
        } else {
            System.out.println("Your Properties:");
            for (Property property : properties) {
                System.out.println("Name: " + property.getName() + ", Description: " + property.getDescription());
            }
        }
    }

    private static void searchProperties(Scanner scanner) {
        System.out.print("Enter property name to search: ");
        String searchName = scanner.next();
        System.out.println("Search Results:");
        for (User user : users.values()) {
            for (Property property : user.getProperties()) {
                if (property.getName().contains(searchName)) {
                    System.out.println("User: " + user.getUsername() + ", Name: " + property.getName() + ", Description: " + property.getDescription());
                }
            }
        }
    }
}
