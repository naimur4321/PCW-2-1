import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid number of arguments.");
            printUsage();
            return;
        }

        String command = args[0];

        if (command.equals("a")) {
            displayAllStudents();  // Show all students
        } else if (command.equals("r")) {
            displayRandomStudent();  // Pick and display a random student
        } else if (command.startsWith("+")) {
            addStudent(command.substring(1));  // Add a new student to the file
        } else if (command.startsWith("?")) {
            searchStudent(command.substring(1));  // Search for a student by name
        } else if (command.equals("c")) {
            countWords();  // Count number of words in the file
        } else {
            System.out.println("Error: Unrecognized command \"" + command + "\".");
            printUsage();
        }
    }

    // Display all student names from the file
    private static void displayAllStudents() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line = reader.readLine();
            String[] studentNames = line.split(",");
            for (String name : studentNames) {
                System.out.println(name.trim());
            }
            System.out.println("Data Loaded.");
        } catch (IOException e) {
            System.out.println("Error loading data.");
        }
    }

    // Display a randomly chosen student name
    private static void displayRandomStudent() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line = reader.readLine();
            String[] studentNames = line.split(",");
            Random rand = new Random();
            int randomIndex = rand.nextInt(studentNames.length);
            System.out.println(studentNames[randomIndex].trim());
            System.out.println("Data Loaded.");
        } catch (IOException e) {
            System.out.println("Error loading data.");
        }
    }

    // Add a new student to the file with a timestamp
    private static void addStudent(String nameToAdd) {
        System.out.println("Loading data ...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
            String timestampFormat = "dd/MM/yyyy-hh:mm:ss a";
            String formattedDate = new SimpleDateFormat(timestampFormat).format(new Date());
            writer.write(", " + nameToAdd + "\nList last updated on " + formattedDate);
            System.out.println("Data Loaded.");
        } catch (IOException e) {
            System.out.println("Error writing data.");
        }
    }

    // Search for a student name in the file
    private static void searchStudent(String nameToSearch) {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line = reader.readLine();
            String[] studentNames = line.split(",");
            for (String name : studentNames) {
                if (name.trim().equals(nameToSearch)) {
                    System.out.println("We found it!");
                    break;
                }
            }
            System.out.println("Data Loaded.");
        } catch (IOException e) {
            System.out.println("Error reading data.");
        }
    }

    // Count the number of words in the student file
    private static void countWords() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line = reader.readLine();
            String[] words = line.trim().split("\\s+");
            System.out.println(words.length + " word(s) found");
            System.out.println("Data Loaded.");
        } catch (IOException e) {
            System.out.println("Error reading data.");
        }
    }

    // Print usage instructions
    private static void printUsage() {
        System.out.println("Valid commands:");
        System.out.println("  a        - Display all students");
        System.out.println("  r        - Display a random student");
        System.out.println("  +Name    - Add a new student");
        System.out.println("  ?Name    - Search for a student");
        System.out.println("  c        - Count words");
    }
}
