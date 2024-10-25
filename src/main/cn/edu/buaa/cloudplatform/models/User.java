package main.cn.edu.buaa.cloudplatform.models;

public class User {
    protected String id;
    protected String name;
    protected String password;
    protected String identity;

    public User(String id, String name, String password, String identity){
        this.id = id;
        this.name = name;
        this.password = password;
        this.identity = identity;
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

    public String getIdentity(){
        return identity;
    }

    @Override
    public String toString(){
        return "User id: " + id + "\nName: " + name;
    }
}
