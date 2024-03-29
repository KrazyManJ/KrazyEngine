package me.KrazyManJ.KrazyEngine.Any;

import me.KrazyManJ.KrazyEngine.Any.Text.StringUtils;
import org.intellij.lang.annotations.Language;

import java.sql.*;

@SuppressWarnings("unused")
public final class MySQL {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    public void connect(String host, int port, String username, String password) {
        try {
            connection = DriverManager.getConnection(
                    StringUtils.format("jdbc:mysql://{0}:{1}?autoReconnect=true", host, port), username, password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnectionValid() {
        try (PreparedStatement statement = prepare("SELECT 1")) {
            statement.executeUpdate();
        } catch (SQLException ignored) {
            return false;
        }
        return true;
    }

    public boolean isConnected() {
        if (connection == null) return false;
        try {
            if (connection.isClosed()) return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return isConnectionValid();
    }

    public void disconnect() {
        if (isConnected()) try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement prepare(@Language("SQL") String command, Object... params) throws SQLException {
        PreparedStatement s = connection.prepareStatement(command);
        for (int i = 0; i < params.length; i++) s.setObject(i, params[i]);
        return s;
    }

    public void query(@Language("SQL") String command, Object... params) {
        try (PreparedStatement statement = prepare(command, params)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer queryOne(@Language("SQL") String command, Object... params) {
        try (PreparedStatement statement = prepare(command, params)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet queryAll(@Language("SQL") String command, Object... params) {
        try (PreparedStatement statement = prepare(command, params)) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
