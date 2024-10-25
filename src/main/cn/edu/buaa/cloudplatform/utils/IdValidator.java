package main.cn.edu.buaa.cloudplatform.utils;

public class IdValidator {
    public static boolean isValidStudentId(String id) {
        if(id != null && id.matches("^(19|20|21|22|23|24)(0[1-9]|[1-3][0-9]|4[0-3])[1-6](00[1-9]|0[1-9][0-9]|[1-9][0-9]{2})}$")){
            //本科生
            return true;
        }
        else if(id != null && id.matches("^(SY|ZY)(21|22|23|24)(0[1-9]|[1-3][0-9]|4[0-3])[1-6](0[1-9]|[1-9][0-9])$")){
            return true;
        }
        else if(id != null && id.matches("^BY(19|20|21|22|23|24)(0[1-9]|[1-3][0-9]|4[0-3])[1-6](0[1-9]|[1-9][0-9])$")){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isValidTeacherId(String id) {
        return id != null && id.matches("^\\d{5}$");
    }

    public static boolean isValidAdminId(String id) {
        return id != null && id.matches("^AD\\d{3}$");
    }
}
