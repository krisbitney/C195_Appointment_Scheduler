package Dao;

import DataModel.User;
import Utils.DbConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImp implements UserDao {

    @Override
    public ObservableList<User> getAllActiveUsers() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        try {
            String query = "select userId, userName from user where active > 0 and userName is not null";
            ResultSet resultSet = DbConnector.getInstance().connection().createStatement().executeQuery(query);
            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String userName = resultSet.getString("userName");
                User newUser = new User(userId, userName, true);
                allUsers.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public Integer getUserIdByUserNameAndPassword(String userName, String password) {
        Integer userId = null;
            try {
                String query = "select userId, userName from user " +
                                "where userName= ? and " +
                                "password= ? and " +
                                "active > 0" + " and " +
                                "userName is not null and password is not null";
                PreparedStatement ps = DbConnector.getInstance().connection().prepareStatement(query);
                ps.setString(1, userName);
                ps.setString(2, password);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    userId = resultSet.getInt("userId");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return userId;
    }
}
