package main.cn.edu.buaa.cloudplatform.models;

public class User {
    protected String id;
    protected String name;
    protected String password;

    public User(String id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return "User id: " + id + "\nName: " + name;
    }
}
