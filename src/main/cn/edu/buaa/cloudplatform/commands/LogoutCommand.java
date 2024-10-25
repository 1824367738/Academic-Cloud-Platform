package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.models.User;
import main.cn.edu.buaa.cloudplatform.services.AuthService;
import main.cn.edu.buaa.cloudplatform.services.UserService;
import main.cn.edu.buaa.cloudplatform.utils.IdValidator;

public class LogoutCommand implements Command{
    private AuthService authService;
    private UserService userService;

    public LogoutCommand(AuthService authService, UserService userService){
        this.authService = authService;
        this.userService = userService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Illegal argument count");
            return;
        }

        if (args.length == 0) {
            if (!authService.isUserLoggedIn()) {
                System.out.println("No one is online");
                return;
            }
            String currentUserId = authService.getCurrentUserId();
            authService.logoutUser(currentUserId);
            System.out.println(currentUserId + " Bye~");
        } else {
            String userId = args[0];
            String currentUserId = authService.getCurrentUserId();
            User user = userService.getUserById(currentUserId);
            if(!user.getIdentity().equals("Administrator")){
                System.out.println("Permission denied");
                return;
            }

            if(!IdValidator.isValidStudentId(userId) && !IdValidator.isValidAdminId(userId) && !IdValidator.isValidTeacherId(userId)){
                System.out.println("Illegal user id");
                return;
            }

            if(!userService.userExist(userId)){
                System.out.println("User does not exist");
                return;
            }

            if (!authService.isUserLoggedIn(userId)) {
                System.out.println(userId + " is not online");
                return;
            }
            authService.logoutUser(userId);
            System.out.println(userId + " Bye~");
        }
    }
}
