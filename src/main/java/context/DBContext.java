
package context;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBContext {

    /**
     * Get database connection
     *
     * @return a Connection object
     */
    public static Connection getConnection() throws Exception {
        Connection conn = null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream f = classLoader.getResourceAsStream("db.properties")) {

            // load the properties file
            Properties pros = new Properties();
            pros.load(f);

            // assign db parameters
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");

            // create a connection to the database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
