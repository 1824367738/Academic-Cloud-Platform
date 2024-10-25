package main.cn.edu.buaa.cloudplatform.commands;

import main.cn.edu.buaa.cloudplatform.models.Administrator;
import main.cn.edu.buaa.cloudplatform.models.Student;
import main.cn.edu.buaa.cloudplatform.models.Teacher;
import main.cn.edu.buaa.cloudplatform.models.User;
import main.cn.edu.buaa.cloudplatform.services.UserService;
import main.cn.edu.buaa.cloudplatform.utils.IdValidator;
import main.cn.edu.buaa.cloudplatform.utils.InputValidator;

public class RegisterCommand implements Command{
    private UserService userService;

    public RegisterCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 5) {
            System.out.println("Illegal argument count");
            return;
        }

        String id = args[0];
        String name = args[1];
        String password = args[2];
        String confirmPassword = args[3];
        String identity = args[4];

        if(!IdValidator.isValidStudentId(id) && !IdValidator.isValidAdminId(id) && !IdValidator.isValidTeacherId(id)){
            System.out.println("Illegal user id");
            return;
        }

        if(userService.userExist(id)){
            System.out.println("User id exists");
            return;
        }

        if(!InputValidator.isValidName(name)){
            System.out.println("Illegal user name");
            return;
        }

        if(!InputValidator.isValidPassword(password)){
            System.out.println("Illegal password");
            return;
        }

        if(!confirmPassword.equals(password)){
            System.out.println("Passwords do not match");
            return;
        }

        User user;
        switch (identity){
            case "Student":
                user = new Student(id, name, password);
                break;
            case  "Teacher":
                user = new Teacher(id, name, password);
                break;
            case "Administrator":
                user = new Administrator(id, name, password);
                break;
            default:
                System.out.println("Illegal identity");
                return;
        }

        if(userService.registerUser(user)){
            System.out.println("Register success");
        }
    }
}
