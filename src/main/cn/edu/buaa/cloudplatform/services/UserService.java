package main.cn.edu.buaa.cloudplatform.services;

import main.cn.edu.buaa.cloudplatform.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();


    /**
     * 注册用户
     * @param user 用户User类
     * @return 注册是否成功
     */
    public boolean registerUser(User user){
        users.put(user.getId(), user);
        return true;
    }

    public boolean userExist(String userId){
        return users.containsKey(userId);
    }

    /**
     * 通过Id获取用户User类
     * @param id 用户ID
     * @return 用户User类
     */
    public User getUserById(String id){
        return users.get(id);
    }

    /**
     * 判断用户是否已注册
     * @param id 用户ID
     * @return 是否注册
     */
    public boolean isUserRegistered(String id){
        return users.containsKey(id);
    }

    /**
     * 获取所有用户User类
     * @return 用户哈希图users
     */
    public Map<String, User> getAllUsers(){
        return users;
    }

    /**
     * 通过用户ID删除用户
     * @param id 用户ID
     * @return 操作是否成功
     */
    public boolean deleteUser(String id){
        if(users.containsKey(id)){
            users.remove(id);
            return true;
        }
        return false;
    }
}
