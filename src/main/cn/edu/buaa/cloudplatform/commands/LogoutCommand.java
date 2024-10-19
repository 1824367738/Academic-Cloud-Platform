package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.services.AuthService;

public class LogoutCommand implements Command{
    private AuthService authService;

    public LogoutCommand(AuthService authService){
        this.authService = authService;
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
            if (!authService.isUserLoggedIn(userId)) {
                System.out.println(userId + " is not online");
                return;
            }
            authService.logoutUser(userId);
            System.out.println(userId + " Bye~");
        }
    }
}
