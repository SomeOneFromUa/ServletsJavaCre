package signINUP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataUsers {
    private static final String url = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
    private static final String log = "root";
    private static final String pass  = "1111";

    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, log, pass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
