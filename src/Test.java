import main.cn.edu.buaa.cloudplatform.commands.*;
import main.cn.edu.buaa.cloudplatform.services.AuthService;
import main.cn.edu.buaa.cloudplatform.services.CourseService;
import main.cn.edu.buaa.cloudplatform.services.UserService;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        // 初始化服务类
        UserService userService = new UserService();
        CourseService courseService = CourseService.getInstance();
        AuthService authService = new AuthService();

        // 初始化命令解析器
        CommandParser commandParser = new CommandParser();

        // 注册命令
        commandParser.registerCommand("quit", new QuitCommand(authService));
        commandParser.registerCommand("register", new RegisterCommand(userService));
        commandParser.registerCommand("login", new LoginCommand(userService, authService));
        commandParser.registerCommand("logout", new LogoutCommand(authService, userService));
        commandParser.registerCommand("printInfo", new PrintInfoCommand(authService, userService));
        commandParser.registerCommand("createCourse", new CreateCourseCommand(courseService, authService, userService));
        commandParser.registerCommand("listCourse", new ListCourseCommand(courseService, authService, userService));
        commandParser.registerCommand("selectCourse", new SelectCourseCommand(courseService, authService, userService));
        commandParser.registerCommand("cancelCourse", new CancelCourseCommand(authService, userService, courseService));

        // 读取用户输入并解析执行命令
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            commandParser.parseAndExecute(input);
        }
    }
}
