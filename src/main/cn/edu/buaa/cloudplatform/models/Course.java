package main.cn.edu.buaa.cloudplatform.models;

public class Course {
    private String courseId;
    private String courseName;
    private String courseTime;
    private double credits;
    private int period;
    private String teacherId;

    public Course(String courseId, String courseName, String courseTime, double credits,
                  int period, String teacherId){
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.credits = credits;
        this.period = period;
        this.teacherId = teacherId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public double getCredits() {
        return credits;
    }

    public int getPeriod() {
        return period;
    }

    public String getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return "Course id: " + courseId + "\nCourse name: " + courseName + "\nCourse time: " + courseTime +
                "\nCredits: " + credits + "\nPeriod: " + period + "\nTeacher id: " + teacherId;
    }
}
