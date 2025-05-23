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
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                for (String student : reader.readLine().split(",")) {
                    System.out.println(student.trim());
                }
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error loading data.");
            }
        } else if (command.equals("r")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String[] studentList = reader.readLine().split(",");
                System.out.println(studentList[new Random().nextInt(studentList.length)].trim());
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error loading data.");
            }
        } else if (command.contains("+")) {
            System.out.println("Loading data ...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss a").format(new Date());
                writer.write(", " + command.substring(1) + "\nList last updated on " + formattedDate);
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error writing data.");
            }
        } else if (command.contains("?")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                for (String student : reader.readLine().split(",")) {
                    if (student.trim().equals(command.substring(1))) {
                        System.out.println("We found it!");
                        break;
                    }
                }
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error reading data.");
            }
        } else if (command.equals("c")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String[] words = reader.readLine().trim().split("\\s+");
                System.out.println(words.length + " word(s) found");
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error reading data.");
            }
        } else {
            System.out.println("Error: Unrecognized command \"" + command + "\".");
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("Valid commands:");
        System.out.println("  a        - Display all students");
        System.out.println("  r        - Display a random student");
        System.out.println("  +Name    - Add a new student");
        System.out.println("  ?Name    - Search for a student");
        System.out.println("  c        - Count words");
    }
}
