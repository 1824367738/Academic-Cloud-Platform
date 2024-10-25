package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.models.Administrator;
import main.cn.edu.buaa.cloudplatform.models.User;
import main.cn.edu.buaa.cloudplatform.services.AuthService;
import main.cn.edu.buaa.cloudplatform.services.UserService;
import main.cn.edu.buaa.cloudplatform.utils.IdValidator;

public class PrintInfoCommand implements Command{
    private AuthService authService;
    private UserService userService;

    public PrintInfoCommand(AuthService authService, UserService userService){
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
            System.out.println(currentUser);
            System.out.println("Print information success");
        } else {
            String userId = args[0];
            if (!(currentUser instanceof Administrator)) {
                System.out.println("Permission denied");
                return;
            }

            if(!IdValidator.isValidStudentId(userId) && !IdValidator.isValidAdminId(userId) && !IdValidator.isValidTeacherId(userId)){
                System.out.println("Illegal user id");
                return;
            }

            User user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("User does not exist");
                return;
            }
            System.out.println(user);
            System.out.println("Print information success");
        }
    }
}
