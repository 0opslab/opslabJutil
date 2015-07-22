package model;


import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String userName;

    public User() {
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof User) {
            User user = (User) obj;
            if (user.getUserId().equals(this.userId) && user.getUserName().equals(this.userName)) {
                return true;
            }
        }
        return false;
    }
}
