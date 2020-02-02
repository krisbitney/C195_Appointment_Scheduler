package DataModel;

public class User {

    private int userId;
    private String userName;
    private boolean active;

    public User(int userId, String userName, boolean active) {
        this.userId = userId;
        this.userName = userName;
        this.active = active;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return userName;
    }
}
