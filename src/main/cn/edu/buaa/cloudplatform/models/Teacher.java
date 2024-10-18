package main.cn.edu.buaa.cloudplatform.models;

public class Teacher extends User{
    public Teacher(String id, String name, String password){
        super(id, name, password);
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: Teacher";
    }
}
