package main.cn.edu.buaa.cloudplatform.models;

import main.cn.edu.buaa.cloudplatform.services.CourseService;

import java.util.HashSet;
import java.util.Set;

public class Student extends User {
    private Set<String> courses = new HashSet<>();

    public Student(String id, String name, String password) {
        super(id, name, password);
    }

    public Set<String> getCourses() {
        return courses;
    }

    public void addCourse(String courseId) {
        courses.add(courseId);
    }

    public boolean hasCourseTimeConflict(String courseTime) {
        CourseService courseService = CourseService.getInstance();
        for (String courseId : courses) {
            if (isTimeConflict(courseService.getCourseById(courseId).getCourseTime(), courseTime)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTimeConflict(String existingTime, String newTime) {
        String[] existingParts = existingTime.split("_");
        String[] newParts = newTime.split("_");

        int existingDay = Integer.parseInt(existingParts[0]);
        int newDay = Integer.parseInt(newParts[0]);

        if (existingDay != newDay) {
            return false;
        }

        String[] existingTimes = existingParts[1].split("-");
        String[] newTimes = newParts[1].split("-");

        int existingStart = Integer.parseInt(existingTimes[0]);
        int existingEnd = Integer.parseInt(existingTimes[1]);
        int newStart = Integer.parseInt(newTimes[0]);
        int newEnd = Integer.parseInt(newTimes[1]);

        return (newStart <= existingEnd && newEnd >= existingStart);
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: Student";
    }
}
