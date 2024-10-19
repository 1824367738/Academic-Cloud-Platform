package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.services.AuthService;

public class QuitCommand implements Command{
    private AuthService authService;

    public QuitCommand(AuthService authService){
        this.authService = authService;
    }

    @Override
    public void execute(String[] args) {
        if(args.length != 0){
            System.out.println("Illegal argument count");
            return;
        }
        authService.logoutAllUsers();
        System.out.println("----- Good Bye! -----");
        System.exit(0);
    }
}
