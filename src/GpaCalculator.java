import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GpaCalculator {
    private List<Course> courses;

    public GpaCalculator() {
        this.courses = new ArrayList<>();
    }

    public void runGpaCalculator() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to GPA Calculator");

        // Input course details
        while (true) {
            try {
                System.out.print("Enter Course Code (or 'exit' to finish): ");
                String courseCode = scanner.nextLine();
                if (courseCode.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter Course Unit: ");
                int courseUnit = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left by nextInt()

                // Input validation for Course Score (catch non-integer input)
                int courseScore;
                while (true) {
                    try {
                        System.out.print("Enter Course Score: ");
                        courseScore = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline left by nextInt()
                        break; // Exit the loop if input is an integer
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer for Course Score.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }

                // Create a Course object and add it to the list
                Course course = new Course(courseCode, courseUnit, courseScore);
                courses.add(course);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid data.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        // Print the header of the table
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");
        System.out.println("| COURSE & CODE              | COURSE UNIT           | GRADE      | GRADE-UNIT          |");
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");

        // Print course details in the table
        for (Course course : courses) {
            System.out.printf("| %-26s | %-21d | %-10s | %-19d |\n",
                    course.getCourseCode(), course.getCourseUnit(),
                    getGrade(course.getCourseScore()), course.getGradeUnit());
        }

        // Calculate and print CGPA
        double cgpa = calculateCGPA(courses);
        System.out.printf("|---------------------------------------------------------------------------------------|\n");
        System.out.printf("Your CGPA is = %.2f to 2 decimal places.\n", cgpa);
    }

    // Other methods (e.g., calculateCGPA) can remain in this class

    // Method to calculate grade unit based on the given grading system
    public static int calculateGradeUnit(int score) {
        if (score >= 70) {
            return 5;
        } else if (score >= 60) {
            return 4;
        } else if (score >= 50) {
            return 3;
        } else if (score >= 45) {
            return 2;
        } else if (score >= 40) {
            return 1;
        } else {
            return 0;
        }
    }

    // Method to get the grade based on the given grading system
    public static String getGrade(int score) {
        if (score >= 70) {
            return "A";
        } else if (score >= 60) {
            return "B";
        } else if (score >= 50) {
            return "C";
        } else if (score >= 45) {
            return "D";
        } else if (score >= 40) {
            return "E";
        } else {
            return "F";
        }
    }

    // Method to calculate CGPA
    public static double calculateCGPA(List<Course> courses) {
        double totalQualityPoints = 0;
        int totalGradeUnits = 0;

        for (Course course : courses) {
            int gradeUnit = calculateGradeUnit(course.getCourseScore());
            double qualityPoint = course.getCourseUnit() * gradeUnit;

            totalQualityPoints += qualityPoint;
            totalGradeUnits += course.getCourseUnit();
        }

        if (totalGradeUnits == 0) {
            return 0; // Avoid division by zero
        }
        return totalQualityPoints / totalGradeUnits;
    }
}
