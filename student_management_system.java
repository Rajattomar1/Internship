import java.io.*;
import java.util.*;

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Inner class to represent a Student
    private static class Student {
        private String name;
        private String rollNumber;
        private String grade;

        // Constructor
        public Student(String name, String rollNumber, String grade) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public String getGrade() {
            return grade;
        }

        // toString to display student info
        @Override
        public String toString() {
            return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
        }
    }

    // Method to add a student
    public void addStudent() {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        // Input validation
        if (name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        // Prevent duplicate entries
        for (Student student : students) {
            if (student.getRollNumber().equalsIgnoreCase(rollNumber)) {
                System.out.println("Student with roll number " + rollNumber + " already exists.");
                return;
            }
        }

        students.add(new Student(name, rollNumber, grade));
        System.out.println("Student added successfully.");
    }

    // Method to remove a student
    public void removeStudent() {
        System.out.print("Enter roll number of the student to remove: ");
        String rollNumber = scanner.nextLine();

        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getRollNumber().equalsIgnoreCase(rollNumber)) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    // Method to search for a student
    public void searchStudent() {
        System.out.print("Enter roll number to search: ");
        String rollNumber = scanner.nextLine();

        for (Student student : students) {
            if (student.getRollNumber().equalsIgnoreCase(rollNumber)) {
                System.out.println("Student found: " + student);
                return;
            }
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    // Method to display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Method to save students to a file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                writer.newLine();
            }
            System.out.println("Student data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data to file.");
        }
    }

    // Method to load students from a file
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                } else {
                    System.out.println("Skipping invalid data entry.");
                }
            }
            System.out.println("Student data loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading data from file.");
        }
    }

    // Method to display the menu
    public void displayMenu() {
        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    removeStudent();
                    break;
                case "3":
                    searchStudent();
                    break;
                case "4":
                    displayAllStudents();
                    break;
                case "5":
                    saveToFile();
                    break;
                case "6":
                    loadFromFile();
                    break;
                case "7":
                    System.out.println("Exiting... Thank you for using the Student Management System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.displayMenu();
    }
}
