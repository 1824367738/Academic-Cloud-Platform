package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.models.User;
import main.cn.edu.buaa.cloudplatform.services.AuthService;
import main.cn.edu.buaa.cloudplatform.services.UserService;
import main.cn.edu.buaa.cloudplatform.utils.IdValidator;

public class LoginCommand implements Command{
    private UserService userService;
    private AuthService authService;

    public LoginCommand(UserService userService, AuthService authService){
        this.authService = authService;
        this.userService = userService;
    }

    @Override
    public void execute(String[] args) {
        if(args.length != 2){
            System.out.println("Illegal argument count");
            return;
        }

        String id = args[0];
        String password = args[1];

        if(!IdValidator.isValidStudentId(id) && !IdValidator.isValidAdminId(id) && !IdValidator.isValidTeacherId(id)){
            System.out.println("Illegal user id");
            return;
        }

        if (!userService.isUserRegistered(id)) {
            System.out.println("User does not exist");
            return;
        }

        User user = userService.getUserById(id);
        if (authService.isUserLoggedIn(id)) {
            System.out.println(id + " is online");
            return;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Wrong password");
            return;
        }

        authService.loginUser(id);
        System.out.println("Welcome to ACP, " + id);
    }
}
