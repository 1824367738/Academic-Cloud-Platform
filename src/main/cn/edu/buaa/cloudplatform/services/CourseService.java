package main.cn.edu.buaa.cloudplatform.services;

import main.cn.edu.buaa.cloudplatform.models.Course;

import java.util.HashMap;
import java.util.Map;

public class CourseService {
    private Map<String, Course> courses = new HashMap<>();
    private int courseCount = 1;

    public String createCourse(String courseName, String courseTime, double credits, int period,
                               String teacherId){
        String courseId = "C-" + courseCount++;
        Course course = new Course(courseId, courseName, courseTime, credits, period, teacherId);
        courses.put(courseId, course);
        return courseId;
    }

    public Course getCourseById(String id){
        return courses.get(id);
    }

    public boolean isCourseExists(String id){
        return courses.containsKey(id);
    }
}
