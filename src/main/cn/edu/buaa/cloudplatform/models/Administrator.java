package main.cn.edu.buaa.cloudplatform.models;

public class Administrator extends User{
    public Administrator(String id, String name, String password){
        super(id, name, password, "Administrator");
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: Administrator";
    }
}
