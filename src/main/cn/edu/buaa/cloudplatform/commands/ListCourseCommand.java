package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.models.Administrator;
import main.cn.edu.buaa.cloudplatform.models.Course;
import main.cn.edu.buaa.cloudplatform.models.Teacher;
import main.cn.edu.buaa.cloudplatform.models.User;
import main.cn.edu.buaa.cloudplatform.services.AuthService;
import main.cn.edu.buaa.cloudplatform.services.CourseService;
import main.cn.edu.buaa.cloudplatform.services.UserService;

import java.util.*;

public class ListCourseCommand implements Command{
    private CourseService courseService;
    private AuthService authService;
    private UserService userService;

    public ListCourseCommand(CourseService courseService, AuthService authService, UserService userService) {
        this.courseService = courseService;
        this.authService = authService;
        this.userService = userService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Illegal argument count");
            return;
        }

        if (!authService.isUserLoggedIn()) {
            System.out.println("No one is online");
            return;
        }

        String currentUserId = authService.getCurrentUserId();
        User currentUser = userService.getUserById(currentUserId);

        if (args.length == 0) {
            if (currentUser instanceof Teacher) {
                listTeacherCourses((Teacher) currentUser);
            } else {
                listAllCourses();
            }
        } else {
            if (!(currentUser instanceof Administrator)) {
                System.out.println("Permission denied");
                return;
            }

            String teacherId = args[0];
            if (!userService.isUserRegistered(teacherId)) {
                System.out.println("User does not exist");
                return;
            }

            User user = userService.getUserById(teacherId);
            if (!(user instanceof Teacher)) {
                System.out.println("User id does not belong to a Teacher");
                return;
            }

            listTeacherCourses((Teacher) user);
        }
    }

    private void listTeacherCourses(Teacher teacher) {
        Set<String> courseIds = teacher.getCourses();
        if (courseIds.isEmpty()) {
            System.out.println("Course does not exist");
            return;
        }

        List<Course> courses = new ArrayList<>();
        for (String courseId : courseIds) {
            courses.add(courseService.getCourseById(courseId));
        }

        courses.sort(Comparator.comparing(Course::getCourseId));

        for (Course course : courses) {
            System.out.println(course.getCourseId() + " " + course.getCourseName() + " " + course.getCourseTime() + " " + String.format("%.1f", course.getCredits()) + " " + course.getPeriod());
        }
        System.out.println("List course success");
    }

    private void listAllCourses() {
        Map<String, Course> allCourses = courseService.getAllCourses();
        if (allCourses.isEmpty()) {
            System.out.println("Course does not exist");
            return;
        }

        List<Course> courses = new ArrayList<>(allCourses.values());
        courses.sort(Comparator.comparing(Course::getCourseId));

        Map<String, List<Course>> coursesByTeacher = new TreeMap<>();
        for (Course course : courses) {
            coursesByTeacher.computeIfAbsent(course.getTeacherId(), k -> new ArrayList<>()).add(course);
        }

        for (Map.Entry<String, List<Course>> entry : coursesByTeacher.entrySet()) {
            String teacherName = userService.getUserById(entry.getKey()).getName();
            for (Course course : entry.getValue()) {
                System.out.println(teacherName + " " + course.getCourseId() + " " + course.getCourseName() + " " + course.getCourseTime() + " " + String.format("%.1f", course.getCredits()) + " " + course.getPeriod());
            }
        }
        System.out.println("List course success");
    }
}
