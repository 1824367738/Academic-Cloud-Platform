package main.cn.edu.buaa.cloudplatform.services;

import java.util.HashSet;
import java.util.Set;

public class AuthService {
    private Set<String> loggedInUsers = new HashSet<>();
    private String currentUserId;

    /**
     * 登录用户
     * @param userId 用户ID
     */
    public void loginUser(String userId){
        loggedInUsers.add(userId);
        currentUserId = userId;
    }

    /**
     * 登出用户
     * @param userId 用户ID
     */
    public void logoutUser(String userId){
        loggedInUsers.remove(userId);
        if(userId.equals(currentUserId)){
            currentUserId = null;
        }
    }

    /**
     * 全部登出，quit
     */
    public void logoutAllUsers(){
        for(String userId : loggedInUsers){
            System.out.println(userId + " Bye~");
        }
        loggedInUsers.clear();
        currentUserId = null;
    }

    /**
     * 判断是否有该用户登录
     * @param userId 用户ID
     * @return 是否
     */
    public boolean isUserLoggedIn(String userId){
        return loggedInUsers.contains(userId);
    }

    /**
     * 判断当前是否有用户登录
     * @return 是否
     */
    public boolean isUserLoggedIn(){
        return currentUserId != null;
    }

    /**
     * 获取当前在线用户ID
     * @return 当前在线用户ID
     */
    public String getCurrentUserId(){
        return currentUserId;
    }
}
