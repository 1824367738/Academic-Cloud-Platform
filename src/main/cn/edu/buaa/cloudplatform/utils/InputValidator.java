package main.cn.edu.buaa.cloudplatform.utils;

public class InputValidator {
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z_]{4,16}$");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@_%$]).{6,16}$");
    }
}
