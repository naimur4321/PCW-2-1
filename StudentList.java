import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static void main(String[] args) {
        // Step #2: Check for valid number of arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments.");
            System.out.println("Usage examples:");
            System.out.println("  java StudentList a       // Display all students");
            System.out.println("  java StudentList r       // Display a random student");
            System.out.println("  java StudentList +Name   // Add a new student");
            System.out.println("  java StudentList ?Name   // Search for a student");
            System.out.println("  java StudentList c       // Count words");
            return;
        }

        String command = args[0];

        if (command.equals("a")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("students.txt")
                        )
                );
                String studentDataLine = reader.readLine();
                String[] studentList = studentDataLine.split(",");
                for (String student : studentList) {
                    System.out.println(student.trim());
                }
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error loading data.");
            }
        } else if (command.equals("r")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("students.txt")
                        )
                );
                String studentDataLine = reader.readLine();
                String[] studentList = studentDataLine.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(studentList.length);
                System.out.println(studentList[randomIndex].trim());
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error loading data.");
            }
        } else if (command.contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("students.txt", true)
                );
                String newStudent = command.substring(1);
                Date currentDate = new Date();
                String dateFormatPattern = "dd/MM/yyyy-hh:mm:ss a";
                DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
                String formattedDate = dateFormat.format(currentDate);
                writer.write(", " + newStudent + "\nList last updated on " + formattedDate);
                writer.close();
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error writing data.");
            }
        } else if (command.contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("students.txt")
                        )
                );
                String studentDataLine = reader.readLine();
                String[] studentList = studentDataLine.split(",");
                String searchName = command.substring(1);
                boolean found = false;
                for (String student : studentList) {
                    if (student.trim().equals(searchName)) {
                        System.out.println("We found it!");
                        found = true;
                        break;
                    }
                }
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error reading data.");
            }
        } else if (command.equals("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("students.txt")
                        )
                );
                String studentDataLine = reader.readLine();
                char[] characterArray = studentDataLine.toCharArray();
                boolean insideWord = false;
                int wordCount = 0;
                for (char character : characterArray) {
                    if (character == ' ') {
                        if (!insideWord) {
                            wordCount++;
                            insideWord = true;
                        }
                    } else {
                        insideWord = false;
                    }
                }
                System.out.println(wordCount + " word(s) found");
                System.out.println("Data Loaded.");
            } catch (Exception e) {
                System.out.println("Error reading data.");
            }
        } else {
            System.out.println("Invalid command argument.");
        }
    }
}
