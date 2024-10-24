package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.models.Course;
import main.cn.edu.buaa.cloudplatform.models.Student;
import main.cn.edu.buaa.cloudplatform.models.User;
import main.cn.edu.buaa.cloudplatform.services.AuthService;
import main.cn.edu.buaa.cloudplatform.services.CourseService;
import main.cn.edu.buaa.cloudplatform.services.UserService;

public class SelectCourseCommand implements Command{
    private CourseService courseService;
    private AuthService authService;
    private UserService userService;

    public SelectCourseCommand(CourseService courseService, AuthService authService, UserService userService) {
        this.courseService = courseService;
        this.authService = authService;
        this.userService = userService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Illegal argument count");
            return;
        }

        if (!authService.isUserLoggedIn()) {
            System.out.println("No one is online");
            return;
        }

        String currentUserId = authService.getCurrentUserId();
        User currentUser = userService.getUserById(currentUserId);

        if (!(currentUser instanceof Student)) {
            System.out.println("Permission denied");
            return;
        }

        String courseId = args[0];
        if (!courseId.matches("^C-\\d+$")) {
            System.out.println("Illegal course id");
            return;
        }

        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            System.out.println("Course does not exist");
            return;
        }

        Student student = (Student) currentUser;
        if (student.hasCourseTimeConflict(course.getCourseTime())) {
            System.out.println("Course time conflicts");
            return;
        }

        student.addCourse(courseId);
        System.out.println("Select course success (courseId: " + courseId + ")");
    }
}
