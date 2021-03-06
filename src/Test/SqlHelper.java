package Test;

import Utils.DbConnector;

import java.sql.SQLException;

public class SqlHelper {

    private static class SqlHelperSingleton {
        private static final SqlHelper INSTANCE = new SqlHelper();
    }

    private SqlHelper() {}

    public static SqlHelper getInstance() {
        return SqlHelperSingleton.INSTANCE;
    }

    public int update(String q) {
        int result = 0;
            try  {
                result = DbConnector.getInstance().connection().createStatement().executeUpdate(q);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return result;
    }

}