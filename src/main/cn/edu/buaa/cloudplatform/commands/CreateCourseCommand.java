package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.models.Teacher;
import main.cn.edu.buaa.cloudplatform.models.User;
import main.cn.edu.buaa.cloudplatform.services.AuthService;
import main.cn.edu.buaa.cloudplatform.services.CourseService;
import main.cn.edu.buaa.cloudplatform.services.UserService;

public class CreateCourseCommand implements Command {
    private CourseService courseService;
    private AuthService authService;
    private UserService userService;

    public CreateCourseCommand(CourseService courseService, AuthService authService, UserService userService) {
        this.courseService = courseService;
        this.authService = authService;
        this.userService = userService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 4) {
            System.out.println("Illegal argument count");
            return;
        }

        if (!authService.isUserLoggedIn()) {
            System.out.println("No one is online");
            return;
        }

        String currentUserId = authService.getCurrentUserId();
        User currentUser = userService.getUserById(currentUserId);

        if (!(currentUser instanceof Teacher)) {
            System.out.println("Permission denied");
            return;
        }

        Teacher teacher = (Teacher) currentUser;

        if (teacher.getCourses().size() >= 10) {
            System.out.println("Course count reaches limit");
            return;
        }

        String courseName = args[0];
        String courseTime = args[1];
        double credits;
        int period;

        if (!isValidCourseName(courseName)) {
            System.out.println("Illegal course name");
            return;
        }

        if (teacher.hasCourseWithName(courseName)) {
            System.out.println("Course name exists");
            return;
        }

        if (!isValidCourseTime(courseTime)) {
            System.out.println("Illegal course time");
            return;
        }

        if (teacher.hasCourseTimeConflict(courseTime)) {
            System.out.println("Course time conflicts");
            return;
        }

        try {
            credits = Double.parseDouble(args[2]);
            if (credits <= 0 || credits > 12) {
                System.out.println("Illegal course credit");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Illegal course credit");
            return;
        }

        try {
            period = Integer.parseInt(args[3]);
            if (period <= 0 || period > 1280) {
                System.out.println("Illegal course period");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Illegal course period");
            return;
        }

        String courseId = courseService.createCourse(courseName, courseTime, credits, period, currentUserId);
        teacher.addCourse(courseId);
        System.out.println("Create course success (courseId: " + courseId + ")");
    }

    private boolean isValidCourseName(String courseName) {
        return courseName.matches("^[a-zA-Z][a-zA-Z0-9-_]{0,19}$");
    }

    private boolean isValidCourseTime(String courseTime) {
        if (!courseTime.matches("^[1-7]_([1-9]|1[0-4])-([1-9]|1[0-4])$")) {
            return false;
        }

        String[] parts = courseTime.split("[-_]");
        int startTime = Integer.parseInt(parts[1]);
        int endTime = Integer.parseInt(parts[2]);

        return startTime <= endTime;
    }
}
