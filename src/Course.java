public class Course {
    private String courseCode;
    private int courseUnit;
    private int courseScore;

    public Course(String courseCode, int courseUnit, int courseScore) {
        this.courseCode = courseCode;
        this.courseUnit = courseUnit;
        this.courseScore = courseScore;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCourseUnit() {
        return courseUnit;
    }

    public int getCourseScore() {
        return courseScore;
    }

    public int getGradeUnit() {
        return GpaCalculator.calculateGradeUnit(courseScore);
    }
}
