package signINUP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String password;

    private Connection connection;

    public void SetUser(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void SetUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(Connection connection) {
        this.connection = connection;
    }
    private Boolean validator () {
        boolean flag = false;
        if (this.name == null || this.password== null){
            return false;
        }
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("select * from users_table where user_login = ? and user_password = ?");
            preparedStatement.setString(1, this.name);
            preparedStatement.setString(2, this.password);
            ResultSet resultSet = preparedStatement.executeQuery();
            flag = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean signUp () {
        Boolean validator = validator();
        int changes = 0;
        if (!validator) {
            try {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("insert into users_table(user_login, user_password) values (?,?)");
                preparedStatement.setString(1, this.name);
                preparedStatement.setString(2, this.password);
                changes = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return changes == 1;
    }

    public boolean signIn (){
        return validator();
    }
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
