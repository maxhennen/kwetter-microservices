package utils;

import domain.User;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String token;
    private User user;
    private int expiresIn;

    public LoginResponse(String token, User user){
        this.token = token;
        this.user = user;
        this.expiresIn = 3600;
    }

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    //    @Override
//    public String toString() {
//        return "LoginResponse{" +
//                "token='" + token + '\'' +
//                ", user=" + user +
//                '}';
//    }
}