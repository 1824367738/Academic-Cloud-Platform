package main.cn.edu.buaa.cloudplatform.utils;

public class IdValidator {
    public static boolean isValidStudentId(String id) {
        return id != null && id.matches("^\\d{8}$");
    }

    public static boolean isValidTeacherId(String id) {
        return id != null && id.matches("^\\d{5}$");
    }

    public static boolean isValidAdminId(String id) {
        return id != null && id.matches("^AD\\d{3}$");
    }
}
