
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = env(
            "DB_URL",
            "jdbc:mysql://localhost:3306/math_whiz_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
    );

    private static final String DB_USER =
            env("DB_USER", "mathwhiz_app");

    private static final String DB_PASSWORD =
            env("DB_PASSWORD", "MathWhizLocal@123");

    private static String env(String key, String fallback) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? fallback : value;
    }

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "MySQL JDBC driver not found. Check the Maven dependency.", e
            );
        }

        return DriverManager.getConnection(
                URL, DB_USER, DB_PASSWORD
        );
    }
}
