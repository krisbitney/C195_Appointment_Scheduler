package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.ZoneId;

public class DbConnector {

    // Database parameters
    private static final String URL = "jdbc:mysql://3.227.166.251/U06Rwn";
    private static final String USER = "U06Rwn";
    private static final String PASSWORD = "53688847845";
    public static final ZoneId DB_TIME_ZONE = ZoneId.of("UTC");

    // For singleton instantiation
    private static DbConnector sInstance;
    private final Connection connection;

    private static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private DbConnector(Connection connection) {
        this.connection = connection;
    }

    public static DbConnector getInstance() {
        if (sInstance == null) {
            synchronized (DbConnector.class) {
                if (sInstance == null) {
                    sInstance = new DbConnector(getConnection());
                }
            }
        }
        return sInstance;
    }

    public Connection connection() {
        return connection;
    }

    public void close() {
        try {
            connection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
