package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.models.*;
import main.cn.edu.buaa.cloudplatform.services.AuthService;
import main.cn.edu.buaa.cloudplatform.services.CourseService;
import main.cn.edu.buaa.cloudplatform.services.UserService;

public class CancelCourseCommand implements Command{
    private AuthService authService;
    private UserService userService;
    private CourseService courseService;

    public CancelCourseCommand(AuthService authService, UserService userService, CourseService courseService){
        this.authService = authService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @Override
    public void execute(String[] args) {
        if(args.length != 1){
            System.out.println("Illegal argument count");
            return;
        }

        if (!authService.isUserLoggedIn()) {
            System.out.println("No one is online");
            return;
        }

        String currentUserId = authService.getCurrentUserId();
        User currentUser = userService.getUserById(currentUserId);

        String courseId = args[0];
        if (!courseId.matches("^C-\\d+$")) {
            System.out.println("Illegal course id");
            return;
        }

        int courseNumber = Integer.parseInt(courseId.substring(2));
        if (courseNumber <= 0) {
            System.out.println("Illegal course id");
            return;
        }

        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            System.out.println("Course does not exist");
            return;
        }

        if (currentUser instanceof Student) {
            Student student = (Student) currentUser;
            if (!student.getCourses().contains(courseId)) {
                System.out.println("Course does not exist");
                return;
            }
            student.removeCourse(courseId);
            System.out.println("Cancel course success (courseId: " + courseId + ")");
        } else if (currentUser instanceof Teacher) {
            Teacher teacher = (Teacher) currentUser;
            if (!teacher.getCourses().contains(courseId)) {
                System.out.println("Course does not exist");
                return;
            }
            teacher.removeCourse(courseId);
            courseService.cancelCourse(courseId);
            removeCourseFromStudents(courseId);
            System.out.println("Cancel course success (courseId: " + courseId + ")");
        } else if (currentUser instanceof Administrator) {
            courseService.cancelCourse(courseId);
            removeCourseFromStudents(courseId);
            System.out.println("Cancel course success (courseId: " + courseId + ")");
        } else {
            System.out.println("Permission denied");
        }
    }

    private void removeCourseFromStudents(String courseId) {
        for (User user : userService.getAllUsers().values()) {
            if (user instanceof Student) {
                Student student = (Student) user;
                student.removeCourse(courseId);
            }
        }
    }
}
